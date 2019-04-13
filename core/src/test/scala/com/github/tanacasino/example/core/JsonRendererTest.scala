package com.github.tanacasino.example.core

import org.scalatest.{FlatSpec, Matchers}

class JsonRendererTest extends FlatSpec with Matchers {

  "A JsonRenderer" should "render" in {
    val json = Json.obj(
      "name"      -> Json.str("Tarou"),
      "age"       -> Json.num(33),
      "hasFriend" -> Json.bool(true),
      "tags" -> Json.arr(
        Json.str("string"),
        Json.bool(false),
        Json.num(10.1),
        Json.num(10),
        JsonNull,
        Json.obj(
          "key1" -> Json.obj(
            "key1-1" -> Json.str("value1-1")
          ),
          "key2" -> Json.num(10000)
        )
      )
    )
    val expected =
      """{"name":"Tarou","age":33,"hasFriend":true,"tags":["string",false,10.1,10,null,{"key1":{"key1-1":"value1-1"},"key2":10000}]}"""
    val actual = SimpleJsonRenderer.render(json)
    println(actual)
    actual shouldBe expected
  }

}
