package com.kaleidosoftlabs.hsm.api.common

import org.springframework.context.annotation.{ImportResource, Configuration}
import org.springframework.beans.factory.annotation.Value


/**
 * Entity to hold messages from property files
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 5:53 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
@Configuration
@ImportResource(Array("classpath:config/spring/applicationContext-properties.xml"))
class Errors {
  @Value("#{apiProps['br.msg']}") var brMsg: String = null
  @Value("#{apiProps['br.code']}") var brCode: String = null
  @Value("#{apiProps['ua.msg']}") var uaMsg: String = null
  @Value("#{apiProps['ua.code']}") var uaCode: String = null
  @Value("#{apiProps['nf.msg']}") var nfMsg: String = null
  @Value("#{apiProps['nf.code']}") var nfCode: String = null
  @Value("#{apiProps['mna.msg']}") var mnaMsg: String = null
  @Value("#{apiProps['mna.code']}") var mnaCode: String = null
  @Value("#{apiProps['rt.msg']}") var rtMsg: String = null
  @Value("#{apiProps['rt.code']}") var rtCode: String = null
  @Value("#{apiProps['umt.msg']}") var umtMsg: String = null
  @Value("#{apiProps['umt.code']}") var umtCode: String = null
  @Value("#{apiProps['ise.msg']}") var iseMsg: String = null
  @Value("#{apiProps['ise.code']}") var iseCode: String = null
  @Value("#{apiProps['ni.msg']}") var niMsg: String = null
  @Value("#{apiProps['ni.code']}") var niCode: String = null
  @Value("#{apiProps['su.msg']}") var suMsg: String = null
  @Value("#{apiProps['su.code']}") var suCode: String = null
  @Value("#{apiProps['cmAuth.msg']}") var cmAuthMsg: String = null
  @Value("#{apiProps['cmAuth.code']}") var cmAuthCode: String = null
  @Value("#{apiProps['iucAuth.msg']}") var iucAuthMsg: String = null
  @Value("#{apiProps['iucAuth.code']}") var iucAuthCode: String = null
  @Value("#{apiProps['nfUser.msg']}") var nfUserMsg: String = null
  @Value("#{apiProps['nfUser.code']}") var nfUserCode: String = null
  @Value("#{apiProps['invRole.msg']}") var invRoleMsg: String = null
  @Value("#{apiProps['invRole.code']}") var invUserCode: String = null
}