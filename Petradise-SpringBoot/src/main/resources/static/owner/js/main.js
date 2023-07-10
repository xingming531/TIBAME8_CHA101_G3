$(document).ready(function() {

	var usernamePage = $('#username-page');
	var chatPage = $('#chat-page');

	var hotelName = getQueryParam('hotelName');
	var role = getQueryVariable("role");
	if (role === "owner") {
		$("#name").prop("readonly",true).css("background-color","lightgray");
		$("#name").val(hotelName);

	
	}


	function getQueryVariable(variable) {
		var query = window.location.search.substring(1); //取的url ?後面的字串,並移除?
		var vars = query.split("&"); //再拿掉&
		for (var i = 0; i < vars.length; i++) {   // vars = role=owner
			var pair = vars[i].split("=");
			if (pair[0] == variable) {		//pair[0]=role
				return pair[1];				//pair[1]=owner
			}
		}
		return false;
	}

	function getQueryParam(param) {
		var urlParams = new URLSearchParams(window.location.search);
		return urlParams.get(param);
	}

	var hotelId = getQueryParam('hotelId');
	var hotelName = null;
	console.log(hotelId);


	$.ajax({
		url: '/hotelName',
		method: 'GET',
		data: {
			hotelId: hotelId // replace this with the actual hotel ID
		},
		success: function(res) {
			console.log(res);
			hotelName = res[0].hotelName;

			$('h2').text(hotelName);

		},
		error: function(xhr, status, error) {
			console.error('Failed to get hotel name:', error);
		}
	});



	var usernamePage = $('#username-page');
	var chatPage = $('#chat-page');
	var usernameForm = $('#usernameForm');
	var messageForm = $('#messageForm');
	var messageInput = $('#message');
	var messageArea = $('#messageArea');
	var connectingElement = $('.connecting');
	var nameInput = $('#name');
	var popup = $('#hint');

	var stompClient = null;
	var username = null;



	loadMessages(hotelId);

	//消息拿出顯示到前端
	function loadMessages() {
		var hotelId = getQueryParam('hotelId');
		$.ajax({
			url: '/api/messages/guest?hotelId=' + hotelId,
			method: 'GET',
			success: function(response) {
				console.log(response);
				var messages = response;
				for (var i = 0; i < messages.length; i++) {
					var message = messages[i];
					appendMessage(message);
				}
			},
			error: function(xhr, status, error) {

				console.error('Failed to load messages:', error);
			}
		});
	}

	function appendMessage(message) {

		var messageElement = $('<li></li>');

		var timestampElement = $('<br><span class="timestamp"></span>');
		var timestampText = new Date(message.timestamp).toLocaleTimeString();
		timestampElement.text(timestampText);


		if (message.content !== null && message.content !== "") {

			var senderElement = $('<span class="name"></span>').text(message.sender + ": ");


			if (message.sender === $('#name').val()) {
				messageElement.css('text-align', 'right');
			}
			messageElement.append(senderElement);



			var contentElement = $('<br><span class="messageBox"></span>');
			if (message.content !== null) {
				contentElement.text(message.content);
			}
			messageElement.append(contentElement);
			messageElement.append(timestampElement);

			$('#messageArea').append(messageElement);
		}

	}



	// 連線
	function connect(event) {
		username = $('#name').val().trim();


		if (username) {
			usernamePage.addClass('hidden');
			chatPage.removeClass('hidden');

			var socket = new SockJS('/chatroom');
			stompClient = Stomp.over(socket);

			stompClient.connect({}, onConnected, onError);
		} else if (hotelName) {
		} else {
			popHint();
		}
		event.preventDefault();
	}

	// 連線建立後要處理的邏輯
	function onConnected() {
		// 訂閱/topic/public
		stompClient.subscribe('/topic/public', onMessageReceived);

		// 發送訊息至/app/join
		stompClient.send("/app/join", {}, JSON.stringify({
			sender: username,
			type: 'JOIN',

		}))

		$('.connecting').addClass('hidden');
	}

	function onError(error) {
		connectingElement.text('Could not connect to WebSocket server. Please refresh this page to try again!');
		connectingElement.css('color', 'red');
	}



	function popHint() {
		popup.toggleClass("show");
	}

	function sendMessage(event) {
		var messageContent = messageInput.val().trim();
		if (messageContent && stompClient) {
			var chatMessage = {
				sender: username,
				content: messageInput.val(),
				type: 'CHAT',
				timestamp: new Date().toISOString(), // adding current time as timestamp
				hotelId: hotelId
			};
			stompClient.send("/app/guest-chat", {}, JSON.stringify(chatMessage));
			messageInput.val('');
		}
		return false;
	}

	/**
	 * 從後端接受訊息後要進行的處理
	 * @param payload 後端送來的訊息
	 * @returns
	 */
	function onMessageReceived(payload) {
		var message = JSON.parse(payload.body);
		appendMessage(message);
		messageArea.scrollTop(messageArea[0].scrollHeight);
	}





	/**
	 * 取得使用者名稱的元素
	 * @param sender 使用者名稱
	 * @returns
	 */
	function getUsernameElement(sender) {
		var usernameElement = document.createElement('span');
		var usernameText = document.createTextNode(sender);
		usernameElement.appendChild(usernameText);
		usernameElement.className = 'name';
		return usernameElement;
	}

	function removePopup(event) {
		popup.removeClass("show");
	}

	// 監聽 'name' class span 的點擊事件
	$(document).on('click', '.name', function() {
		var replyName = "回覆#" + $(this).text();
		$('#message').val(replyName);
		console.log(replyName);

	});


	// 監聽回覆輸入框的 'blur' 事件
	$(document).on('blur', '#replyBox', function() {
		if ($.contains(this.parentNode, this)) {
			$(this).remove();
		}
	});

	// 監聽回覆輸入框的 'keydown' 事件
	$(document).on('keydown', '#replyBox', function(e) {
		if (e.key === 'Enter') {
			// 當 Enter 鍵被按下時，獲取輸入的回覆
			var reply = $(this).val().trim();
			if (reply !== "") {
				// 如果回覆不為空，則將它添加到相應的訊息後面
				var replyNameElement = $('<span class="replyName"></span>').text(username + ': '); //回覆人的名稱
				var replyMessageElement = $('<p class="replyMessage"></p>').text(reply); //回覆的訊息
				var replyElement = $('<div class="reply"></div>').append(replyNameElement, replyMessageElement);
				$(this).parent().append(replyElement);

				if ($.contains(this.parentNode, '#replyBox')) {
					$('#replyBox').remove();
				}
			}
		}
	});
	function sendReply(replyTo, replyContent) {
		var chatMessage = {
			sender: username,
			replyTo: replyTo,
			replyContent: replyContent,
			type: 'REPLY'
		};


		stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
	}

	nameInput.on('focus', removePopup);
	usernameForm.on('submit', connect);
	messageForm.on('submit', sendMessage);
})
