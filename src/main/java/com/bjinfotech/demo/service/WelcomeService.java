package com.bjinfotech.demo.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by IntelliJ IDEA.
 * User: cleverpig
 * Date: 11-1-9
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
public class WelcomeService {
//  @PreAuthorize("hasRole('PERM_ACCESS_ADMIN')")
  public String sayHelloForAdmin(){
    return "你好，管理员";
  }
//  @PreAuthorize("hasAnyRole('PERM_ACCESS_ADMIN','PERM_ACCESS_USER')")
  public String sayHelloForUser(){
     return "你好，用户";
  }
}
