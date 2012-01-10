package com.kaleidosoftlabs.framework.exception.database

import com.kaleidosoftlabs.framework.base.BaseException

/**
 * Exception to denote that the requested entity is not found in the database
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:09 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
case class NotFoundException(message: String) extends BaseException