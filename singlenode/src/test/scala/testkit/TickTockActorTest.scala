package testkit

import akka.actor.{Props, Actor, ActorSystem}
import akka.testkit.{TestKit, TestActorRef, ImplicitSender}
import org.scalatest.{WordSpecLike, BeforeAndAfterAll}
import org.scalatest.matchers.MustMatchers
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Await

class TickTockActorTest 
	extends TestKit(ActorSystem("TickTockActorTest")) 
	with WordSpecLike
	with MustMatchers
	with BeforeAndAfterAll {

	override def afterAll() {
		system.shutdown()
	}

	"TickTockActor" should {
		"work with tock message" in {
			val tt = TestActorRef[TickTockActor].underlyingActor
			tt.tock(Tock("hh"))
			///tt.state should be(true)
			assert(tt.state, true)
		}
		"work with tick message" in {
			implicit val timeout = Timeout(5 seconds)
			val tt = TestActorRef[TickTockActor]
			val future = (tt ? Tick("hh")).mapTo[String]
			val result = Await.result(future, timeout.duration)
			assert(result eq "processed the tick message")
		}
		"not work with any others" in {
			val tt = TestActorRef[TickTockActor]
			try {
				tt.receive("ahhahaha")
				fail() //if reach here
			} catch {
				case e: IllegalArgumentException =>
					assert(e.getMessage() eq "boom!")
			}
		}
	}
}

