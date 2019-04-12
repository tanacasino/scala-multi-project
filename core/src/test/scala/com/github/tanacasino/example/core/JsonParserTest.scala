package com.github.tanacasino.example.core

import org.scalatest.{FlatSpec, Matchers, TryValues}

class JsonParserTest extends FlatSpec with Matchers with TryValues {

  "A JsonParser" should "parseFromString" in {
    val expected = Json.obj(
      "id"        -> Json.num(100),
      "name"      -> Json.str("Tarou"),
      "hasFriend" -> Json.bool(false),
      "tags" -> Json.arr(
        Json.str("aaa"),
        Json.num(5),
        Json.bool(true)
      )
    )
    val jsonString = """{"id": 100, "name": "Tarou", "hasFriend": false, "tags": ["aaa", 5, true]}"""

    JsonParser.parseFromString(jsonString).success.value shouldBe expected
  }

}
