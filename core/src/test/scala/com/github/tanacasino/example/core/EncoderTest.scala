package com.github.tanacasino.example.core

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class EncoderTest extends AnyFlatSpec with Matchers {

  import EncoderTest._

  "A Encoder" should "encode default types" in {
    Encoder.encode(1) shouldBe Json.num(1)
  }

  it should "encode case class with companion implicit" in {
    val expected = Json.obj(
      "name"      -> Json.str("Tarou"),
      "age"       -> Json.num(33),
      "hasFriend" -> Json.bool(false)
    )

    val person = Person("Tarou", 33, false)

    Encoder.encode(person) shouldBe expected
  }

}

object EncoderTest {

  case class Person(name: String, age: Int, hasFriend: Boolean)
  object Person {
    implicit val encoder: Encoder[Person] = new Encoder[Person] {
      override def encode(a: Person): Json = Json.obj(
        "name"      -> Encoder.encode(a.name),
        "age"       -> Encoder.encode(a.age),
        "hasFriend" -> Encoder.encode(a.hasFriend)
      )
    }
  }

}
