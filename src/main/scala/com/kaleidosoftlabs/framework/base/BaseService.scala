package com.kaleidosoftlabs.framework.base

/**
 * Interface listing the most basic services required to be present in any
 * service built upon an entity object that is persisted to the database
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 1:38 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
trait BaseService[T] {
  /**
   * Service to insert the new object
   *
   * @param object The newly object
   */
  def insert(obj: T): Unit

  /**
   * Service to update an existing object
   *
   * @param object The existing object
   */
  def update(obj: T): Unit

  /**
   * Service to delete an existing object
   *
   * @param object The existing object
   */
  def delete(obj: T): Unit

  /**
   * Service to find an existing object by its given id and query name
   *
   * @param id Id of the resource
   * @param query Named query to be executed
   */
  def findById(id: Int, query: String): T

  /**
   * Service to find an existing object by its given id and query name
   *
   * @param id Id of the resource
   * @param query Named query to be executed
   */
  def findById(id: Long, query: String): T
}