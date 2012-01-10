package com.kaleidosoftlabs.framework.exception.auth

import com.kaleidosoftlabs.framework.base.BaseException

/**
 * Exception indicating Auth failure.
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 2:19 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
case class AuthenticationFailedException(message: String) extends BaseException