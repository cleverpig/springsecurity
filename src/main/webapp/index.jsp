<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
  <h2>Hello World! 欢迎<sec:authentication property="principal.username"/></h2>
  <a href="<c:url value="/admin.jsp"/>">管理员页面</a>
  <a href="<c:url value="/user.jsp"/>">用户页面</a>
  <a href="<c:url value="/j_spring_security_logout"/>">登出</a>
</body>
</html>
