package com.kaleidosoftlabs.framework.exception.service

import com.kaleidosoftlabs.framework.base.BaseException

/**
 * Exception indicating service implementation missing
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:11 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
case class NotYetImplementedException(message: String) extends BaseException