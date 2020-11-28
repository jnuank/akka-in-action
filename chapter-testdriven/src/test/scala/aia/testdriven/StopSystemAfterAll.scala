package aia.testdriven

import org.scalatest.{ Suite, BeforeAndAfterAll }
import akka.testkit.TestKit

// trait トレイト名 extends 継承元
trait StopSystemAfterAll extends BeforeAndAfterAll { 

  // 自分型アノテーション
  // this: ミックスインできる先の型の指定
  // TODO: with Sutieってなんだろう
  this: TestKit with Suite =>
  override protected def afterAll(): Unit = {
    super.afterAll()
    // implicit val system = ActorSystem() ということをやっているわけだろうか。
    // ↓ ここ、本だとshutdown() なんだよね。やっていることは一緒なんだろうけど。
    system.terminate()
  }
}

