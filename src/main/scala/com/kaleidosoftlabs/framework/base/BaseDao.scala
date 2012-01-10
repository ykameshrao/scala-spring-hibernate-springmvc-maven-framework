package com.kaleidosoftlabs.framework.base

/**
 * Data Access Object (DAO) interface. This is an interface used to tag our DAO
 * classes and to provide common methods to all DAOs.
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 1:23 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
trait BaseDao[T] {
  /**
   * Method to insert the new row into config.database table
   *
   * @param obj The object entity to be persisted
   */
  def insert(obj: T): Unit

  /**
   * Method to update an existing row in the config.database table
   *
   * @param obj The object entity to be updated
   */
  def update(obj: T): Unit

  /**
   * Method to insert a new row or update a row if it was
   * already existing in the system.
   *
   * @param obj The object entity to be updated
   */
  def insertOrUpdate(obj: T): Unit

  /**
   * Method to delete an existing row in the config.database table
   *
   * @param obj The object entity to be deleted
   */
  def delete(obj: T): Unit

  /**
   * Method to find a config.database item by id
   *
   * @param id     The id by which the row has to be found
   * @param nqName The name of the NamedQuery to be executed
   */
  def findById(id: Int, query: String): List[T]

  /**
   * Method to find a config.database item by id
   *
   * @param id     The id by which the row has to be found
   * @param nqName The name of the NamedQuery to be executed
   */
  def findById(id: Long, query: String): List[T]
}