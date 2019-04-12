package com.github.tanacasino.example.core

import scala.util.Try

/**
  * JSON AST
  */
sealed trait JsonValue

object JsonValue {

  def obj(values: (String, JsonValue)*): JsonObject =
    JsonObject(values.toMap)

  def arr(values: JsonValue*): JsonArray =
    JsonArray(values.toList)

  def str(value: String): JsonString =
    JsonString(value)

  def num(value: Int): JsonNumber =
    JsonNumber(value.toString)

  def bool(value: Boolean): JsonBoolean =
    JsonBoolean(value)

}

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
case class JsonNumber(value: String) extends JsonValue {
  def asInt: Try[Int]        = Try(value.toInt)
  def asUnsafeInt: Int       = value.toInt
  def asDouble: Try[Double]  = Try(value.toDouble)
  def asUnsafeDouble: Double = value.toDouble
}

/**
  * null
  */
case object JsonNull extends JsonValue
