package com.bjinfotech.demo.service;

import com.octo.captcha.service.CaptchaService;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-9
 * Time: 上午5:58
 * To change this template use File | Settings | File Templates.
 */
public class CaptchaServiceFactoryBean implements FactoryBean<CaptchaService>{
  @Override
  public CaptchaService getObject() throws Exception {
    return CaptchaServiceSingleton.getInstance();
  }

  @Override
  public Class<CaptchaService> getObjectType() {
    return CaptchaService.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
