package com.bjinfotech.demo.servlet;

import com.bjinfotech.demo.service.CaptchaServiceSingleton;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-7
 * Time: 下午11:53
 * To change this template use File | Settings | File Templates.
 */
public class CaptchaServlet extends HttpServlet{
  public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";
  public static final String CAPTCHA_SERVICE_BEAN_NAME ="captchaService";
  private DefaultManageableImageCaptchaService captchaService;
  private Log log= LogFactory.getLog(CaptchaServlet.class);

  @Override
  public void init(ServletConfig config) throws ServletException {
    String configFile=config.getInitParameter("configFile");
    ApplicationContext context=new ClassPathXmlApplicationContext(configFile);
    captchaService= (DefaultManageableImageCaptchaService) context.getBean(CAPTCHA_SERVICE_BEAN_NAME);
  }

  @Override
  public void destroy() {
    captchaService=null;
    super.destroy();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    service(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    service(req, resp);
  }

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    byte[] captchaChallengeAsJpeg = null;
    // the output stream to render the captcha image as jpeg into
    ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
    try {
      // get the session id that will identify the generated captcha.
      // the same id must be used to validate the response, the session id is a good candidate!
//      captchaService= CaptchaServiceSingleton.getInstance();
      String captchaId = req.getSession().getId();
      BufferedImage challenge = captchaService.getImageChallengeForID(captchaId, req.getLocale());

      log.info("captchaId:"+captchaId);
      ImageIO.write(challenge, CAPTCHA_IMAGE_FORMAT, jpegOutputStream);
    } catch (IllegalArgumentException e) {
      res.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    } catch (CaptchaServiceException e) {
      res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }

    captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

    // flush it in the response
    res.setHeader("Cache-Control", "no-store");
    res.setHeader("Pragma", "no-cache");
    res.setDateHeader("Expires", 0);
    res.setContentType("image/"+CAPTCHA_IMAGE_FORMAT);

    ServletOutputStream responseOutputStream = res.getOutputStream();
    responseOutputStream.write(captchaChallengeAsJpeg);
    responseOutputStream.flush();
    responseOutputStream.close();
  }

}
