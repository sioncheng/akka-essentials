package ch1

import akka.actor.{Actor, ActorLogging}

class MapActor extends Actor with ActorLogging {

	def receive:Receive = {
		case message: String =>
		    val em = evaluateMessage(message)
			sender ! em
			log.info(s"mapper mapped ${em}")
	}

	def evaluateMessage(message: String) : MapData = {
		val splitteredMsg = message.split(" ")
		def punctuationFilter(x: String) = {
			x.replace("!","").replace(".","").replace(",","")
		}
		val mappedData = splitteredMsg.withFilter(x => x != "").map(x => WordCount(punctuationFilter(x),1))
		MapData(mappedData)
	}
}