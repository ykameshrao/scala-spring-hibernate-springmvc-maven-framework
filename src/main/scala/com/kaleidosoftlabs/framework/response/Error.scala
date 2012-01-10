package com.kaleidosoftlabs.framework.response

import java.util.Arrays
import reflect.BeanProperty
import com.thoughtworks.xstream.annotations.XStreamOmitField

/**
 * This object would majorly be used for communicating the system exceptions on
 * the frontend.
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:13 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class Error(@BeanProperty var code: String,
            @BeanProperty var message: AnyRef,
            @BeanProperty @XStreamOmitField stacktrace: Array[StackTraceElement],
            var exception: Exception) {

  def this(code: String, message: AnyRef, stacktrace: Array[StackTraceElement]) =
    this (code, message, stacktrace, null)


  def this(code: String, message: AnyRef) = this (code, message, null)


  def this(message: AnyRef) = this (null, message)


  override def toString: String = {
    return "Error{" +
      "code='" + code + '\'' +
      ", message=" + message +
      ", stackTrace=" + (if (stacktrace == null) null else Arrays.asList(stacktrace)) +
      ", exception=" + exception + '}'
  }
}