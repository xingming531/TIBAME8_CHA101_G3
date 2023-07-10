$(function(){	//文檔載入後的jquery寫法(同$(document).ready(function(){}))
	
	
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
	
	
	
	var data ; //存回應資料的長度用(res.length)
	var currentPage = 1; //起始頁
	
	//清除頁面資料,刷新用
	function clearPage() {
	    $("tbody").empty();
	    $("#loading").hide();
	  }
	
	
	
	$.ajax({
		  url: "/ownerManage/getAll",
		  type: "GET",
		  data: { action: "getAll" },
		  dataType: "JSON",
		  success: function(res) {
		    console.log(res);

		    data = res.length;
		    var page = Math.ceil(data / 5);
		    
		    //查看圖片
		    $("tbody").on("click", ".pic", function() {
    			const index = $(this).closest("tr").index(); //找到最接近父元素的tr索引值,與資料庫一樣是從0開始
    			const imageBase64 = res[index].imageBase64;
    			Swal.fire({
    				  title: '特定寵物業許可證',
    				  width: '80%',
    				  background: '#fff',
    				  html: `<img src="data:image/octet-stream;base64,${imageBase64}">`
    				});
    		});
		    
		  
		    
		    //先呼叫一次,這樣一進畫面就有第一頁資料
		    displayPage(currentPage,res);
		    
		    
//===================================================依關鍵字搜尋↓===========================================================//
		    $(".search_btn").on("click",function(){
	    	
				var number =  parseInt($(".owner_search").val());
		    	var keyword = $(".owner_search").val();
		    	
		    	var error = true; //用來處理空值情形
		    	if(keyword === ""){
		    		 currentPage = 1; //  currentPage 設為 1
					   displayPage(currentPage, res);
					    $(".pagination").empty();
					    for (let page1 = 1; page1 <= page; page1++) {
					      $(".pagination").append(`<li class="page-item"><a class="page-link" href="#">${page1}</a></li>`);
					      $(".pagination .page-link:first").css("background-color", "#f8c544");
					    }
					 
		    	
		    	
		    	}else if (!isNaN(number)) {		
		    		clearPage();// 先清空頁面
			    	
	 		    	appendTableData(res);// 再呼叫創建td的函式,此時會一次顯示全部資料在頁面上
				// 遍歷每個tr
				 $("tbody tr").each(function() {
					var tdNumber = parseInt($(this).find("td").text());
					//如果輸入的和tr有對上就顯示該筆tr,沒有對上的全部隱藏
					if(number === tdNumber){
						$(".pagination").empty();
						$(this).show();
						error = false;
					}else{
						$(this).hide();
					}
				});
				if(error){
					  swal.fire({
							title:"查無此業主",
							html:`<img src = "./img/cry.gif">`
						})
				}
				

				
			}else if(keyword != null){
				 swal.fire({
			            title: 'Loading',
			            html:`<img src = "./img/appleCat.gif" style="width:200px; height:200px;" >`,
			            showConfirmButton: false,
						allowOutsideClick: true,
			            onBeforeOpen: () => {
			            swal.showLoading()
			            }
			        });
		    	$.ajax({
		    		url: "/ownerManage/getAll",
		    		type: "GET",
		    		data: { keyword:keyword },
		    		dataType: "JSON",
		    		success: function(res){		    			
		    			swal.close();	    			
		    			console.log(res);		    			
		    			clearPage();
		    			if(res.length !== 0){
		    			for (let i = 0; i < res.length; i++) {
		    		        $("tbody").append(`<tr ${i}>
		    		          <td>${res[i].hotelId}</td>
		    		          <td>${res[i].ownerName}</td>
		    		          <td>${res[i].hotelName}</td>
		    		          <td><button class="owner btn btn-primary" type="button" style="background: #f8c544;">📝</button></td>
		    		          <td><button class="hotel btn btn-primary" type="button" style="background: #f8c544;">🏨</button></td>
		    		          <td><button class="pic btn btn-primary" type="button" style="background: #f8c544;">🖼️</button></td>
		    		          <td>
		    		            <select class="ownerAccess" style="width:70px;">
		    		              <optgroup label="權限更改"> 
		    		                ${res[i].ownerAccess === 'NORMAL' ? `<option value="0" selected>正常</option>` : `<option value="0">正常</option>`}
		    		                ${res[i].ownerAccess === 'SUSPENDED' ? `<option value="1" selected>停權</option>` : `<option value="1">停權</option>`}
		    		                ${res[i].ownerAccess === 'NOT_VERIFIED' ? `<option value="2" selected>未審核</option>` : `<option value="2">未審核</option>`}
		    		              </optgroup>
		    		            </select>
		    		          </td>
		    		        </tr>`);
		    			}
		    			currentPage = 1;
						displayPage(currentPage,res);
						var filteredData = res.length;
				        var filteredPage = Math.ceil(filteredData / 5);
				        $(".pagination").empty(); // 清空頁籤
				        //依符合條件的資料顯示頁籤
				        for (let page1 = 1; page1 <= filteredPage; page1++) {
				          $(".pagination").append(`<li class="page-item"><a class="page-link" href="#">${page1}</a></li>`);
				          $(".pagination .page-link:first").css("background-color", "#f8c544");
				        }
						 $(".pagination").on("click", "li", function() {
						      currentPage = parseInt($(this).text());
						      displayPage(currentPage,res);
						    });
		    			}else if(res.length === 0){
		    				swal.fire({
								title:"搜尋不到包含此關鍵字之項目",
								html:`<img src = "./img/cry.gif" style="width:200px; height:200px;" ><br>跳轉中．．．`,
								showConfirmButton: false,
								allowOutsideClick: true
							});	
		    				 setTimeout(function() {
		    				        location.reload();
		    				    }, 1000); // Change this to the number of milliseconds you want to wait
		    				    return;		    				
		    			}
		    		}
		    		
		    	});
		    	
		    }	
		});	
		
		    $(".owner_search").keyup(function(event) {
		        if (event.keyCode === 13) { // 13 是 enter 鍵的鍵碼
		            $(".search_btn").click(); // 觸發搜尋按鈕的點擊事件
		        }
		    });
		  
		  
		    //離開搜尋欄位的時候,如果搜尋欄位為空值,顯示第一頁
		    $(".owner_search").on("blur",function(){
		   	 var input = $(".owner_search").val();
		   	 if(input == ""){
		   		currentPage = 1; //  currentPage 設為 1
			    displayPage(currentPage, res);
		   		//清除頁籤
			    $(".pagination").empty();
		   		//返回第一頁
			    for (let page1 = 1; page1 <= page; page1++) {
			      $(".pagination").append(`<li class="page-item"><a class="page-link" href="#">${page1}</a></li>`);
			      $(".pagination .page-link:first").css("background-color", "#f8c544");
			    }
		   	 }

		   });
//===================================================依關鍵字搜尋↑===========================================================//
		    
		    
//====================================================依權限搜尋↓===========================================================//	
			$(".Access_btn").on("click",function(event){	
				
				event.preventDefault();
				  
				var filteredRes = []; //存符合條件的資料
				clearPage();//清除畫面		
				appendTableData(res); //顯示所有tr
				if($(".normal").is(":checked") && $(".stopAccess").is(":checked")){
					$(this).show();
					$(".isNone2").hide();				
					$("tbody tr").each(function(i){
						if($(this).find(".ownerAccess").val() !== ""){
							var add = parseInt($(this).find("td:first").text());
							if(res[i].hotelId == add){
								 filteredRes.push(res[i]);
							}						
						}
					});
					//顯示畫面,只顯示符合條件的資料
					//從第一頁開始
					currentPage = 1;
					displayPage(currentPage,filteredRes);
					var filteredData = filteredRes.length;
			        var filteredPage = Math.ceil(filteredData / 5);
			        $(".pagination").empty(); // 清空頁籤
			        //依符合條件的資料顯示頁籤
			        for (let page1 = 1; page1 <= filteredPage; page1++) {
			          $(".pagination").append(`<li class="page-item"><a class="page-link" href="#">${page1}</a></li>`);
			          $(".pagination .page-link:first").css("background-color", "#f8c544");
			        }
					 $(".pagination").on("click", "li", function() {
					      currentPage = parseInt($(this).text());
					      displayPage(currentPage,filteredRes);
					    });
				}
				else if ($(".normal").is(":checked")){ //如果選中正常的checkbox
					$(this).show();
					$(".isNone2").hide();
					$("tbody tr").each(function(i){
						if($(this).find(".ownerAccess").val() == "0"){	
							var add = parseInt($(this).find("td:first").text());
							if(res[i].hotelId == add){
								 filteredRes.push(res[i]);
							}						
						}
					});
					currentPage = 1;
					displayPage(currentPage,filteredRes);
					var filteredData = filteredRes.length;
			        var filteredPage = Math.ceil(filteredData / 5);
			        $(".pagination").empty(); // 清空頁籤
			        for (let page1 = 1; page1 <= filteredPage; page1++) {
			          $(".pagination").append(`<li class="page-item"><a class="page-link" href="#">${page1}</a></li>`);
			          $(".pagination .page-link:first").css("background-color", "#f8c544");
			        }
					 $(".pagination").on("click", "li", function() {
					      currentPage = parseInt($(this).text());
					      displayPage(currentPage,filteredRes);
					    });					
				}else if($(".stopAccess").is(":checked")){  //如果選中停權的checkbox
					$(this).show();
					$(".isNone2").hide();
					$("tbody tr").each(function(i){
						if($(this).find(".ownerAccess").val() == "1"){
							var add = parseInt($(this).find("td:first").text());
							if(res[i].hotelId == add){
								 filteredRes.push(res[i]);
							}						
						}
					});	
					currentPage = 1;
					displayPage(currentPage,filteredRes);	
					var filteredData = filteredRes.length;
			        var filteredPage = Math.ceil(filteredData / 5);
			        $(".pagination").empty(); // 清空頁籤
			        for (let page1 = 1; page1 <= filteredPage; page1++) {
			          $(".pagination").append(`<li class="page-item"><a class="page-link" href="#">${page1}</a></li>`);
			          $(".pagination .page-link:first").css("background-color", "#f8c544");
			        }
					 $(".pagination").on("click", "li", function() {
					      currentPage = parseInt($(this).text());
					      displayPage(currentPage,filteredRes);
					    });
				}else if(!$(".normal").is(":checked") && !$(".stopAccess").is(":checked")){
					swal.fire({
						title:"未選擇搜尋項目",
						html:`<img src = "./img/applecat.gif" style="width:300px;">`,					
					})
					event.preventDefault();
				    clearPage(); 
				    currentPage = 1;
				    displayPage(currentPage, res); 
				    var totalPages = Math.ceil(res.length / 5);
				    $(".pagination").empty(); // 清空頁籤
				    for (let page = 1; page <= totalPages; page++) {
				        $(".pagination").append(`<li class="page-item"><a class="page-link" href="#">${page}</a></li>`);
				        $(".pagination .page-link:first").css("background-color", "#f8c544");
				    }			   
				    $(".pagination").on("click", "li", function() {
				        currentPage = parseInt($(this).text());
				        displayPage(currentPage, res);
				    });			   
				}		
			});
 			

//========================================================依權限搜尋↑==========================================================//

		    //動態生成頁籤
		    for (let page1 = 1; page1 <= page; page1++) {
		      $(".pagination").append(`<li class="page-item"><a class="page-link" href="#">${page1}</a></li>`);
		      $(".pagination .page-link:first").css("background-color", "#f8c544");
		    }
		    
		    $(".pagination").on("click", ".page-link", function() {
		        // 移除所有頁籤的背景色
		        $(".pagination .page-link").css("background-color", "");	      
		        // 將點擊的頁籤設置背景色
		        $(this).css("background-color", "#f8c544"); // 將顏色更改為您需要的顏色
		   
		    });
		    

		    // 頁籤點擊事件
		    $(".pagination").on("click", "li", function() {
		      currentPage = parseInt($(this).text());
		      displayPage(currentPage,res);
		    });
		  }
		});

	
	
	//分頁函式:一頁僅顯示五筆資料
	function displayPage(currentPage,data) {
      	var start = (currentPage - 1) * 5;
      	var end = start + 5;
      	var res = data.slice(start, end);
      clearPage();//刷新頁面   
      appendTableData(res);
      }
	
	//動態生成tr&td函式 
      function appendTableData(res) {
      for (let i = 0; i < res.length; i++) {
        $("tbody").append(`<tr ${i}>
          <td>${res[i].hotelId}</td>
          <td>${res[i].ownerName}</td>
          <td>${res[i].hotelName}</td>
          <td><button class="owner btn btn-primary" type="button" style="background: #f8c544;">📝</button></td>
          <td><button class="hotel btn btn-primary" type="button" style="background: #f8c544;">🏨</button></td>
          <td><button class="pic btn btn-primary" type="button" style="background: #f8c544;">🖼️</button></td>
          <td>
            <select class="ownerAccess" style="width:70px;">
              <optgroup label="權限更改"> 
                ${res[i].ownerAccess === "NORMAL" ? `<option value="0" selected>正常</option>` : `<option value="0">正常</option>`}
                ${res[i].ownerAccess === "SUSPENDED" ? `<option value="1" selected>停權</option>` : `<option value="1">停權</option>`}
                ${res[i].ownerAccess === "NOT_VERIFIED" ? `<option value="2" selected>未審核</option>` : `<option value="2">未審核</option>`}
              </optgroup>
            </select>
          </td>
        </tr>`);
        
        
        
      

		    $("tbody").on("click", ".owner", function() {
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
		    
		    
		    $("tbody").on("click", ".hotel", function() {
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
      	}
      }

      $("tbody").on("mousedown", ".ownerAccess", function() {
    	  var selectBox = $(this);
    	  var originalValue = selectBox.val();
    	  selectBox.one('change', function() { 
    	  Swal.fire({	    
    	    html:`<img src= "./img/update.gif" style="with 300px; height:300px;">`,
    	    showCancelButton: true,
    	    confirmButtonText: "確定",
    	    cancelButtonText: "取消"
    	    
    	  })
    	  .then((result) => { //返回的結果
    	    if (result.isConfirmed) {
    	      console.log("更改");
    	      changeAccess(this); //當前要更改的元素呼叫函式
    	    } else {
    	    	selectBox.val(originalValue); 
    	    }
    	  });
    	});
    });

    	// 修改權限
    	function changeAccess(e) {
    	  var selectedValue = $(e).val();
    	  var $row = $(e).closest("tr"); // 抓到當前的行數
    	  var hotelId = $row.find("td:first").text();
    	  var hotelName = $row.find("td:eq(1)").text(); //:eq(1)1為索引值,從0開始 ,取第二個td的值

    	  $.ajax({
    	    url: "/ownerManage/update",
    	    type: "POST",
    	    data: {
    	      action: "update",
    	      ownerAccess: selectedValue,
    	      hotelId: hotelId
    	    },
    	    dataType: "JSON",
    	    success: function(res) {
    	      console.log("更新成功");
    	      console.log(res);   	  
    	      Swal.fire({
    	    	  iconHtml:`<img src= "./img/happy.gif" style="with 200px; height:200px;">`,
    	    	  title:"業主編號 : " + hotelId +"<br>" + "業主名稱 : "+  hotelName +"<br>" + "權限已完成更新"
    	    	  });
    	    },
    	    error: function(xhr, status, error) {
    	      console.log("更新失敗");
    	      console.log(xhr.responseText);
    	    }
    	  });
    	}
});