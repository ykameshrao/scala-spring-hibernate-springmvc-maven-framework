package com.kaleidosoftlabs.util

import org.apache.log4j.Logger

/**
 * This class is meant to log all the app messages to Server Logger Console/File
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:32 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
trait Log {
  def log = Logger.getLogger(this.getClass)
}