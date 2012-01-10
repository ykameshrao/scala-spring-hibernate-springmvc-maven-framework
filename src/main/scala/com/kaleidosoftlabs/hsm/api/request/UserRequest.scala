package com.kaleidosoftlabs.hsm.api.request

import javax.xml.bind.annotation.{XmlElement, XmlRootElement}


/**
 * Request object to create/update a User
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 8:28 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
@XmlRootElement(name = "user")
class UserRequest {
  @XmlElement
  var username: String = null

  @XmlElement
  var password: String = null

  @XmlElement
  var email: String = null

  @XmlElement
  var mobile: String = null

  @XmlElement
  var role: String = null
}