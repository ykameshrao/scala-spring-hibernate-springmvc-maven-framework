package com.kaleidosoftlabs.hsm.api.controller

import base.BaseApiController
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.ModelAndView
import org.springframework.stereotype.Controller
import org.springframework.context.annotation.ImportResource
import com.kaleidosoftlabs.framework.response.{Response, Error}
import org.springframework.web.bind.annotation.{ResponseBody, ResponseStatus, RequestMapping}
import com.kaleidosoftlabs.hsm.api.common.{Const, Routes}

/**
 * Controller to handle the api errors
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 7:37 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
@Controller
@RequestMapping(Array("errors"))
@ImportResource(Array("classpath:config/spring/applicationContext-properties.xml"))
class ErrorController extends BaseApiController {
  @RequestMapping(value = Array("/400")) 
  @ResponseStatus(HttpStatus.BAD_REQUEST) 
  @ResponseBody def badRequest: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.brCode, apiErrors.brMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("/401"))
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody def unauthorised: ModelAndView = {
    var response: Response = this.response
    response.error = new Error(apiErrors.uaCode, apiErrors.uaMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("/404"))
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody def notFound: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.nfCode, apiErrors.nfMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("/405"))
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ResponseBody def methodNotAllowed: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.mnaCode, apiErrors.mnaMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("/408"))
  @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
  @ResponseBody def requestTimeout: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.rtCode, apiErrors.rtMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("/415"))
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ResponseBody def unsupportedMediaType: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.umtCode, apiErrors.umtMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("/500"))
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody def internalServerError: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.iseCode, apiErrors.iseMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("/501"))
  @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
  @ResponseBody def notImplemented: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.niCode, apiErrors.niMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("/503"))
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  @ResponseBody def serviceUnavailable: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.suCode, apiErrors.suMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("credsMissing"))
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody def credsMissing: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.cmAuthCode, apiErrors.cmAuthMsg)
    return mav(response)
  }


  @RequestMapping(value = Array("authFailed"))
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody def authFailed: ModelAndView = {
    val response: Response = this.response
    response.error = new Error(apiErrors.iucAuthCode, apiErrors.iucAuthMsg)
    return mav(response)
  }
}