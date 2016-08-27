package ch1

import akka.actor.{ActorRef, ActorSystem,Props}
import scala.concurrent.Await
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

object Main extends App {

	println("map reduce in actor")

	val statement1 = "what are you doing now?"
	val statement2 = "do you like scala?"
	val statement3 = "akka is awsome!"

	val system = ActorSystem("mapreduceinactor")
	val masterActor = system.actorOf(Props[MasterActor])

	masterActor ! statement1
	masterActor ! statement2
	masterActor ! statement3

	Thread.sleep(1000)

	implicit val timeout = Timeout(2 seconds)
	val future = masterActor ? Result()
	val result = Await.result(future, timeout.duration).asInstanceOf[String]
  
  	println(result)

	system.shutdown()
}