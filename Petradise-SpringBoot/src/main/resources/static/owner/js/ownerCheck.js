$(function() {
	
	//reload按鈕
	$(".reload").hover(
			function(){
		//移入
		$(this).find('i').addClass('fa-spin');
			},
			function(){
				$(this).find('i').removeClass('fa-spin');
			});
	$(".reload").on("click",function(){
		location.reload();
	});
	//重新整理畫面
	function clearPage(){
		location.reload();
	}
	var data ; //存回應資料的長度用(res.length)
	var currentPage = 1; //起始頁
	  $.ajax({
	    url: "/ownerCheck/getAll",
	    type: "GET",
// 	    data: { hotelStatus: "0" }, //查詢未審核的資料
	    dataType: "JSON",
	    success: function(res) {
	      console.log(res);
	      
	     if(res.length === 0){
	    	 $("tbody").append(`
	    			 <tr><td>無資料</td></tr>	 
	     `)
	     $("#loading").hide();
	     };
	     
	     //優化↓
	     let tempDiv =$("<div>");
	      for (let i = 0; i < res.length; i++) {
			  $("#loading").hide();
	    	  tempDiv.append(`
	    			  <tr>
	   	            <td>${res[i].hotelId}</td>
	   	            <td>${res[i].ownerName}</td>
	   	            <td>${res[i].hotelName}</td>
	   	            <td><button class="owner btn btn-primary" type="button" style="background: #f8c544;">📝</button></td>
	   	            <td><button class="hotel btn btn-primary" type="button" style="background: #f8c544;">🏨</button></td>
	   	            <td><button class="pic btn btn-primary" type="button" style="background: #f8c544;">🖼️</button></td>
	   	            <td>
	   	              <select class="hotelStatus" style="width:90px;">
	   	                <optgroup label="權限更改"> 
	   	                  ${res[i].hotelStatus === '0' ? `<option value="0" selected>審核中</option>` : `<option value="0">審核中</option>`}
	   	                  ${res[i].hotelStatus === '1' ? `<option value="1" selected>未通過</option>` : `<option value="1">未通過</option>`}
	   	                  ${res[i].hotelStatus === '2' ? `<option value="2" selected>通過</option>` : `<option value="2">通過</option>`}

	   	                </optgroup>
	   	              </select>
	   	            </td>
	   	          </tr>
	   	          	`);
	    	  let tr = tempDiv.children().last();
	    	  (function(i){
	    	  tr.find(".owner").click(function() {
	    		  var click = parseInt($(this).closest("tr").find("td:first").text());

			      var html = "";
			      if (res[i].hotelId == click) {
			        html += "<table>"
			       	html += "<tr>" + "<td style='text-align: left;'>" + "業主名稱 : " + res[i].ownerName + "</td>" + "<tr>"
			        html += "<tr>" + "<td style='text-align: left;'>" + "會員帳號 : " + res[i].ownerAccount + "</td>" + "<tr>"
			        html += "<tr>" + "<td style='text-align: left;'>" + "聯絡電話 : " + res[i].ownerPhone + "</td>" + "<tr>"
			        html += "<tr>" + "<td style='text-align: left;'>" + "電子信箱 : " + res[i].ownerEmail + "</td>" + "<tr>"
			        html += "<tr>" + "<td style='text-align: left;'>" + "身分證字號 : " + res[i].ownerId + "</td>" + "<tr>"
			        html += "<tr>" + "<td style='text-align: left;'>" + "收款帳號 : " + res[i].ownerBank + "</td>" + "<tr>"
			        html += "</table>"
			        swal.fire({
			          html: html
			        });
			      }
			    }); 
	    	  tr.find(".hotel").click(function() {
	    		  var click = parseInt($(this).closest("tr").find("td:first").text());
			      var html = "";
			      if (res[i].hotelId == click) {
			        html += "<table>"
			        html += "<tr>" + "<td style='text-align: left;'>" + "旅館名稱 : " + res[i].hotelName + "</td>" + "<tr>"
			        html += "<tr>" + "<td style='text-align: left;'>" + "旅館地址 : " + res[i].hotelAddress + "</td>" + "<tr>"
			        html += "<tr>" + "<td style='text-align: left;'>" + "許可證號 : " + res[i].hotelLicId + "</td>" + "<tr>"     
			        html += "</table>"
			        swal.fire({
			          html: html
			        });
			      }
			    });
	    	  
	    	  tr.find(".pic").click(function() {
	    		  const index = $(this).closest("tr").index(); //找到最接近父元素的tr索引值,與資料庫一樣是從0開始
	     			const imageBase64 = res[index].imageBase64;
	     			Swal.fire({
	     				  title: '特定寵物業許可證',
	     				  width: '80%',
	     				  background: '#fff',
	     				  html: `<img src="data:image/octet-stream;base64,${imageBase64}">`
	     				});
	     		});
	    	  })(i);//呼叫匿名含式給他i值
	    	  $("tbody").append(tempDiv.children());
	    	//優化↑

	      }
	      
	    },
	    error: function(xhr, status, error) {
	      console.log("請求失敗");
	      console.log(xhr.responseText);
	    }
	  });
	
		    	  
		    	  $("tbody").on("mousedown",".hotelStatus",function(){   //mousedown事件可以在確認更改前先保存原本的值
    				var selectBox = $(this);
    				var originalValue = selectBox.val();

    					selectBox.one('change', function() {  //使用one方法一次性的change事件
        				Swal.fire({     
            				html:`<img src= "./img/update.gif" style="with 300px; height:300px;">`,
            				showCancelButton: true,
            				confirmButtonText: "確定",
            				cancelButtonText: "取消"
        				})
        				.then((result) => {
            				if(result.isConfirmed){
                			changeStatus(this);
            				}else if(result.isDismissed){
                			selectBox.val(originalValue);
            		}
        		})
    		});

		    	  
	function changeStatus(e){ 	  
	  var selectedValue = $(e).val();
		console.log(selectedValue);
	  var $row = $(e).closest("tr"); // 抓到當前的行數
	  var hotelId = $row.find("td:first").text();
	  var hotelName = $row.find("td:eq(1)").text(); //:eq(1)1為索引值,從0開始 ,取第二個td的值

	  $.ajax({
	    url: "/ownerCheck/update",
	    type: "POST",
	    data: JSON.stringify({
	      action: "update",
	      hotelId: hotelId,
	      hotelStatus: selectedValue,      
	    }),
	    contentType: "application/json",
	    dataType: "text",
	    success: function(res) {
	      console.log("更新成功");
	      console.log(res);
	      if(parseInt(selectedValue) === 2){
	      Swal.fire({
	    	  iconHtml:`<img src= "./img/happy.gif" style="with 200px; height:200px;">`,
	    	  title:"業主編號 : " + hotelId +"<br>" + "業主名稱 : "+  hotelName +"<br>" + "已成為合作業主!"
	    	  }).then((result)=>{
	    		  if(result.isConfirmed){
	 
	    		  }else{
	    			
	    		  }
	    	  });
	     	
	      }else if(parseInt(selectedValue) === 1){
	    	  Swal.fire({
		    	  iconHtml:`<img src= "./img/cry.gif" style="with 100px; height:100px;">`,
		    	  title:"業主編號 : " + hotelId +"<br>" + "業主名稱 : "+  hotelName +"<br>" + "已刪除該筆資料"
		    	  }).then((result)=>{
		    		  if(result.isConfirmed){
		    	
		    		  }else {
		    			
		    		  }
		    	  });
	      }
	    },
	    error: function(xhr, status, error) {
	      console.log("更新失敗");
	      console.log(xhr.responseText);
	    }
	  });
	 }	   
	});

});