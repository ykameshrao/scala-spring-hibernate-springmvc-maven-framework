package com.kaleidosoftlabs.framework.base

import com.kaleidosoftlabs.framework.exception.database.NotFoundException
import org.springframework.beans.factory.annotation.Autowired

/**
 * Base Implementation of the service class
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 4:57 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
trait BaseServiceImpl[T] extends BaseService[T] {
  var dao: BaseDao[T] = null

  def insert(obj: T) = dao.insert(obj)


  def update(obj: T) = dao.update(obj)


  def delete(obj: T) = dao.delete(obj)


  def findById(id: Int, query: String): T = {
    val res = dao.findById(id, query)
    res match {
      case r: List[T] => r.head
      case _ => throw NotFoundException("Entity not found")
    }
  }


  def findById(id: Long, query: String): T = {
    val res = dao.findById(id, query)
    res match {
      case r: List[T] => r.head
      case _ => throw NotFoundException("Entity not found")
    }
  }
}