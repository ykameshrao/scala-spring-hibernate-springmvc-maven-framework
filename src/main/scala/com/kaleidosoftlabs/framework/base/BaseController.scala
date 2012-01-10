package com.kaleidosoftlabs.framework.base

import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.springframework.web.servlet.mvc.AbstractController
import com.kaleidosoftlabs.util.Log

/**
 * All controllers in spring should extend this controller so as to have
 * centralize control for doing any sort of common functionality.
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 1:40 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class BaseController extends AbstractController with Log {
  protected def handleRequestInternal(httpServletRequest: HttpServletRequest,
                                      httpServletResponse: HttpServletResponse): ModelAndView = {
    return null
  }
}