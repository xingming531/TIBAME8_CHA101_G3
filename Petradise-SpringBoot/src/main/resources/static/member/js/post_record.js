(() => {
    $(document).ready(function () {
        guardIsSignedIn();
    	btnToPost();
    	axiosPostRecord();
    })
    
    
    
	function btnToPost(){
		const btn_create = document.querySelector('.btn-create');
	    btn_create.addEventListener('click', () => {
	        const targetPageUrl = '/lostPet/lost_post.html';
	        window.location.href = targetPageUrl;
	    });
	}
    
    function axiosPostRecord (){
	    const btn_delete = document.querySelector('.btn-delete');
	    let conta = document.querySelector(".conta");
	    const moreContentLink = document.querySelector(".btn-more");
	    const editLink = document.querySelector(".btn-edit");
	    const mem_id = getMemberId();
	    let deleteId ;
		axios.get("/LostPetArticle/memId=" + mem_id)
	    .then(function (response) {
	      console.log(response.data);
	      let data = response.data;
	
	      
	      let html = '';
	
	      if (Array.isArray(data)) {
	    	  let parentElement = document.querySelector(".article");
	    	  parentElement.remove();
	    	  
	        
	    	  data.forEach(function (item) {
		    	  deleteId = item.articleId;
					
	    		  html += `
		        	<div class="article article${item.articleId}">
		              <div class="article-title">
		                <h5>${item.title}</h5>
		              </div>
		              <div class="info">
		                <h5>${item.text}</h5>
		                <a href="/lostPet/lost_post_edit.html?id=${item.articleId}" class="btn-edit">修改</a>
		                <button class="btn-delete" id = ${deleteId} >刪除</button>
		                <a href="/lostPet/forum.html?id=${item.articleId}" class="btn-more">更多內容</a>
		              </div>
		            </div>
		            `;
		    	 
	    	  });
	      } 
	      conta.innerHTML = html;
	      
	      
	      const deleteButtons = document.querySelectorAll('.btn-delete');
	      deleteButtons.forEach(function(button) {
	          button.addEventListener('click', function() {
	              deleteId = button.getAttribute('id');
	              console.log(deleteId);
	              $.ajax({
	                  url: '/LostPetArticle/delete/id=' + deleteId,
	                  type: 'PUT',
	                  success: function(response) {
	                      console.log('Article deleted:', response);
	                      let className = '.article'+deleteId ;
	                      let parentElement = document.querySelector(className);
	                      
	                      parentElement.remove();
	                  },
	                  error: function(xhr, status, error) {
	                      console.log('Error:', error);
	                  }
	              });
	          });
	      });
	    })
	    .catch(function (error) {
	      console.log(error);
	    });
	}
    
})();