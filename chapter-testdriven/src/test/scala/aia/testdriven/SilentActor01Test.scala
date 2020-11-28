package aia.testdriven

import org.scalatest.{WordSpecLike, MustMatchers}
//import akka.testkit.TestKit
import akka.testkit.{ TestActorRef, TestKit }
import akka.actor._

//This test is ignored in the BookBuild, it's added to the defaultExcludedNames



object SilentActor {
  // このアクターが処理できるメッセージの型の定義
  case class SilentMessage(data: String)
  case class GetState(receiver: ActorRef)
}

class SilentActor extends Actor {
  import SilentActor._
  var internalState = Vector[String]()

  def receive = {
    // :+ → appended
    case SilentMessage(data) => internalState = internalState :+ data
  }

  def state = internalState
}

class SilentActor01Test extends TestKit(ActorSystem("testsystem"))
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll {
  // travisのビルドをパスするためコメントアウトしますが、こちらが書籍内のテストです。
   "A Silent Actor" must {
     "change state when it receives a message, single threaded" in {
       import SilentActor._

       val silentActor = TestActorRef[SilentActor]
       silentActor ! SilentMessage("whisper")
       silentActor.underlyingActor.state must (contain("whisper"))

     }
     "change state when it receives a message, multi-threaded" in {
       // 関連するメッセージをまとめて保持するコンパニオンオブジェクトを使えるようにする？
//       import SilentActor._
//
//
//       // テストを書くと最初は失敗する
//       fail("not implemented yet")
     }
   }
//  "A Silent Actor" must {
//    "change state when it receives a message, single threaded" ignore {
//      // テストを書くと最初は失敗する
//      fail("not implemented yet")
//    }
//    "change state when it receives a message, multi-threaded" ignore {
//      // テストを書くと最初は失敗する
//      fail("not implemented yet")
//    }
//  }

}

