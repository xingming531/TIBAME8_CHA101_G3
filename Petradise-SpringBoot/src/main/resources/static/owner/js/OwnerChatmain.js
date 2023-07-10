	$(document).ready(function() {
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
	    var hotelId = null;
		
		$('#usernameForm').on('submit', function(event) {
	    event.preventDefault(); // 阻止表單的默認提交行為
	
	    var account = $('#account').val().trim();
	    var password = $('#password').val().trim();
	
	    if (account && password) {
	        // 向後端的 /login 端點發送 POST 請求
	        $.ajax({
	            url: '/login',
	            type: 'POST',
	            data: {
	                account: account,
	                password: password
	            },
	            success: function(res) {
	                // 如果登入成功，則隱藏登入頁面並顯示聊天頁面
	                if (res) {
						console.log(res);
	                    username ="✨" + res.ownerName; // 將用戶名設置為成功登入的帳號
	                    hotelName = "✨" + res.hotelName;
	                    hotelId = res.hotelId;
	                    usernamePage.addClass('hidden');
	                    chatPage.removeClass('hidden'); //顯示聊天畫面

	                    window.location.href = "/owner/Chat.html?hotelId=" + hotelId + "&role=owner&hotelName=" + encodeURIComponent(hotelName);

	                    $('h2').text(res.hotelName);
	
	                    // 預先已經寫好的websocket連接程式碼
	                    var socket = new SockJS('/chatroom');
	                    stompClient = Stomp.over(socket);
	
	                    stompClient.connect({}, onConnected, onError);
	                    loadMessages(hotelId);
	                    
	                } 
	            },
	            error: function(xhr, status, error) {
	                // 在此處處理錯誤
	                console.error('Failed to login:', error);
	                Swal.fire({
						icon:'error',
						title:'帳號或密碼錯誤',
						
					})
	            }
	        });
	    }
	});
	
//	    loadMessages();
	
	//消息拿出顯示到前端
	function loadMessages(hotelId) {
	
	    $.ajax({
	        url: '/api/messages?hotelId= ' + hotelId, 
	        method: 'GET',
	        data:{
				hotelId:hotelId
			},
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
	    
	    
	    var senderElement = $('<span class="name"></span>').text(message.sender + ": " );
	    
	      if (message.sender === username) {
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
	
	
	
	
	
	// 連線建立後要處理的邏輯
	function onConnected() {
	    // 訂閱/topic/public
	    stompClient.subscribe('/topic/public', onMessageReceived);
	
	    // 發送訊息至/app/join
	    stompClient.send("/app/join", {}, JSON.stringify({
	        sender : username,
	        type : 'JOIN',
	        
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
	            sender : username,
	            content : messageInput.val(),
	            type : 'CHAT',
	            timestamp: new Date().toISOString(), // adding current time as timestamp
	            hotelId: hotelId
	        };
	        stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
	        messageInput.val('');
	    }
	    event.preventDefault();
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
	
	 function removePopup (event) {
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
	   if($.contains(this.parentNode, this)) {
	        $(this).remove();
	    }
	});
	
	// 監聽回覆輸入框的 'keydown' 事件
	$(document).on('keydown', '#replyBox', function(e) {
	    if(e.key === 'Enter') {
	        // 當 Enter 鍵被按下時，獲取輸入的回覆
	        var reply = $(this).val().trim();
	        if(reply !== "") {
	            // 如果回覆不為空，則將它添加到相應的訊息後面
	            var replyNameElement = $('<span class="replyName"></span>').text(username + ': '); //回覆人的名稱
	            var replyMessageElement = $('<p class="replyMessage"></p>').text(reply); //回覆的訊息
	            var replyElement = $('<div class="reply"></div>').append(replyNameElement, replyMessageElement);
	            $(this).parent().append(replyElement);
	            // 這裡你可能需要添加一些代碼來將回覆保存到數據庫或其他存儲位置
	             if($.contains(this.parentNode, '#replyBox')) {
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
	
	        // 发送消息至/app/chat
	        stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
	    }
	
	    nameInput.on('focus', removePopup);
	    messageForm.on('submit', sendMessage);
	    })
