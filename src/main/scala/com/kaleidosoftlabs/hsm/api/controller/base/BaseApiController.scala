package com.kaleidosoftlabs.hsm.api.controller.base

import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import com.kaleidosoftlabs.framework.base.BaseController
import com.kaleidosoftlabs.framework.aop.ExceptionHandlerAdvice
import com.kaleidosoftlabs.framework.response.Response
import org.springframework.web.bind.annotation.{ResponseBody, ExceptionHandler}
import com.kaleidosoftlabs.hsm.api.common.{Const, Errors}
import com.kaleidosoftlabs.framework.response.Error
import org.springframework.web.servlet.ModelAndView
import org.springframework.context.ApplicationContext

/**
 * Class to be extended by each of the controller
 * implementing API methods and services for smooth access
 * to the commonly used server response object and integration
 * with ExceptionHandlerAdvice interceptor to communicate the
 * exact exception to the frontend for better debugging.
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 7:37 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class BaseApiController extends BaseController {
  @Autowired protected val appCtx:ApplicationContext = null
  @Autowired protected var exceptionHandlerAdvice: ExceptionHandlerAdvice = null
  @Autowired protected var httpReq: HttpServletRequest = null
  @Autowired protected var apiErrors: Errors = null


  protected def response: Response = {
    var serverResponse: Response = new Response
    exceptionHandlerAdvice.response = serverResponse
    return serverResponse
  }


  protected def mav(response: Response): ModelAndView = {
    val mav_ = new ModelAndView
    response.respTime = (System.currentTimeMillis() - response.reqTime)  + "ms"
    response.reqTime = 0
    mav_.addObject(Const.response, response)
    return mav_
  }


  @ExceptionHandler(Array(classOf[Exception]))
  @ResponseBody def exceptionHandler(ex: Exception): ModelAndView = {
    val response: Response = new Response
    response.error = new Error("500", ex.getMessage)
    log.error("[" + this.getClass.getSimpleName + ": baseApiExceptionHandler] Response: " + response)
    return mav(response)
  }
}