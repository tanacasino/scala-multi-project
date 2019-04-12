package com.github.tanacasino.example.core

import org.scalatest.{FlatSpec, Matchers}

class JsonRendererTest extends FlatSpec with Matchers {

  "A JsonRenderer" should "render" in {
    val json = Json.obj(
      "name"      -> Json.str("Tarou"),
      "age"       -> Json.num(33),
      "hasFriend" -> Json.bool(true)
    )
    val expected = """{"age":33,"name":"Tarou","hasFriend":true}"""
    val actual   = JsonRenderer.render(json)
    actual shouldBe expected
  }

}
