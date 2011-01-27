<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bjinfotech.demo.service.WelcomeService"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="java.security.acl.Permission" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<html>
<head><title>admin</title></head>
<body>
  <span>欢迎<sec:authentication property="principal.username"/>，这是管理员页面</span>
  <a href="<c:url value="/index.jsp"/>">返回首页</a>
  <span>
    <%
      ApplicationContext context= WebApplicationContextUtils.getWebApplicationContext(application);
      WelcomeService service= (WelcomeService) context.getBean("welcomeService");
    %>
    <sec:authorize ifAnyGranted="PERM_ACCESS_ADMIN">
      <%=service.sayHelloForAdmin()%><br/>
    </sec:authorize>
    <sec:authorize ifAnyGranted="PERM_ACCESS_ADMIN,PERM_ACCESS_USER">
      <%=service.sayHelloForUser()%>
    </sec:authorize>
  </span>
</body>
</html>