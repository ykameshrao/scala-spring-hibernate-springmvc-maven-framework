package com.kaleidosoftlabs.framework.exception.database

import com.kaleidosoftlabs.framework.base.BaseException

/**
 * Exception to denote that the database fetch operation resulted in zero
 * results for the query. The explicit/possible reasons for this would be
 * rovided by the throwing class.
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:10 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
case class ZeroResultsException(message: String) extends BaseException