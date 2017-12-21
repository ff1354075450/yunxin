<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/taglib.jsp" %>
<html>
<head>云心</head>
<body>
<h2>Hello World!</h2>

<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxecb77a908ac21669&redirect_uri=http://buy.dotwintech.com/jerry/app/buyer/codeLogin&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect">用户静默授权</a>
<br>
<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxecb77a908ac21669&redirect_uri=http://buy.dotwintech.com/jerry/app/buyer/codeLogin&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect">用户直接授权</a>
</body>
</html>
