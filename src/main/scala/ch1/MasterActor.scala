package ch1

import akka.actor.{Actor,Props, ActorLogging}

class MasterActor extends Actor  with ActorLogging {

	val mapActor = context.actorOf(Props[MapActor])
	val reduceActor = context.actorOf(Props[ReduceActor])
	val aggregateActor = context.actorOf(Props[AggregateActor])


	def receive:Receive = {
		case message: String =>
			mapActor ! message
			log.info(s"master map ${message}")
		case mapedData: MapData =>
			reduceActor ! mapedData
			log.info(s"master reduce ${mapedData}")
		case reducedData: ReduceData =>
			aggregateActor ! reducedData
			log.info(s"master aggregate ${reducedData}")
		case r: Result =>
			aggregateActor.forward(r)
			log.info(s"master forward ${r}")
	}
}