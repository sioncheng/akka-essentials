package ch1

import akka.actor.{Actor, ActorLogging}

class AggregateActor extends Actor with ActorLogging {
	val aggregatedData = new scala.collection.mutable.HashMap[String,Int]()

	def receive: Receive = {
		case reducedData: ReduceData =>
			aggregate(reducedData)
			log.info(s"aggregator aggregated ${aggregatedData}")
		case r: Result =>
			sender ! aggregatedData.toString
			log.info(s"aggregator response result ${aggregatedData.toString}")
	}

	def aggregate(reducedData: ReduceData) {
		reducedData.reducedData.foreach(x=>{
			if(aggregatedData.contains(x._1)) {
				aggregatedData(x._1) = aggregatedData(x._1) + x._2
			} else {
				aggregatedData(x._1) = x._2
			}
		})
	}
}