package aia.testdriven

import org.scalatest.{ Suite, BeforeAndAfterAll }
import akka.testkit.TestKit

// trait トレイト名 extends 継承元
trait StopSystemAfterAll extends BeforeAndAfterAll { 

  // 自分型アノテーション
  // this: ミックスインできる先の型の指定
  // TestKit と Suite を合成している。 withは。
  this: TestKit with Suite =>
  override protected def afterAll(): Unit = {
    // TestKit と Suiteのものが扱える
    super.afterAll()
    // implicit val system = ActorSystem() ということをやっているわけだろうか。
    // ↓ ここ、本だとshutdown() なんだよね。やっていることは一緒なんだろうけど。
    system.terminate()
  }
}

