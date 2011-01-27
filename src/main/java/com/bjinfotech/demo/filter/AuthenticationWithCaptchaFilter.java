package com.bjinfotech.demo.filter;

import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-7
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationWithCaptchaFilter extends UsernamePasswordAuthenticationFilter {
//  private static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD="validateCode";
  private static final String DEFAULT_VALIDATE_CODE_PARAMETER="j_captcha_response";
  private Log log= LogFactory.getLog(AuthenticationWithCaptchaFilter.class);
  private ImageCaptchaService captchaService;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse reponse)
      throws AuthenticationException {
      if (checkValidateCode(request)==false) {
        String username = obtainUsername(request);
        request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, username);
        // 用户输入的值与看到的不一致,抛出异常
        log.error("验证码输入不正确!");
        throw new AuthCodeValidationException("验证码输入不正确");
      }
      else{
        log.info("验证码输入正确");
      }
      return super.attemptAuthentication(request,reponse);
  }
  protected boolean checkValidateCode(HttpServletRequest request) {
    String validateCodeParameter = obtainValidateCodeParameter(request);
    log.info("validateCodeParameter:"+validateCodeParameter);
    log.info("request.getSession().getId():"+request.getSession().getId());
    boolean validateResult=captchaService.validateResponseForID(request.getSession().getId(),validateCodeParameter);

    return validateResult;
  }
  private String obtainValidateCodeParameter(HttpServletRequest request) {
    return request.getParameter(DEFAULT_VALIDATE_CODE_PARAMETER);
  }

//  private String obtainSessionValidateCode(HttpServletRequest request) {
//    Object obj = request.getSession().getAttribute(DEFAULT_SESSION_VALIDATE_CODE_FIELD);
//    return obj==null?"":obj.toString();
//  }

  public ImageCaptchaService getCaptchaService() {
    return captchaService;
  }

  public void setCaptchaService(ImageCaptchaService captchaService) {
    this.captchaService = captchaService;
  }
}
