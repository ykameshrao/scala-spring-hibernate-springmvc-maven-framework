package com.kaleidosoftlabs.framework.aop

import com.kaleidosoftlabs.framework.response.Response
import com.kaleidosoftlabs.framework.response.Error
import java.lang.reflect.Method
import com.kaleidosoftlabs.framework.base.BaseException
import com.kaleidosoftlabs.util.Log
import org.springframework.aop.ThrowsAdvice

/**
 * Class to help handle the Exceptions in the application, system or any level
 * code at one single point. Acts as the intercepter point using Spring AOP to
 * commonly handle all the thrown exceptions on the beans/classes to which it is
 * proxying. This will print the exception to console. You can extend this class
 * to have specialized Exception handler features, like sending an email to
 * developer, showing a message on frontend, attaching an error code and common
 * user understandable message.
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:19 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class ExceptionHandlerAdvice extends ThrowsAdvice with Log {
  var response: Response = null

  /**
   * Gets called after the Exception is thrown at the code level
   *
   * @param m      Method where the exception was thrown
   * @param args   Arguments sent to that method
   * @param target The enclosing class of the method
   * @param ex     The exception that was thrown
   */
  def afterThrowing(m: Method, args: Array[AnyRef], target: AnyRef, ex: Throwable): Unit = {
    log.error(ex.getMessage)
    val error: Error = new Error("500", ex.getMessage, ex.getStackTrace)
    if (response != null) response.error = error
  }


  /**
   * Gets called after the Exception is thrown at the code level
   *
   * @param m      Method where the exception was thrown
   * @param args   Arguments sent to that method
   * @param target The enclosing class of the method
   * @param ex     The exception that was thrown
   */
  def afterThrowing(m: Method, args: Array[AnyRef], target: AnyRef, ex: RuntimeException): Unit = {
    log.error(ex.getMessage)
    val error: Error = new Error("500", ex.getMessage, ex.getStackTrace)
    if (response != null) response.error = error
  }


  /**
   * Gets called after the Exception is thrown at the code level
   *
   * @param m      Method where the exception was thrown
   * @param args   Arguments sent to that method
   * @param target The enclosing class of the method
   * @param ex     The exception that was thrown
   */
  def afterThrowing(m: Method, args: Array[AnyRef], target: AnyRef, ex: Exception): Unit = {
    log.error(ex.getMessage)
    val error: Error = new Error("500", ex.getMessage, ex.getStackTrace)
    ex.printStackTrace
    if (response != null) response.error = error
  }


  /**
   * Gets called after the project level Exception is thrown at the code level
   *
   * @param m      Method where the exception was thrown
   * @param args   Arguments sent to that method
   * @param target The enclosing class of the method
   * @param ex     The exception that was thrown, mainly the project level
   */
  def afterThrowing(m: Method, args: Array[AnyRef], target: AnyRef, ex: BaseException): Unit = {
    log.error(ex.getMessage)
    var error: Error = new Error("500", ex.getMessage, ex.getStackTrace)
    ex.printStackTrace
    if (response != null) response.error = error
  }
}