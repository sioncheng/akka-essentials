package testkit

import akka.actor.{Props, Actor, ActorSystem}
import akka.testkit.{TestKit, TestActorRef, ImplicitSender, TestProbe}
import org.scalatest.{WordSpecLike, BeforeAndAfterAll}
import org.scalatest.matchers.MustMatchers
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Await

class ActorsTest 
	extends TestKit(ActorSystem("TickTockActorTest"))
	with ImplicitSender //must with this to get testActor
	with WordSpecLike
	with MustMatchers
	with BeforeAndAfterAll {

	override def afterAll() {
		system.shutdown()
	}

	"EchoActor" should {
		"send back message" in {
			val echoActor = system.actorOf(Props[EchoActor])
			echoActor ! "hello" //sender is testActor
			expectMsg("hello")
		}
	}

	"ForwardActor" should {
		"forward message to next" in {
			val forwardActor = system.actorOf(Props(new ForwardActor(this.testActor)))
			forwardActor ! "hello"
			expectMsg("hello")
		}
	}

	"SequencingActor" should {
		"checks for one message" in {
			import scala.util.Random

			val randomHead = Random.nextInt(5)
			val randomTail = Random.nextInt(8)
			val headList = Range(randomHead, 5).map(_ * -1).toList
			val tailList = Range(randomTail, 8).map(_ * 2).toList
			val seqActor = system.actorOf(Props(classOf[SequencingActor], 
				testActor, 
				headList, 
				tailList))

			ignoreMsg {
          		case msg: Integer => {
          			println(s"**** ignoreMsg ${msg}")
          			true
          		}
        	}
	        seqActor ! "something"
	        expectMsg("something")
	        ignoreMsg {
	          case msg: Integer => {
	          	println(s"==== ignoreMsg ${msg}")
	          	true
	          }
	        }
	        expectNoMsg
	        ignoreNoMsg
		}
	}
}