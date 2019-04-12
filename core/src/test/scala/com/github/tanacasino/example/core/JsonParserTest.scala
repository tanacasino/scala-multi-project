package com.github.tanacasino.example.core

import org.scalatest.{FlatSpec, Matchers, TryValues}

class JsonParserTest extends FlatSpec with Matchers with TryValues {

  "A JsonParser" should "parseFromString" in {
    val expected = JsonValue.obj(
      "id"        -> JsonValue.num(100),
      "name"      -> JsonValue.str("Tarou"),
      "hasFriend" -> JsonValue.bool(false),
      "tags" -> JsonValue.arr(
        JsonValue.str("aaa"),
        JsonValue.num(5),
        JsonValue.bool(true)
      )
    )
    val jsonString = """{"id": 100, "name": "Tarou", "hasFriend": false, "tags": ["aaa", 5, true]}"""

    JsonParser.parseFromString(jsonString).success.value shouldBe expected
  }

}
