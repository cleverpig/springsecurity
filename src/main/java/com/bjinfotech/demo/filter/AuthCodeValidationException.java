package com.bjinfotech.demo.filter;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-7
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class AuthCodeValidationException extends AuthenticationException {
  public AuthCodeValidationException(String msg) {
    super(msg);
  }
}
