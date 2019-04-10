package com.github.tanacasino.example.core

/**
  * JSON AST
  */
sealed trait JsonValue

/**
  *  JSON object {}
  * @param values Key and Json Value
  */
case class JsonObject(values: Map[String, JsonValue]) extends JsonValue

/**
  * JSON array []
  * @param values json values array
  */
case class JsonArray(values: List[JsonValue]) extends JsonValue

/**
  * true or false
  */
case class JsonBoolean(value: Boolean) extends JsonValue

/**
  * string
  */
case class JsonString(value: String) extends JsonValue

/**
  * number
  */
case class JsonNumber(value: String) extends JsonValue

/**
  * null
  */
case object JsonNull extends JsonValue
