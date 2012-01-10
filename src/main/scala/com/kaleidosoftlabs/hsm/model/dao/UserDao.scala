package com.kaleidosoftlabs.hsm.model.dao

import com.kaleidosoftlabs.hsm.model.entity.User
import com.kaleidosoftlabs.framework.base.{BaseHibernateDao, BaseDao}
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.hibernate.SessionFactory

/**
 * Class to implement the methods of the Dao interface. The most
 * basic methods for this operation are implemented in the BaseHibernateDao
 * automatically. All other additional method implementation goes in here.
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 4:39 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
trait UserDao extends BaseDao[User] {
  def findByUserName(userName: String): List[User]
}

@Repository("userDao")
class UserDaoImpl extends BaseHibernateDao[User] with UserDao {
  def findByUserName(userName: String): List[User] = {
    getHibernateTemplate.find("from User u where u.userName = ?", userName).toArray.toList.asInstanceOf[List[User]]
  }
}