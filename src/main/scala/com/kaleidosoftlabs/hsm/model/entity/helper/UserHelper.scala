package com.kaleidosoftlabs.hsm.model.entity.helper

import com.kaleidosoftlabs.util.Log
import com.kaleidosoftlabs.hsm.api.common.Errors
import com.kaleidosoftlabs.hsm.model.entity.enum.Role
import javax.servlet.http.HttpServletRequest
import com.kaleidosoftlabs.hsm.model.entity.User
import java.util.Date
import com.kaleidosoftlabs.util.Log
import javax.validation.{Validator, ConstraintViolation, Validation, ValidatorFactory}
import java.util.{Set}
import scala.collection.JavaConversions._
import org.hibernate.validator.{InvalidValue, ClassValidator}
import org.apache.commons.lang.WordUtils
import com.kaleidosoftlabs.framework.validation.Validity
import collection.mutable.MutableList


/**
 *
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 8:57 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
object UserHelper extends Log {
  private val roleMap = Map('super_admin -> Role.SuperAdmin,
    'customer_admin -> Role.CustomerAdmin,
    'app_user -> Role.AppUser)

  /**
   * Method to create a new User object
   *
   * @param uname
   * @param pass
   * @param email
   * @param role
   * @return
   */
  def createUser(uname: String,
                 pass: String,
                 email: String,
                 mobile: String,
                 role: String,
                 req: HttpServletRequest): User = {
    val user: User = new User
    user.userName = uname
    user.passWord = pass
    user.email = email
    user.mobile = mobile
    user.currentLoginAt = new Date
    user.currentLoginIp = req.getRemoteAddr
    roleMap.get(Symbol(role.trim.toLowerCase)) match {
      case Some(r) => user.role = r.toString
      case None => user.role = Role.InvalidRole.toString
    }

    user
  }


  /**
   * Method to create the SHA hash of the password before storing
   *
   * @param pass
   * @return SHA hash digest of the password
   */
  def hashPassword(pass: String): String = {
    org.apache.commons.codec.digest.DigestUtils.shaHex(pass)
  }


  /**
   * Checks if the given password for the user is valid or not
   *
   * @param pass
   * @return Boolean value indicating success or failure
   */
  def isValidPass(user: User, pass: String): Boolean = {
    user.passWord == hashPassword(pass)
  }


  /**
   * Method to validate a user object
   *
   * @param user
   * @return
   */
  def hibernateValidator(user: User): MutableList[String] = {
    log.info("Validating Hibernate: " + user)
    var err: MutableList[String] = new MutableList[String]()
    val userValidator: ClassValidator[User] = new ClassValidator[User](classOf[User])
    val userErrs: Array[InvalidValue] = userValidator.getInvalidValues(user)
    log.info("Hibernate Validity Errors: " + userErrs.length)
    for (invalidValue <- userErrs) {
      val msg = WordUtils.capitalize(invalidValue.getPropertyPath) + " " + invalidValue.getMessage
      err += msg
    }
    return err
  }


  /**
   * Validate the user object
   *
   * @param user
   * @return
   */
  def validate(user: User, apiErrors: Errors): Validity = {
    val validatorFactory: ValidatorFactory = Validation.buildDefaultValidatorFactory
    val validator: Validator = validatorFactory.getValidator
    val violations: Set[ConstraintViolation[User]] = validator.validate(user)
    var err: MutableList[String] = new MutableList[String]()

    for (violation <- violations) {
      val propertyPath: String = violation.getPropertyPath.toString.toLowerCase
      val message: String = violation.getMessage
      val errMsg: String = WordUtils.capitalize(propertyPath) + " " + message
      log.info("Invalid Value: '" + errMsg)
      err += errMsg.toString
    }

    if (user.role == Role.InvalidRole.toString) {
      err += apiErrors.invRoleMsg.toString
    }

    hibernateValidator(user).foreach(msg => err += msg.toString)

    return new Validity(err.flatten.isEmpty, err.toArray)
  }
}