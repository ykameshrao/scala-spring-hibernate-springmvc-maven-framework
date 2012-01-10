package com.kaleidosoftlabs.hsm.common

/**
 * Named queries to be used in the TeamPilot entities
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 4:27 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
object Queries {
  final val userById = "from User where id = ?"
  final val allUsers = "from User"
}