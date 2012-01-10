package com.kaleidosoftlabs.framework.exception.auth

import com.kaleidosoftlabs.framework.base.BaseException

/**
 * Exception indicating Auth credentials missing.
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 2:18 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
case class AuthCredentialsMissingException(message: String) extends BaseException