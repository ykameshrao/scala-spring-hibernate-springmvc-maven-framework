package com.kaleidosoftlabs.hsm.system.actor

import com.kaleidosoftlabs.util.Log
import akka.actor.{TypedActor, Actor}

/**
 * Testing typed Akka actors
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 11:40 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
trait TestTypedActor {
  def pingTestTypedActor(msg: String): Unit
}

class TestTypedActorImpl extends TypedActor with TestTypedActor with Log {
  def pingTestTypedActor(msg: String) {
    log.info("Received message: " + msg)
  }
}