package com.kaleidosoftlabs.framework.response

import org.codehaus.jackson.annotate.JsonIgnore
import reflect.BeanProperty
import com.thoughtworks.xstream.annotations.{XStreamOmitField, XStreamAlias}


/**
 * The purpose of this class is to accumulcate all the positive, negative and
 * neutral responses of the user activity and send it back to the user as on
 * object. It will contain both the result and/or errors for user actions.
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:17 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
@XStreamAlias("response")
class Response(@BeanProperty var result: AnyRef,
               @BeanProperty var error: Error,
               @JsonIgnore @XStreamOmitField var reqTime: Long,
               @BeanProperty var respTime: String) {

  def this() {
    this (null, null, 0L, null)
    this.reqTime = System.currentTimeMillis
  }

  override def toString: String = {
    return "Response{" +
      "result=" + result +
      ", error=" + error +
      ", reqTime=" + reqTime +
      ", respTime=" + respTime + '}'
  }
}