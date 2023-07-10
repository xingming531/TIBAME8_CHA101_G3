$(function(){
	
	//有沒有驗證信箱
	var isEmailVerified = false;
	
	$(".emailCheck").on("click",function(){
		var ownerEmail = $("#owner_email").val();
		if(ownerEmail != ""){
		   $.ajax({
		        url: '/ownerSignUP/checkMail',
		        type: 'POST',
		        data: JSON.stringify({ownerEmail: ownerEmail}),
		        contentType: 'application/json',
		        success: function() {
		            swal.fire({
		            	title:"已寄信至您輸入的信箱",
		            	input: 'text',
		                inputPlaceholder: '請輸入信箱收到之驗證碼',
		                showCancelButton: true,
		                confirmButtonText: '確認',
		                cancelButtonText: '取消',
		                showLoaderOnConfirm: true, // ajax加載器
		                preConfirm: function(token){
		                	return new Promise(function(resolve, reject){
		                		$.ajax({
		                			url: "/ownerSignUP/verify-email",
		                			type: "POST",
		                			data: { token: token },
		                			success: function(res){
		                				resolve(res);
		                			},
		                			error: function() {
		                                reject('驗證郵件失敗');
		                            }
		                		});
		                	});
		                }
		            }).then(function(result) {
		            	if(result.isConfirmed){
		            	isEmailVerified = true;
		                Swal.fire({
		                    title: result.value,
		                    icon: 'success',
		                    showCancelButton: false,
		                    confirmButtonText: '確定'
		                });
		            	}else if(result.isDismissed){
		            		
		            	}
		            }).catch(function(error) {
		                Swal.fire({
		                    title: error,
		                    icon: 'error',
		                    showCancelButton: false,
		                    confirmButtonText: '確定'
		                });
		            });
		        },
		        
		        error: function() {
		            alert('發送失敗');
		        }
		   
		    });
		}else{
			swal.fire({
				title: "請先輸入信箱",
			})
		}
	});
	
	
	
	
	let allData = {};
	
	var previousFileName = null;
	$("#hotel_lic_pic").on("change", function () {
        var hotelLicFile = $(this)[0].files[0];
        var hotelLicName = $(this).prop("files")[0];
        //有選擇圖片 fileName=hotelLicName的name屬性,否則為null;
        var fileName = hotelLicName ? hotelLicName.name : previousFileName; 
       
         	//如果有上傳圖片,或是又再次觸發change事件但沒有選擇新圖片時
        	if (hotelLicFile || fileName !== previousFileName ) {
        	previousFileName = fileName ;
            var reader = new FileReader();
            reader.onload = function (e) {
            	var hotelLicBase64 = reader.result;
            	var base64Data = e.target.result.split(",")[1]; // 去除前缀部分
            	console.log(base64Data);
            	allData.imageBase64 = base64Data;
                var img = $("<img>").attr("src", e.target.result);
                $(".picture").html(img);
            };
            reader.readAsDataURL(hotelLicFile); // 預覽圖
        	}	
    });
	
        $('#contact_form').bootstrapValidator({//初始化驗證功能
            // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                owner_name: {
                    validators: {
                        stringLength: {
                            min: 2,
                            message: '請填入姓氏與姓名至少二字元'
                        },
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FFFㄅ-ㄩㄚ-ㄦA-Za-z\s]+$/,
                            message: '此欄位不得有符號及數字或使用空格區隔'
                        }
                    },
                },
                owner_phone: {
                    validators: {
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        regexp: {
                            regexp: /^(09\d{8}|02\d{8}|0[3-8]\d{7,10})$/,
                            message: '長度不符'
                        }
                    },
                },
                owner_id: {
                    validators: {
                        notEmpty: {
                            message: '此欄位不得空白'
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
                            message: '此欄位不得空白'
                        },
                        emailAddress: {
                            message: '請輸入有效的信箱'
                        }
                    }

                },

                owner_bank: {
                    validators: {
                        stringLength: {
                            min: 8,
                            message: ' '
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
                owner_account: {
                    validators: {
                        stringLength: {
                            min: 8,
                            message: '長度不合法,請確認'
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
                owner_password: {
                    validators: {
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        stringLength: {
                            min: 8,
                            max: 20,
                            message: '密碼長度度需介於8到20個字符,僅能大小寫字母及數字'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]+$/,
                            message: '密碼含有不合格式字元,請重新輸入'
                        },
                    }
                },
                re_password: {
                    validators: {
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        identical: {
                            field: 'owner_password',
                            message: '與前次密碼不相符'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]+$/,
                            message: '有不合格式字元'
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
                            message: " "
                        }
                    }

                },
            },

        });
        
        var isDataValid = true;

			$(".btn_set").click(function(e) {
				e.preventDefault();
				if (!isEmailVerified) {
					Swal.fire({
						allowOutsideClick: false,
						icon: 'error',
						title: '未完成電子郵件驗證',
						text: '請先完成電子郵件驗證'
					});
				}else{
				e.preventDefault();
	  			// 檢查表單驗證結果	
  			Swal.fire({
    			text: "即將送出表單",
    			allowOutsideClick: false,
    			showCancelButton: true,
    			confirmButtonText: '確定',
    			cancelButtonText: '取消'
  			}).then((result) => {
  				e.preventDefault();
    			if (result.isConfirmed) {
	
      			for (const key in allData) {
       			// 如果為null或空字串
       				if (!allData[key]) {
          				isDataValid = false;
          				Swal.fire({
            				allowOutsideClick: false,
            				icon: 'error',
            				title: '資料未填齊全或格式錯誤',
           					text: '請再次確認～'
          				});
          				break; // 有一個是空就退出循環
       				}
      			}
      			
          				
        			 if(isDataValid){
        				  $.ajax({
                              type: "POST",
                              url: "/ownerSignUP/insert",
                              contentType: "application/json",
                              data: JSON.stringify(allData),
                              success: function(res) {
                            	  
                            	  Swal.fire({
                  	          		confirmButtonText: '確定',
                  	          		title: '表單提交完成',
                  	          		iconHtml:`<img src= "./img/happy.gif" style="with 200px; height:200px;">`,
                  	          		text: '審核完畢後會寄發通知信,請留意您的電子信箱',
                  	        		}).then((result) => {
                  	        			if (result.isConfirmed) {
                  	        				location.reload();
                  	        		}
                  	        	});
                            	
                              },
                              
                              error: function(xhr, textStatus, errorThrown) {
                                  Swal.fire({
                                      allowOutsideClick: false,
                                      icon: 'error',
                                      title: '統一編號已被註冊',
                                      text: xhr.responseText // 顯示後端傳回的錯誤信息
                                  });
                              }
                          })
        				         				  
        	     	}
        		
      		}
    			
    	
  	});
}

          try {
        	  //正則化驗證名字
        	  const ownerName = $('#owner_name').val();
        	  const nameRegex = /^[\u4E00-\u9FFFㄅ-ㄩㄚ-ㄦA-Za-z\s]{2,50}$/; 	  
        	  if (!nameRegex.test(ownerName)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '資料格式錯誤或有欄位填寫不全',
        	    });
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	  
        	//正則化驗證電話
        	const ownerPhone = $('#owner_phone').val();
        	const phoneRegex = /^(09\d{8}|02\d{8}|0[3-8]\d{7,10})$/;
        	if (!phoneRegex.test(ownerPhone)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '連絡電話格式有誤哦',
        	    });
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	
        	//正則化驗證身分證
        	const ownerId = $('#owner_id').val();
        	const idRegex = /^[A-Z]{1}[1-2]{1}\d{8}$/;
        	if (!idRegex.test(ownerId)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '資料格式錯誤或有欄位填寫不全',
        	    });
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	//正則化驗證收款帳戶
        	const ownerBank = $('#owner_bank').val();
        	const bankRegex = /^\d{8,}$/;
        	if (!bankRegex.test(ownerBank)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '資料格式錯誤或有欄位填寫不全',
        	    });
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	//正則化驗證統一編號
        	const ownerAccount = $('#owner_account').val();
        	const accountRegex = /^\d{8,}$/;
        	if (!accountRegex.test(ownerAccount)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '資料格式錯誤或有欄位填寫不全',
        	    });
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	
        	
        	//正則化驗證地址
        	const hotelAddress = $('#hotel_address').val();
        	const addressRegex = /^[\u4E00-\u9FFF0-9\u3105-\u3129\-]+$/;
        	if (!addressRegex.test(hotelAddress)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '資料格式錯誤或有欄位填寫不全',
        	    });
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	//正則化驗證密碼
        	const ownerPassword = $('#owner_password').val();
        	const passwordRegex =/^[a-zA-Z0-9]{8,}$/;
        	if (!passwordRegex.test(ownerPassword)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '資料格式錯誤或有欄位填寫不全',
        	    });
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	//正則化驗證證號
        	const hotelLicId = $('#hotel_lic_id').val();
        	const hotellicIdRegex =/^[a-zA-Z0-9 -]+$/;
        	if (!hotellicIdRegex.test(hotelLicId)) {
        	    Swal.fire({
        	      allowOutsideClick: false,
        	      icon: 'error',
        	      title: '資料格式錯誤或有欄位填寫不全',
        	    });
        	    return; //只要驗證不通過就阻止後續程式執行,不然資料就進後端了
        	  }
        	//正則化驗證信箱 
        	const ownerEmail = $('#owner_email').val();
        	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        	if (!emailRegex.test(ownerEmail)) {
        	  Swal.fire({
        	    allowOutsideClick: false,
        	    icon: 'error',
        	    title: '信箱格式錯誤',
        	  });
        	  return;
        	}
          
        	  allData = {
              ownerName: ownerName,
              ownerPhone: ownerPhone,
              ownerId: ownerId,
              ownerEmail: ownerEmail,
              ownerPassword: ownerPassword,
              ownerBank: ownerBank,
              hotelName: $('#hotel_name').val(),
              ownerAccount: ownerAccount,
              hotelAddress: hotelAddress,
              hotelLicId: hotelLicId,
              imageBase64:allData.imageBase64
            };
          } catch (err) {
            console.log('新增失敗', err);
           
          }
        });


    $("#hotel_lic_pic").on("change", function () {
        // console.log("1");    
        $(".picture").css({
            "background-image": "none",
            "opacity": "1"
        });
    });

});