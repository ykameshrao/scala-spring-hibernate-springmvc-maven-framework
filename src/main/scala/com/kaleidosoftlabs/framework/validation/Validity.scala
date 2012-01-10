package com.kaleidosoftlabs.framework.validation

/**
 * Class to store the status of an entity validation
 * to respond the request in best possible and standard way
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 1:57 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class Validity(var valid: Boolean, var errors: Array[String]) {
  def this(valid: Boolean) {
    this (valid, null)
  }


  def errorMsgs: AnyRef = {
    if (errors.size > 1) return errors
    else return errors(0)
  }


  def isValid: Boolean = {
    return valid
  }


  def setValid(valid: Boolean): Unit = {
    this.valid = valid
  }


  def getErrors: Array[String] = {
    return errors
  }


  def setErrors(errors: Array[String]): Unit = {
    this.errors = errors
  }
}