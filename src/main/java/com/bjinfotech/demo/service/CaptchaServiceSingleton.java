package com.bjinfotech.demo.service;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-9
 * Time: 上午5:53
 * To change this template use File | Settings | File Templates.
 */
public class CaptchaServiceSingleton {
  private static DefaultManageableImageCaptchaService captchaService;
  private static final Log log= LogFactory.getLog(CaptchaService.class);

  public CaptchaServiceSingleton() {
  }
  public static DefaultManageableImageCaptchaService getInstance(){
    if (captchaService==null){
      log.info("create new Captcha instance...");
      captchaService=new DefaultManageableImageCaptchaService(
          new FastHashMapCaptchaStore(),
    		  new MyImageCaptchaEngine(),
    		  180,
    		  100000,
    		  75000
      );
    }
    return captchaService;
  }
}
