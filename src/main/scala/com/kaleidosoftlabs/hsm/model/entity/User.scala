package com.kaleidosoftlabs.hsm.model.entity

import java.util.Date
import com.thoughtworks.xstream.annotations.XStreamOmitField
import com.kaleidosoftlabs.framework.base.BaseEntity
import com.kaleidosoftlabs.hsm.model.entity.enum.Role._
import javax.persistence._
import com.kaleidosoftlabs.hsm.common.Queries
import org.codehaus.jackson.annotate.JsonIgnore
import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.{Length, NotEmpty, Email}
import reflect.BeanProperty

/**
 *
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 4:10 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
class User extends BaseEntity {
  @Column(unique = true) @NotBlank @NotEmpty @Length(min = 6, max = 20)
  @BeanProperty var userName: String = null

  @Column @Email @NotBlank @NotEmpty
  @BeanProperty var email: String = null

  @Column
  @BeanProperty var mobile: String = null

  @Column
  @BeanProperty var role: String = null

  @Column @XStreamOmitField @JsonIgnore @Length(min = 6)
  var passWord: String = null

  @Column
  @BeanProperty var loginCount: Int = 0

  @Column
  @BeanProperty var currentLoginAt: Date = null

  @Column
  @BeanProperty var lastLoginAt: Date = null

  @Column
  @BeanProperty var currentLoginIp: String = null

  @Column
  @BeanProperty var lastLoginIp: String = null
}