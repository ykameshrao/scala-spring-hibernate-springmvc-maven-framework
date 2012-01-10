package com.kaleidosoftlabs.hsm.system.actor

import com.kaleidosoftlabs.util.Log
import akka.actor.{Actor}

/**
 * Testing Akka Actors
 *
 * @author: Y Kamesh Rao
 * @created: 12/3/11 3:50 PM
 * @company: &copy; 2011-2012, Kaleidosoft Labs
 */
class TestActor extends Actor with Log {
  def receive = {
    case "Hello" => log.info("Hiya! there")
    case _ => log.info("Didn't get you")
  }
}