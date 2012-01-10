package com.kaleidosoftlabs.hsm.model.entity.embedded

import javax.persistence.Embeddable

/**
 * Stores the general address elements
 * 
 * @author: Y Kamesh Rao
 * @created: 12/3/11 4:31 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
@Embeddable
case class Address(var streetAddress: String,
                   var city: String,
                   var state: String,
                   var country: String,
                   var postalCode: String)