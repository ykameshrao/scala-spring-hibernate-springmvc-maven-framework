package com.kaleidosoftlabs.hsm.api.controller

import base.BaseApiController
import org.springframework.web.servlet.ModelAndView
import org.springframework.beans.factory.annotation.Autowired
import com.kaleidosoftlabs.hsm.service.UserService
import com.kaleidosoftlabs.framework.response.{Response, Result, Error}
import com.kaleidosoftlabs.hsm.common.Queries
import com.kaleidosoftlabs.hsm.api.common.{Const, Routes}
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}
import com.kaleidosoftlabs.framework.validation.Validity
import com.kaleidosoftlabs.hsm.model.entity.User
import com.kaleidosoftlabs.hsm.model.entity.helper.UserHelper
import org.springframework.stereotype.Controller

/**
 * Controller to provide User CRUD
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 7:37 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
@Controller
@RequestMapping(Array("users"))
class UserController extends BaseApiController {
  @Autowired val userService: UserService = null


  @RequestMapping(value = Array("verify"), method = Array(RequestMethod.GET))
  @ResponseBody def verify: ModelAndView = {
    val response: Response = this.response
    try {
      response.result = 
        userService.findById(httpReq.getAttribute(Const.authUserId).asInstanceOf[Long], Queries.userById)
    } catch {
      case e: Exception => {
        log.error(e.getMessage)
        response.error = new Error(apiErrors.iseCode, e.getMessage)
      }
    }
    return mav(response)
  }


  @RequestMapping(value = Array("create"), method = Array(RequestMethod.POST))
  @ResponseBody def create(@RequestParam(value = "username") userName: String, 
                           @RequestParam(value = "password") passWord: String, 
                           @RequestParam(value = "email") email: String, 
                           @RequestParam(value = "mobile") mobile: String, 
                           @RequestParam(value = "role") role: String): ModelAndView = {
    val response: Response = this.response
    try {
      val user: User = UserHelper.createUser(userName, passWord, email, mobile, role, httpReq)
      val vsUser: Validity = UserHelper.validate(user, apiErrors)
      if (vsUser.valid) {
        userService.insert(user)
        response.result = user
      } else {
        response.error = new Error(apiErrors.invUserCode, vsUser.errorMsgs)
      }
    }
    catch {
      case e: Exception => {
        log.error(e.getMessage)
        response.error = new Error(apiErrors.iseCode, e.getMessage)
      }
    }
    return mav(response)
  }
}