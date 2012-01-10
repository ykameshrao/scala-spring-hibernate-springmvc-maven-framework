package com.kaleidosoftlabs.framework.base

import javax.persistence._
import java.util.Date
import reflect.BeanProperty


/**
 * Entity to be extended by sub classes for
 * basic entities
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 12:50 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
@MappedSuperclass
abstract class BaseEntity {
  @Id @GeneratedValue
  @BeanProperty var id: Long = 0

  @Column @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty var createdAt: Date = new Date()

  @Column @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty var updatedAt: Date = new Date()

  def updateTimestamp = updatedAt = new Date()
}