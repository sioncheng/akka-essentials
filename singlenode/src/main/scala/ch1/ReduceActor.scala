package ch1

import akka.actor.{Actor, ActorLogging}

class ReduceActor extends Actor with ActorLogging {

	def receive: Receive = {
		case mapData: MapData =>
			val reducedData = reduce(mapData)
			sender ! reducedData
			log.info(s"reducer reduced ${reducedData}")
	}

	def reduce(mapData: MapData) = {
		val reducedData = new scala.collection.mutable.HashMap[String,Int]()

		mapData.wordCounts.foreach(w => 
		    if (reducedData.contains(w.word)) {
		      reducedData(w.word) = reducedData(w.word) + 1
		    } else {
		      reducedData(w.word) = 1
		    }
		)

		ReduceData(reducedData.toMap)
	}
}