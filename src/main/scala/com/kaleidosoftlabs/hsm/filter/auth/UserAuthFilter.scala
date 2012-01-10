package com.kaleidosoftlabs.hsm.filter.auth

import org.apache.commons.codec.binary.Base64
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.FilterChain
import com.kaleidosoftlabs.util.Log
import com.kaleidosoftlabs.hsm.model.entity.User
import com.kaleidosoftlabs.hsm.service.UserService
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import com.kaleidosoftlabs.hsm.model.entity.helper.UserHelper
import com.kaleidosoftlabs.hsm.api.common.{Const, Routes}
import com.kaleidosoftlabs.framework.exception.auth.{AuthCredentialsMissingException, AuthenticationFailedException}
import com.kaleidosoftlabs.framework.exception.database.NotFoundException


/**
 * Filter to process user authentication for the incoming service requests
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:59 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class UserAuthFilter extends OncePerRequestFilter with Log {
  @Autowired var userService: UserService = null


  protected def doFilterInternal(httpServletRequest: HttpServletRequest,
                                 httpServletResponse: HttpServletResponse,
                                 filterChain: FilterChain): Unit = {
    val authorizationHeader: String = httpServletRequest.getHeader("authorization")
    val authenticationHeader: String = httpServletRequest.getHeader("authentication")
    val route: String = httpServletRequest.getRequestURI
    log.info("route: " + route)
    if (Routes.userAuthRoutes.contains(route)) {
      try {
        var authHeader: String = null
        if (authorizationHeader == null || authorizationHeader.equalsIgnoreCase("")) {
          if (authenticationHeader == null || authenticationHeader.equalsIgnoreCase("")) {
            authHeader = null
          }
          else {
            authHeader = authenticationHeader
            log.info("authentication: " + authenticationHeader)
          }
        }
        else {
          authHeader = authorizationHeader
          log.info("authorization: " + authorizationHeader)
        }
        if (authHeader != null && !authHeader.equalsIgnoreCase("")) {
          val creds: Array[String] = authHeader.split(" ")
          if (creds(0).equalsIgnoreCase("basic")) {
            val credString: String = new String(Base64.decodeBase64(creds(1).getBytes))
            val userPass: Array[String] = credString.split(":")
            if (userPass != null && userPass.length > 0) {
              val user: User = userService.findByUserName(userPass(0))
              log.info("User Found: " + user.userName)
              if (user.passWord == UserHelper.hashPassword(userPass(1))) {
                log.info("Authenticated: " + user.userName)
                httpServletRequest.setAttribute(Const.authUserId, user.id)
                filterChain.doFilter(httpServletRequest, httpServletResponse)
              }
              else {
                log.info("User Authentication Failed: " + user.userName)
                throw new AuthenticationFailedException("User authentication failed")
              }
            }
            else {
              throw new AuthCredentialsMissingException("Authentication parameters missing")
            }
          }
          else {
            throw new AuthCredentialsMissingException("Authentication parameters missing")
          }
        }
        else {
          throw new AuthCredentialsMissingException("Authentication parameters missing")
        }
      }
      catch {
        case ae: AuthenticationFailedException => {
          httpServletResponse.setHeader("accepts", httpServletRequest.getHeader("accepts"))
          httpServletResponse.sendRedirect(Routes.authFailedUrl + route.substring(route.indexOf(".")))
        }
        case ae: AuthCredentialsMissingException => {
          httpServletResponse.setHeader("accepts", httpServletRequest.getHeader("accepts"))
          httpServletResponse.sendRedirect(Routes.credsMissingUrl + route.substring(route.indexOf(".")))
        }
        case nfe: NotFoundException => {
          httpServletResponse.setHeader("accepts", httpServletRequest.getHeader("accepts"))
          httpServletResponse.sendRedirect(Routes.authFailedUrl + route.substring(route.indexOf(".")))
        }
        case e: Exception => {
          log.error(e.getMessage)
          httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
        }
      }
    }
    else {
      filterChain.doFilter(httpServletRequest, httpServletResponse)
    }
  }
}