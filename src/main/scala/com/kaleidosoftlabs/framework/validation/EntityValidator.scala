package com.kaleidosoftlabs.framework.validation

import org.apache.commons.lang.WordUtils
import javax.validation.{Validator, ConstraintViolation, Validation, ValidatorFactory}
import java.util.{Set, ArrayList}
import scala.collection.JavaConversions._
import org.hibernate.validator.{InvalidValue, ClassValidator}
import collection.mutable.MutableList

/**
 * Method to commonly validate the Spring and Hibernate
 * entity constraints on an object passed
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 2:05 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class EntityValidator[T] {
  /**
   * Validate the T object
   *
   * @param bean
   * @return
   */
  def validate(bean: T, beanClass: Class[T]): Validity = {
    val validatorFactory: ValidatorFactory = Validation.buildDefaultValidatorFactory
    val validator: Validator = validatorFactory.getValidator
    val violations: Set[ConstraintViolation[T]] = validator.validate(bean)
    var err:MutableList[String] = new MutableList[String]()
    for (violation <- violations) {
      val propertyPath: String = violation.getPropertyPath.toString.toLowerCase
      val message: String = violation.getMessage
      val errMsg: String = WordUtils.capitalize(propertyPath) + " " + message
      err += errMsg
    }
    hValidate(bean, beanClass).foreach(msg => err += msg)
    return new Validity(err.isEmpty, err.toArray)
  }


  /**
   * Validate the passed bean using the hibernate application level
   * validation
   *
   * @param bean
   * @param beanClass
   * @return
   */
  def hValidate(bean: T, beanClass: Class[T]): MutableList[String] = {
    var err:MutableList[String] = new MutableList[String]()
    val hValidator: ClassValidator[T] = new ClassValidator[T](beanClass)
    val hErrs: Array[InvalidValue] = hValidator.getInvalidValues(bean)
    for (invalidValue <- hErrs) {
      val msg = WordUtils.capitalize(invalidValue.getPropertyPath) + " " + invalidValue.getMessage
      err += msg
    }
    return err
  }
}