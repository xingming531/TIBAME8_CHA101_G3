$(document).ready(function() {
		
		
		
		
		const ownerName = getHotelOwnerName();
		if (ownerName) {
			$('#owner-name').text(ownerName);
		}
	});
    $(function () {
		const hotelId = getHotelOwnerId();
   
    //====================================查詢使用者資料============================================//
    	$.ajax({
    		  url: `/ownerAccount/${hotelId}`,
    		  type: 'GET',
    		  contentType: "application/json",
    		  success: function(res) {
    		    console.log(res);
    		    
    		    $("#owner_name").val(res.ownerName);
    		    $("#owner_id").val(res.ownerId);
    		    $("#owner_email").val(res.ownerEmail);
    		    $("#owner_password").val(res.ownerPassword);
    		    $("#owner_phone").val(res.ownerPhone);
    		    $("#owner_bank").val(res.ownerBank);
    		    $("#hotel_name").val(res.hotelName);
    		    $("#hotel_address").val(res.hotelAddress);
    		    $("#hotel_address").val(res.hotelAddress);
    		    var hotelLicId = res.hotelLicId;
    		    console.log(hotelLicId);
    		    $('#hotel_lic').text($('#hotel_lic').text()+hotelLicId);
    		    
    		    const hotelLicPic = res.hotelLicPic;

    		    $('.hotelLicPic_btn').on("click",function(){
    		    	Swal.fire({
    		    		title:"許可證",
    		    		width:"600px",
    		    		html:`<img src="data:image/octet-stream;base64,${hotelLicPic}" style="width:500px">`
    		    	});
    		    });
    		    
    		  },
    		  error: function(xhr, status, error) {
    		   
    		  }
    		});

    	//====================================查詢使用者資料============================================//    	

    
    
        // 讓輸入框變為不可修改
        $('.form-control').prop('disabled', true);
        
        
      //====================================修改個資============================================//
        
        //修改用function , 因為有兩個部分都會用到
        function updateOwnerAccount(data) {
			const hotelId = getHotelOwnerId();
            $.ajax({
                url: `/ownerAccount/${hotelId}`,
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (res) {
                    console.log("更新成功");
                },
                error: function (err) {
                    console.log("更新失敗");
                }
            });
        }
        
        // 編輯
        $(".update_btn").on("click",function(){
            $(".form-control").prop("disabled",false);
            $(".save_btn").css("display","inline-block");
        });
	
        //儲存
        $(".save_btn").on("click",function(){
        	Swal.fire({
        		title:"是否儲存資料?",
        		html:`<img src= "./img/update.gif" style="with 300px; height:300px;">`,
        		showCancelButton: true,
        	    confirmButtonText: "確定",
        	    cancelButtonText: "取消",		
        	})
        	.then(result => {
        		if (result.isConfirmed) {	
            $('.form-control').prop('disabled',true);
        		
            //名字驗證
            var newOwnerName = $("#owner_name").val();           
      	  	var nameRegex = /^[\u4E00-\u9FFFㄅ-ㄩㄚ-ㄦA-Za-z\s]{2,50}$/; 	  
      	  	if (!nameRegex.test(newOwnerName)) {
      	    	Swal.fire({
      	      		allowOutsideClick: false,
      	      		icon: 'error',
      	      		title: '業主名稱格式有誤',
      	    	});
      	    	$('.form-control').prop('disabled',false);
      	    	return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
      	  	}
            
            
            //信箱驗證
           	var newOwnerEmail = $("#owner_email").val();
           	var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        	if (!emailRegex.test(newOwnerEmail)) {
        	  Swal.fire({
        	    allowOutsideClick: false,
        	    icon: 'error',
        	    title: '信箱格式有誤',
        	  });
        	  $('.form-control').prop('disabled',false);
        	  return;
        	}
            //電話驗證      
           	var newOwnerPhone = $("#owner_phone").val();
           	var phoneRegex = /^(09\d{8}|02\d{8}|0[3-8]\d{7,10})$/;
        	if (!phoneRegex.test(newOwnerPhone)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '手機/市話格式有誤',
        	    });
        	    $('.form-control').prop('disabled',false);
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	//收款帳戶驗證
           	var newOwnerBank = $("#owner_bank").val();
           	var bankRegex = /^\d{8,}$/;
        	if (!bankRegex.test(newOwnerBank)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '收款帳戶格式有誤',
        	    });
        	    $('.form-control').prop('disabled',false);
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	
            //驗證旅館名稱    	
            var newHotelName = $("#hotel_name").val();
            var hotelNameRegex = /^.{1,}$/;
            if (!hotelNameRegex.test(newHotelName)) {
              // 驗證不通過，執行相應的處理邏輯
              Swal.fire({
                allowOutsideClick: false,
                icon: 'error',
                title: '寵物旅館名稱格式有誤',
              });
              $('.form-control').prop('disabled',false);
              return;
            }
            
            
            var newHotelAddress = $("#hotel_address").val();
            var addressRegex = /^[\u4E00-\u9FFF0-9\u3105-\u3129\-]+$/;
        	if (!addressRegex.test(newHotelAddress)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '旅館地址格式有誤',
        	    });
        	    $('.form-control').prop('disabled',false);
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        		}
        	  
            var data = {
                    ownerName: newOwnerName,
                    ownerEmail: newOwnerEmail,
                    ownerPhone: newOwnerPhone,
                    ownerBank: newOwnerBank,
                    hotelName: newHotelName,
                    hotelAddress: newHotelAddress,
                    hotelLicId: $("#hotel_lic").text(),
                    ownerPassword: $("#owner_password").val(),
                    
                    
                	};         
            		updateOwnerAccount(data);
        	 	}else{
        	 		return;
        	 	}
        		Swal.fire({
        			html:`<img src= "./img/dancingCat.gif" style="with 300px; height:300px;">`,
        			title:"資料已更新",
        			confirmButtonText: "確定"
        			
        		})
        		$(".save_btn").css("display","none");
        		
            }); 
        });
            
            
       
    
        $('#contact_form').bootstrapValidator({


            fields: {
                owner_name: {
                    validators: {
                        stringLength: {
                            min: 2,
                            message: '請填入姓氏與姓名至少二字元'
                        },
                        notEmpty: {
                            message: '請填入名字'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FFFㄅ-ㄩㄚ-ㄦA-Za-z\s]+$/,
                            message: '此欄位不得有符號及數字或使用空格區隔'
                        },
                    }
                },

                owner_id: {
                    validators: {
                        notEmpty: {
                            message: '請填入身分證字號'
                        },
                        stringLength: {
                            min: 10,
                            message: '長度不符'
                        },
                        regexp: {
                            regexp: /^[A-Z]{1}[1-2]{1}\d{8}$/,
                            message: '身分證格式不正確'
                        }
                    }
                },
                owner_email: {
                    validators: {
                        notEmpty: {
                            message: '請填入信箱'
                        },
                        emailAddress: {
                            message: '請輸入有效的信箱'
                        }
                    }
                },
                owner_phone: {
                    validators: {
                        notEmpty: {
                            message: '請填入連絡電話'
                        },
                        regexp: {
                            regexp: /^(09\d{8}|02\d{8}|0[3-8]\d{7,10})$/,
                            message: '長度不符'
                        },
                    }
                },
                owner_bank: {
                	validators: {
                        stringLength: {
                            min: 8,
                            message: '收款帳戶長度不符'
                        },
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        regexp: {
                            regexp: /^\d+$/,
                            message: '銀行帳號格式異常'
                        }
                    }
                },
                hotel_name: {
                    validators: {
                        stringLength: {
                            min: 1,
                            message: '此欄位不得空白'
                        },
                        notEmpty: {
                            message: '此欄位不得空白'
                        }
                    }
                },
                hotel_address: {
                    validators: {
                        stringLength: {
                            min: 1,
                            message: ' '
                        },
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FFF0-9\u3105-\u3129\-]+$/,
                            message: '地址格式不正確，僅能包含中文、數字和（-）符號'
                        }
                    }
                },

                hotel_lic_id: {
                    validators: {
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9 -]+$/,
                            message: '請確認輸入證號'
                        },
                        stringLength: {
                            min: 3,
                            message: "長度不符"
                        }
                    }
                },
            }
        });


        $(".change_password").on("click", function () {
            Swal.fire({
                title: "變更密碼",
                showCancelButton: true,
                confirmButtonText: '確定',
                cancelButtonText: '取消',
                html: `
                <div style="position: relative;"> 
                    <label style="width: 70px">新密碼</label>    
                         <input id ="new_password" name="new_password"  type="password" maxlength="20" style="width: 200px" placeholder="8~20字元數字、英文">
                         <i class="toggle-password-icon fas fa-eye" style="position: absolute; right: 50px; cursor:pointer"></i>
                            
                </div>
                <br>
                <div>
                    <label style="width: 70px">確認密碼</label>    
                         <input  id ="re_password" name="re_password"  type="password" maxlength="20" style="width: 200px" placeholder="請再輸入一次密碼">           
                </div>`

            }).then((result)=>{
                if(result.isConfirmed){
                	var newPassword = $("#new_password").val();
                	var passwordRegexp = /^[a-zA-Z0-9]{8,20}$/;
                	var rePassword = $("#re_password").val();
                	
                	if(passwordRegexp.test(newPassword)){
                		if(newPassword === rePassword){
                    		console.log("一樣");
                        	Swal.fire({
                        		iconHtml:`<img src= "./img/happy.gif" style="with 200px; height:200px;">`,
                            	title:"密碼變更成功",
                            	text:"請牢記您的新密碼"
                        	})
                        	 	var newOwnerName = $("#owner_name").val();
           						var newOwnerEmail = $("#owner_email").val();
           						var newOwnerPhone = $("#owner_phone").val();
           						var newOwnerBank = $("#owner_bank").val();
            					var newHotelName = $("#hotel_name").val();
            					var newHotelAddress = $("#hotel_address").val();
            					
                        		var data ={
                        			ownerName: newOwnerName,
                                    ownerEmail: newOwnerEmail,
                                    ownerPhone: newOwnerPhone,
                                    ownerBank: newOwnerBank,
                                    hotelName: newHotelName,
                                    hotelAddress: newHotelAddress,
                                    hotelLicId: $("#hotel_lic").text(),
                			ownerPassword:newPassword
                			};
                        	updateOwnerAccount(data);
                        	
                    	}else{
                    		Swal.fire({
                    			iconHtml:`<img src= "./img/cry.gif" style="with 150px; height:150px;">`,
                            	title:"密碼變更失敗",
                            	text:"兩次輸入的密碼不一致"
                        	})
                    	}
                		
                	}else{
                		Swal.fire({
                			icon:'error',
                        	title:"密碼不符格式",
                        	text:"密碼長度請介於8~20字符,僅能大小寫字母及數字"
                    	})
                	}
                	
                	
                	
                           	
                }
            });
            
        });
        
        
        
        
        
        
        $(document).on("click",".toggle-password-icon",function(){
            var passwordInput = $(this).closest("div").find("input");

            if(passwordInput.attr("type") === "password"){
                passwordInput.attr("type","text");
            }else{
                passwordInput.attr("type","password");
            }
        });

        

    });