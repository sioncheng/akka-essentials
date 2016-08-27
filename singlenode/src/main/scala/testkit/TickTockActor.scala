package testkit

import akka.actor.Actor

case class Tick(message: String)
case class Tock(message: String)

class TickTockActor extends Actor {

	var state = false

	def receive: Receive = {
		case message: Tick => tick(message)
		case message: Tock => tock(message)
		case _ => throw new IllegalArgumentException("boom!")
	}

	def tick(message: Tick) = {
		sender.tell("processed the tick message", self)
	}

	def tock(message: Tock) = {
		state = (!state)
	}
}