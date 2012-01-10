package com.kaleidosoftlabs.framework.base

import org.springframework.orm.hibernate3.support.HibernateDaoSupport
import org.springframework.beans.factory.annotation.Autowired
import org.hibernate.{SessionFactory, FlushMode}

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common methods that they might all use. Can be used for standard CRUD
 * operations. This class uses the spring hibernate support for db ops.
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 1:22 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class BaseHibernateDao[T] extends HibernateDaoSupport with BaseDao[T] {
  @Autowired
  def init(factory: SessionFactory) {
      setSessionFactory(factory);
  }

  def insert(obj: T) {
    getHibernateTemplate.getSessionFactory.getCurrentSession.setFlushMode(FlushMode.AUTO)
    getHibernateTemplate.save(obj)
    getHibernateTemplate.flush()
  }


  def update(obj: T) {
    // Update the timestamp is obj is a subclass of BaseEntity
    if(obj.isInstanceOf[BaseEntity]) obj.asInstanceOf[BaseEntity].updateTimestamp

    getHibernateTemplate.getSessionFactory.getCurrentSession.setFlushMode(FlushMode.AUTO)
    getHibernateTemplate.saveOrUpdate(obj)
    getHibernateTemplate.flush()
  }


  def insertOrUpdate(obj: T) {
    getHibernateTemplate.getSessionFactory.getCurrentSession.setFlushMode(FlushMode.AUTO)
    getHibernateTemplate.saveOrUpdate(obj)
    getHibernateTemplate.flush()
  }


  def delete(obj: T) {
    getHibernateTemplate.getSessionFactory.getCurrentSession.setFlushMode(FlushMode.AUTO)
    getHibernateTemplate.delete(obj)
    getHibernateTemplate.flush()
  }


  def findById(id: Int, query: String): List[T] =
    getHibernateTemplate.find(query, id).toArray.toList.asInstanceOf[List[T]]


  def findById(id: Long, query: String): List[T] =
    getHibernateTemplate.find(query, id).toArray.toList.asInstanceOf[List[T]]


  def flushedTransaction(codeInFlushedMode: => Unit) = {
    getHibernateTemplate.getSessionFactory.getCurrentSession.setFlushMode(FlushMode.AUTO)
    codeInFlushedMode
    getHibernateTemplate.flush()
  }
}