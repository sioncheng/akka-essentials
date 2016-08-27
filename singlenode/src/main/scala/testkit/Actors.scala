package testkit

import akka.actor.{Actor, ActorRef}

class EchoActor extends Actor {
	def receive: Receive = {
		case message => 
			sender ! message
	}
}

class ForwardActor(next: ActorRef) extends Actor {
	def receive: Receive = {
		case message => next ! message
	}
}

class SequencingActor(next: ActorRef, head:List[Integer], tail: List[Integer]) extends Actor {
     def receive: Receive = {
       case message =>
         head map (next ! _)
         next ! message
         tail map (next ! _)
	}
}