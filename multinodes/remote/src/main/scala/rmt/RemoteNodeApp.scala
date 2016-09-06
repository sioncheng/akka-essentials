package rmt

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import akka.kernel.Bootable


class RemoteNodeApp extends Bootable {

	val config = ConfigFactory.load().getConfig("RemoteSys")
	val system = ActorSystem("RemoteNodeApp", config)

	def startup = {
		val af = system.actorOf(Props[RemoteActor], name="remoteActor")
		println(s"{}", af)
	}

	def shutdown = {
		system.shutdown()
	}
}