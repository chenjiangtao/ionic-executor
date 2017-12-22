var Test = {
    test: function () {
        alert('test');
    }
}

var prompt = function (message, style, time) {
    style = (style === undefined) ? 'alert-success' : style;
    time = (time === undefined) ? 1500 : time;
    $('<div>')
        .appendTo('body')
        .addClass('alert ' + style)
        .html(message)
        .show()
        .delay(time)
        .fadeOut();
};

// 成功提示
var success_prompt = function (message, time) {
    prompt(message, 'alert-success', time);
};

// 失败提示
var fail_prompt = function (message, time) {
    prompt(message, 'alert-danger', time);
};

// 提醒
var warning_prompt = function (message, time) {
    prompt(message, 'alert-warning', time);
};

// 信息提示
var info_prompt = function (message, time) {
    prompt(message, 'alert-info', time);
};


$(document).ready(function () {
    // alert(JSON.stringify(data)); //调试用
    var tips = document.getElementById("exec-result");

    $("#exec-cmd-bt").click(function () {
        alert("功能失效");
        var data = {};
        data['commands'] = $("#commands").val().trim();
        tips.innerHTML = ("<h3>命令执行中......</h3><br>" + data['commands']);

        $.ajax({
            type: 'POST',
            dataType: 'json', //返回json数据
            data: data,
            url: '/api/exec/cmd',
            success: function (result) {
                if (result.success) {
                    tips.innerHTML = ("<h3>执行成功</h3><br>" + "<p>" + result.message + "</p>");
                } else {
                    tips.innerHTML = ("<h3>执行失败</h3><br>" + "<p>" + result.message + "</p>");
                }
            },
            error: function () {
                tips.innerHTML = ("<h3>系统异常</h3><br>");
            }
        });

    });


    $("#release").click(function () {
        tips.innerHTML = ("<h3>打包中......</h3><br>");
        $.ajax({
            type: 'GET',
            dataType: 'json', //返回json数据
            url: '/api/exec/release',
            success: function (result) {
                if (result.success) {
                    tips.innerHTML = ("<h3>执行成功</h3><br>" + "<p>" + result.message + "</p>");
                } else {
                    tips.innerHTML = ("<h3>执行失败</h3><br>" + "<p>" + result.message + "</p>");
                }
            },
            error: function () {
                tips.innerHTML = ( "<h3>系统异常</h3><br>");
            }
        });

    });

    $("#gitpull").click(function () {
        tips.innerHTML = ("<h3>代码拉取中......</h3><br>");
        $.ajax({
            type: 'GET',
            dataType: 'json', //返回json数据
            url: '/api/exec/gitpull',
            success: function (result) {
                if (result.success) {
                    tips.innerHTML = ("<h3>执行成功</h3><br>" + "<p>" + result.message + "</p>");
                } else {
                    tips.innerHTML = ("<h3>执行失败</h3><br>" + "<p>" + result.message + "</p>");
                }
            },
            error: function () {
                tips.innerHTML = ( "<h3>系统异常</h3><br>");
            }
        });

    });

    $("#gitpull-ymy").click(function () {
        tips.innerHTML = ("<h3>代码拉取中......</h3><br>");
        $.ajax({
            type: 'GET',
            dataType: 'json', //返回json数据
            url: '/api/exec/gitpull-ymy',
            success: function (result) {
                if (result.success) {
                    tips.innerHTML = ("<h3>执行成功</h3><br>" + "<p>" + result.message + "</p>");
                } else {
                    tips.innerHTML = ("<h3>执行失败</h3><br>" + "<p>" + result.message + "</p>");
                }
            },
            error: function () {
                tips.innerHTML = ( "<h3>系统异常</h3><br>");
            }
        });
    });


    $("#add-platform-release").click(function () {
        tips.innerHTML = ("<h3>删除platform并重新打包中......</h3><br>");
        $.ajax({
            type: 'GET',
            dataType: 'json', //返回json数据
            url: '/api/exec/add-platform-release',
            success: function (result) {
                if (result.success) {
                    tips.innerHTML = ("<h3>执行成功</h3><br>" + "<p>" + result.message + "</p>");
                } else {
                    tips.innerHTML = ("<h3>执行失败</h3><br>" + "<p>" + result.message + "</p>");
                }
            },
            error: function () {
                tips.innerHTML = ("<h3>系统异常</h3><br>");
            }
        });

    });


    $("#rmall").click(function () {
        tips.innerHTML = ("<h3>未实现......</h3><br>");
        $.ajax({
            type: 'GET',
            dataType: 'json', //返回json数据
            url: '/api/exec/rmall',
            success: function (result) {
                if (result.success) {
                    result.innerHTML = ("<h3>执行成功</h3><br>" + "<p>" + result.message + "</p>");
                } else {
                    result.innerHTML = ("<h3>执行失败</h3><br>" + "<p>" + result.message + "</p>");
                }
            },
            error: function () {
                result.innerHTML = ("<h3>系统异常</h3><br>");
            }
        });

    });

});