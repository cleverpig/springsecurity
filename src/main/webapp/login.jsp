<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<html>
<head><title>登录页</title></head>

<body>
  <script type="text/javascript">
    function reloadCaptchaCode(id){
      var date=new Date();
      document.getElementById(id).src='captcha.htm?date='+date.getTime();
    }
  </script>
  <c:if test="${not empty param.error}">
    <p>
      <span style="color:red;">
        登录失败请重试！<br/>
        原因: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
      </span>
    </p>
  </c:if>

  <form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
    <table>
      <tr>
        <td>用户名：</td>
        <td>
          <input type='text' name='j_username' value='<c:if test="${not empty param.error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
        </td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><input type='password' name='j_password'></td>
      </tr>
      <tr>
        <td>验证码：</td>
        <td>
          <img id="captchaCode" src="captcha.htm"/><input type="text" name="j_captcha_response" value=""/>
          <a href="javascript:reloadCaptchaCode('captchaCode');">刷新</a>
        </td>
      </tr>
      <tr>
        <td><input type="checkbox" name="_spring_security_remember_me"></td>
        <td>记住我，两周内不需登录</td>
      </tr>

      <tr>
        <td colspan='2'>
          <input type="submit" type="登录">
          <input type="reset" type="重置">
        </td>
      </tr>
    </table>
  </form>
</body>
</html>