package com.kaleidosoftlabs.hsm.model.entity.enum

/**
 * Types of users saved in this entity based on their role
 * Helps in controlling access to system elements.
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 4:16 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
object Role extends Enumeration {
  type Role = Value
  val SuperAdmin, CustomerAdmin, AppUser, InvalidRole = Value
}