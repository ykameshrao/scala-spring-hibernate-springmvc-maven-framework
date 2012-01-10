package com.kaleidosoftlabs.hsm.service

import com.kaleidosoftlabs.framework.exception.database.NotFoundException
import com.kaleidosoftlabs.hsm.model.entity.User
import com.kaleidosoftlabs.hsm.model.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import com.kaleidosoftlabs.framework.base.{BaseServiceImpl, BaseService}
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * Service class to have business logic operation on User entity
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 4:54 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
trait UserService extends BaseService[User] {
  /**
   * Service to find a user by username
   *
   * @param userName The username to be searched for
   */
  def findByUserName(userName: String): User
}


@Service
@Transactional
class UserServiceImpl extends UserService with BaseServiceImpl[User] {
  @Autowired
  val userDao: UserDao = null

  @PostConstruct
  def setupBaseServiceDao = dao = userDao


  def findByUserName(userName: String): User = {
    val res = userDao.findByUserName(userName)
    res match {
      case r: List[User] => r.head
      case _ => throw NotFoundException("User was not found")
    }
  }
}

