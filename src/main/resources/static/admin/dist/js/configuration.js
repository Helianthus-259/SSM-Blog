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

$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/adminuser/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '用户名', name: 'username', index: 'username', width: 80},
            {label: '昵称', name: 'nickName', index: 'nickName', width: 80},
        ],
        height: 700,
        rowNum: 18,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
    function coverImageFormatter(cellvalue) {
        return "<img src='" + cellvalue + "' height=\"120\" width=\"160\" alt='coverImage'/>";
    }

    function statusFormatter(cellvalue) {
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 50%;\">草稿</button>";
        }
        else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 50%;\">发布</button>";
        }
    }

});

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