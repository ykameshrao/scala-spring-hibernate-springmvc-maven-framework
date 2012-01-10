package com.kaleidosoftlabs.framework.base

/**
 * The exception class to be extended by all
 * custom Exception classes
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 1:43 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
/**
 * The exception class to be extended by all
 * custom Exception classes
 *
 * @author Y Kamesh Rao
 */
class BaseException extends Exception {
  protected var errorCode: AnyRef = null

  def this(message: String) {
    this ()
  }


  def this(message: String, errorCode: AnyRef) {
    this ()
    this.errorCode = errorCode
  }
}

