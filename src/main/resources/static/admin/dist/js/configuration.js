$(function () {
    //修改站点信息
    $('#updateWebsiteButton').click(function () {
        $("#updateWebsiteButton").attr("disabled", true);
        //ajax提交数据
        var params = $("#websiteForm").serialize();
        $.ajax({
            type: "POST",
            url: "/admin/configurations/website",
            data: params,
            success: function (result) {
                if (result.resultCode == 200 && result.data) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
    });
    //个人信息
    $('#updateUserInfoButton').click(function () {
        $("#updateUserInfoButton").attr("disabled", true);
        var userName = $('#yourName').val();
        var nickName = $('#yourNickname').val();
        var oPassword = $('#yourPassword').val();
        var nPassword = $('#yourPassword1').val();
        if (true){
            var params = $("#userInfoForm").serialize();
            $.ajax({
                type: "POST",
                url: "/admin/configurations/adduserInfo",
                data: params,
                success: function (result) {
                    if (result.resultCode == 200) {
                        swal("保存成功", {
                            icon: "success",
                        });
                    }
                    else {
                        swal(result.message, {
                            icon: "error",
                        });
                    }
                    ;
                },
                error: function () {
                    swal("操作失败", {
                        icon: "error",
                    });
                }
            });
        }

    });
    //修改底部设置
    $('#updateFooterButton').click(function () {
        $("#updateFooterButton").attr("disabled", true);
        var params = $("#footerForm").serialize();
        $.ajax({
            type: "POST",
            url: "/admin/configurations/footer",
            data: params,
            success: function (result) {
                if (result.resultCode == 200&& result.data) {
                    swal("保存成功", {
                        icon: "success",
                    });
                }
                else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
    });

})

// function validUserNameForUpdate(userName, nickName) {
//     if (isNull(userName) || userName.trim().length < 1) {
//         $('#updateUserName-info').css("display", "block");
//         $('#updateUserName-info').html("请输入登陆名称！");
//         return false;
//     }
//     if (isNull(nickName) || nickName.trim().length < 1) {
//         $('#updateUserName-info').css("display", "block");
//         $('#updateUserName-info').html("昵称不能为空！");
//         return false;
//     }
//     if (!validUserName(userName)) {
//         $('#updateUserName-info').css("display", "block");
//         $('#updateUserName-info').html("请输入符合规范的登录名！");
//         return false;
//     }
//     if (!validCN_ENString2_18(nickName)) {
//         $('#updateUserName-info').css("display", "block");
//         $('#updateUserName-info').html("请输入符合规范的昵称！");
//         return false;
//     }
//     return true;
// }
//
// /**
//  * 密码验证
//  */
// function validPasswordForUpdate(originalPassword, newPassword) {
//     if (isNull(originalPassword) || originalPassword.trim().length < 1) {
//         $('#updatePassword-info').css("display", "block");
//         $('#updatePassword-info').html("请输入原密码！");
//         return false;
//     }
//     if (isNull(newPassword) || newPassword.trim().length < 1) {
//         $('#updatePassword-info').css("display", "block");
//         $('#updatePassword-info').html("新密码不能为空！");
//         return false;
//     }
//     if (!validPassword(newPassword)) {
//         $('#updatePassword-info').css("display", "block");
//         $('#updatePassword-info').html("请输入符合规范的密码！");
//         return false;
//     }
//     if (!validPassword(originalPassword)) {
//         $('#updatePassword-info').css("display", "block");
//         $('#updatePassword-info').html("请输入符合规范的密码！");
//         return false;
//     }
//     return true;
// }