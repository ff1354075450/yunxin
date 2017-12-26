<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/taglib.jsp" %>
<html>
<head>云心</head>
<body>
<h2>Hello World!</h2>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" type="text/javascript" charset="utf-8"></script>

<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxecb77a908ac21669&redirect_uri=http://buy.dotwintech.com/yunxin/wechat/getToken&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect">用户静默授权</a>
<br>
<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxecb77a908ac21669&redirect_uri=http://buy.dotwintech.com/yunxin/wechat/getToken&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect">用户直接授权</a>

</body>
</html>
