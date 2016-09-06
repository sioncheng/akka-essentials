package rmt

import akka.actor.{Actor, ActorLogging}

class RemoteActor extends Actor with ActorLogging{

	def receive: Receive = {
		case message: String =>
			log.info(s"remote actor {}", message)
			sender ! "give you back " + message
	}
}