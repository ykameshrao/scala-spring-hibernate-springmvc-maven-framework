package com.kaleidosoftlabs.hsm.api.controller

import base.BaseApiController
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.ModelAndView
import org.springframework.beans.factory.annotation.Autowired
import com.kaleidosoftlabs.hsm.service.UserService
import com.kaleidosoftlabs.framework.response.{Response, Error, Result}
import org.springframework.web.bind.annotation.{ResponseBody, ResponseStatus, RequestMapping, RequestMethod}
import com.kaleidosoftlabs.hsm.model.entity.User
import com.kaleidosoftlabs.hsm.model.entity.helper.UserHelper
import com.kaleidosoftlabs.hsm.common.Queries
import org.springframework.stereotype.Controller
import com.kaleidosoftlabs.hsm.api.common.{Routes, Const}
import com.kaleidosoftlabs.hsm.system.actor.{TestTypedActor, TestActor}
import akka.actor.Actor._
import akka.actor.ActorRef
import org.springframework.context.ApplicationContext

/**
 * Controller to test the availability of the API
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 7:37 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
@Controller
@RequestMapping(Array("test"))
class TestController extends BaseApiController {
  @Autowired val userService: UserService = null
  @Autowired val testTypedActor: TestTypedActor = null
  private var testActor: ActorRef = null


  @RequestMapping(value = Array("/ping"), method = Array(RequestMethod.GET))
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody def ping: ModelAndView = {
    log.info("testing ping...")
    val response: Response = this.response
    response.result = "Chabootra says Hi..!"
    log.info("responding: " + response.toString)
    return mav(response)
  }


  @RequestMapping(value = Array("/pingActor"), method = Array(RequestMethod.GET))
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody def pingActor: ModelAndView = {
    log.info("testing actor ping...")
    testActor = appCtx.getBean("testActor").asInstanceOf[ActorRef]
    val response: Response = this.response
    var i: Int = 0
    while (i < 1000) {
      testActor ! "Hello"
      testActor ! i.toString
      testTypedActor.pingTestTypedActor("Hi there! Typed actor")
      i += 1
    }

    response.result = "Actor pinged..."
    log.info("responding: " + response)
    return mav(response)
  }


  @RequestMapping(value = Array("/user"), method = Array(RequestMethod.GET))
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody def user: ModelAndView = {
    val response: Response = this.response
    val u: User = UserHelper.createUser("test", "test", "test@test.com", "9898989898", "super_admin", httpReq)
    response.result = u
    log.info("responding: " + response.toString)
    return mav(response)
  }


  @RequestMapping(value = Array("/authUser"), method = Array(RequestMethod.GET))
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody def authUser: ModelAndView = {
    val response: Response = this.response
    try {
      response.result =
        userService.findById(httpReq.getAttribute(Const.authUserId).asInstanceOf[Long], Queries.userById)
    } catch {
      case e: Exception => {
        log.error(e.getMessage)
      }
    }
    log.info("responding: " + response.toString)
    return mav(response)
  }


  @RequestMapping(value = Array("/exception"), method = Array(RequestMethod.GET)) @ResponseStatus(HttpStatus.BAD_REQUEST) @ResponseBody def exception: ModelAndView = {
    val response: Response = this.response
    try {
      userService.findById(100000L, Queries.userById)
    } catch {
      case e: Exception => {
        log.error(e.getMessage)
        throw e
      }
    }
    return mav(response)
  }
}