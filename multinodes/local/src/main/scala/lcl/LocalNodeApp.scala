package lcl

import akka.actor.{Actor, ActorRef, Props, ActorSystem}
import com.typesafe.config.ConfigFactory

object LocalNodeApp extends App {

	val config = ConfigFactory.load().getConfig("LocalSys")
	val system = ActorSystem("localNodeApp", config)

	val clientActor = system.actorOf(Props[LocalActor])
	println("client actor", clientActor)
	clientActor ! "Hello"

	Thread.sleep(4000)

	system.shutdown()

}