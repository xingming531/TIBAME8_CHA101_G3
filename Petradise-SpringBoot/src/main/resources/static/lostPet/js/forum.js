$(document).ready(function () {
	   		const urlParams = new URLSearchParams(window.location.search);
	        const id = parseInt(urlParams.get('id'), 10);
			axiosToGetArticle();
			response();
			toLostPage();
})
		
        function toLostPage(){
        	const btn_redi = document.querySelector('.button-redi');
        	btn_redi.addEventListener('click', () => {
    	        const targetPageUrl = '/lostPet/lost.html';
    	        window.location.href = targetPageUrl;
    	    });
        }
        
    	function response(){
    		document.querySelector('.button-res').addEventListener("click", function () {
    			guardIsSignedIn();
        	    Swal.fire({
        	        title: '回覆文章ㄅ',
        	        input: 'textarea',
        	        allowOutsideClick: true,
        	        backdrop: true,
        	    }).then(function (result) {
        	        if (result.value != null) {
        	            let text = result.value.trim(); // 檢查前先執行 trim()
        	            if (text !== "") {
        	                let jsonData = {
        	                    "articleId": id,
        	                    "response": {
        	                        "memId": getMemberId(),
        	                        "responseContent": text,
        	                    }
        	                };
        	                console.log(jsonData);
        	                $.ajax({
        	                    url: "/LostPetRespose/add",
        	                    method: "POST",
        	                    contentType: "application/json",
        	                    data: JSON.stringify(jsonData),
        	                }).then(function(response) {
        	                    console.log(response);
        	                    window.location.reload();
        	                }).catch(function(error) {
        	                    console.error(error);
        	                });
        	            } else {
        	                Swal.fire({
        	                    title: 'Error',
        	                    text: 'Please enter something',
        	                    icon: 'error'
        	                });
        	            }
        	        }
        	    });
        	});
    	}
    	


       
        
        function axiosToGetArticle(){
        	 let conta = document.querySelector(".bbs-screen");
             let comment = document.querySelector(".comment");
             
        	axios.get("/LostPetArticle/id=" + id)
            .then(function(response) {
              let data = response.data;
            
              console.log(data);
    			
              if (data["articleStatus"] == undefined) {
    		
            	  window.location.assign("/lostPet/forum_deleted.html");
    		  } 
              const originalDate = data.lostDate;
    		  const parsedDate = new Date(originalDate);
    		  const formattedDate = parsedDate.toISOString().replace("T", " ").slice(0, 16);
    		
              data["chipNum"] = data["chipNum"] || '無';
              data["species"] = data["species"] || '無';
    			

              const articleHTML = `
                <div class="article-metaline"><span class="article-meta-tag">作者</span><span class="article-meta-value">${data.memId}</span></div>
                <div class="article-metaline"><span class="article-meta-tag">標題</span><span class="article-meta-value">${data.title}</span></div>
                <div class="article-metaline"><span class="article-meta-tag">時間</span><span class="article-meta-value">${data.update}</span></div>
                <div>
                    <label for="lost_place">遺失地點：${data.lostPlace}</label>
                </div>
                <div>
                    <label for="chip_num">晶片號碼：${data.chipNum}</label>
                </div>
                <div>
                    <label for="species">品種：${data.species}</label>
                </div>
                <div>
                    <label for="color">毛色：${data.color}</label>
                </div>
                <div>
                    <label for="datetime">遺失時間：${formattedDate}</label>
                </div>
                <div>
                    <label for="feature">寵物特徵：${data.feature}</label>
                </div>
                <div>
                    <label for="contact_phone">聯絡電話：${data.contactPhone}</label>
                </div>
                <div>
                    <label for="pic">動物圖片：</label>
                    <div class="animal-pic">
                    </div>
                </div>
                <div>
                    <label for="text">文章補充內容：${data.text}</label>
                </div>
                <div>
                    <span>----------------------------------------------------------------------------------------------------</span>
                </div>
              `;

              conta.innerHTML = articleHTML;

              let pic = document.querySelector(".animal-pic");
              if (data.lostPetPic && data.lostPetPic.length > 0) {
                const picHTML = data.lostPetPic.map(pic => `<img class="pic" src="data:image/*;base64,${pic.lostPetPic}">`);
                pic.innerHTML = picHTML;
              } else {
                const defaultPicHTML = `<img class="pic" src="https://www.mindvan.com/dbp/files/cooljobz/cooljobz.com_2111021450.628.jpg">`;
                pic.innerHTML = defaultPicHTML;
              }

              if (data.lostPetResponse && data.lostPetResponse.length > 0) {
            	  const commentHTML = data.lostPetResponse.map((response, index) => `
            	    <div>
            	      <span class="an">${index + 1}F</span>
            	      <span class="f3 push-content">：${response.responseContent}</span>
            	      <span class="push-ipdatetime">${response.responseTime}</span>
            	    </div>
            	    <br>
            	  `).join('');

            	  comment.innerHTML = commentHTML;
            	}
            })
            .catch((error) => console.log(error));
        }