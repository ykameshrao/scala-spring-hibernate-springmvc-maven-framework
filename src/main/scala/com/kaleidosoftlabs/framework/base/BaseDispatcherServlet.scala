package com.kaleidosoftlabs.framework.base

import org.springframework.web.servlet.DispatcherServlet
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import com.kaleidosoftlabs.util.Log

/**
 * BaseDispatcherServlet acts as a wrapper for Spring's DispatcherServlet class
 * so that we can have our own custom implementations written here to have those facilities
 * or features being provided to everybody extending this class
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 1:37 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class BaseDispatcherServlet extends DispatcherServlet with Log {
  protected override def doService(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    super.doService(request, response)
  }


  protected override def doDispatch(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    super.doDispatch(request, response)
  }


  protected override def service(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    super.service(request, response)
  }
}