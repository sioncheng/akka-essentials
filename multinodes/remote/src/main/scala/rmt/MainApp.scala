package rmt


import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

object MainApp extends App {
	println("hello remote actor")

	val config = ConfigFactory.load().getConfig("RemoteSys")
	val system = ActorSystem("RemoteNodeApp", config)
	println(config)

	val af = system.actorOf(Props[RemoteActor], name="remoteActor")
	println(s"main remote actor {}", af)
	
	

	Thread.sleep(24 * 60 * 1000)
}
