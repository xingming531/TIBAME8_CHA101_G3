function formCheck() {
    $(document).ready(function () { //$(document).ready()方法可以確保在操作或處理文檔元素時，這些元素已經完全加載並可以被正確訪問
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
                        stringLength: {
                            min: 10,
                            message: ' '
                        },
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        regexp: {
                            regexp: /^\d+$/,
                            message: ' '
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
                            message: ' '
                        }
                    }

                },
                owner_email: {
                    validators: {
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        emailAddress: {
                            message: ' '
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
                            message: ' '
                        },
                        notEmpty: {
                            message: '此欄位不得空白'
                        }
                    },
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
                    },
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
                    },
                },
                owner_password: {
                    validators: {
                        notEmpty: {
                            message: '此欄位不得空白'
                        },
                        stringLength: {
                            min: 8,
                            max: 20,
                            message: '密碼長度度需介於8到20个字符,僅能大小寫字母及數字'
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
                }
            },

        });


    });
}