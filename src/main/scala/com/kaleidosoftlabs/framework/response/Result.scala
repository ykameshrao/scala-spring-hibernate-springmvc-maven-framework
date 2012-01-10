package com.kaleidosoftlabs.framework.response

import reflect.BeanProperty

/**
 * This class returns the users with the result object expected by the user
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:15 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class Result {
  @BeanProperty var obj: AnyRef = null
  @BeanProperty var message: String = null


  def this(obj: AnyRef, message: String) {
    this ()
    this.obj = obj
    this.message = message
  }


  override def toString: String = {
    return "Result{" + "object=" + obj + ", message='" + message + '\'' + '}'
  }
}