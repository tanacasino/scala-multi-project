package com.github.tanacasino.example.auto

import org.scalatest.{FlatSpec, Matchers}

import com.github.tanacasino.example.core.{Encoder, Json}

class AutoEncoderTest extends FlatSpec with Matchers {

  import AutoEncoderTest._

  "A GenericEncoder auto derivation" should "encode" in {
    val tarou = Person("Tarou", 30, true)
    val expected = Json.obj(
      "name"      -> Json.str("Tarou"),
      "age"       -> Json.num(30),
      "hasFriend" -> Json.bool(true)
    )
    Encoder.encode(tarou) shouldBe expected
  }

}

object AutoEncoderTest {

  case class Person(name: String, age: Int, hasFriend: Boolean)

}
