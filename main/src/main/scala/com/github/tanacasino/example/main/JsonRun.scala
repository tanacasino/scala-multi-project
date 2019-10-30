package com.github.tanacasino.example.main

import com.github.tanacasino.example.core.JsonParser

object JsonRun {

  def main(args: Array[String]): Unit = {
    val jsonString = """{"id": 100, "name": "Tarou", "hasFriend": false}"""
    JsonParser
      .parseFromString(jsonString)
      .fold(
        error => println(s"Failed: ${error.getMessage}"),
        jsonValue => println(s"Success! value=${jsonValue.toString}")
      )
  }

}
