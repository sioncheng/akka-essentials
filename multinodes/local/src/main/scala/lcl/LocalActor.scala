package lcl

import akka.actor.{Actor, ActorRef, ActorSystem, Props, ActorLogging}
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.duration._
import scala.concurrent.Await

class LocalActor extends Actor with ActorLogging {

	val remoteActor = context.actorSelection("akka.tcp://RemoteNodeApp@0.0.0.0:2552/user/remoteActor")

	implicit val timeout = Timeout(2 seconds)

	def receive: Receive = {
		case message: String =>
			log.info("prepare to ask remote {} for {}", remoteActor, message)
			val future = (remoteActor ? message).mapTo[String]
			val result = Await.result(future, timeout.duration)
			log.info("message `{}` received from remote {}", result, remoteActor)
	}
}