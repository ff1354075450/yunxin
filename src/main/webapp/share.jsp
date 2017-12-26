<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/taglib.jsp" %>
<html>
<head>
    <title>分享</title>
</head>
<body>

<h1>简单分享页面</h1>
<br>

<button onclick="share()">分享</button>

<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" type="text/javascript" charset="utf-8"></script>
<script>
    wx.config({
        ${wxConfig}
    });
    wx.ready(function(){
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
        alert("read方法");
    });
    wx.error(function(res){
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
        alert(res);
    });
    wx.onMenuShareTimeline({
        title: '分享朋友圈', // 分享标题
        link: 'http://buy.dotwintech.com/jerry', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: '', // 分享图标
        success: function () {
            // 用户确认分享后执行的回调函数
            alert("分享成功")
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
            alert("分享取消")
        }
    });
    wx.onMenuShareAppMessage({
        title: '分享给朋友', // 分享标题
        desc: '测试分享给朋友', // 分享描述
        link: 'http://www.dotwintech.com/jerry', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: '', // 分享图标
        type: '', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            alert("分享成功")
// 用户确认分享后执行的回调函数
        },
        cancel: function () {
// 用户取消分享后执行的回调函数
            alert("分享取消")
        }
    });
    function share() {
        wx.chooseWXPay({
            timestamp: 0, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
            nonceStr: '', // 支付签名随机串，不长于 32 位
            package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
            signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
            paySign: '', // 支付签名
            success: function (res) {
                
// 支付成功后的回调函数
            }
        });
    }
</script>
</body>
</html>
