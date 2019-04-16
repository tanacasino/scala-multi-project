package com.github.tanacasino.example.core

import scala.util.Try

/**
  * JSON AST
  */
sealed trait Json

object Json {

  def obj(values: (String, Json)*): JsonObject =
    JsonObject(values.toMap)

  def arr(values: Json*): JsonArray =
    JsonArray(values.toList)

  def str(value: String): JsonString =
    JsonString(value)

  def num(value: Int): JsonNumber =
    JsonNumber(value.toString)

  def num(value: Double): JsonNumber =
    JsonNumber(value.toString)

  def bool(value: Boolean): JsonBoolean =
    JsonBoolean(value)

}

/**
  *  JSON object {}
  * @param values Key and Json Value
  */
case class JsonObject(values: Map[String, Json]) extends Json {

  def ++(that: JsonObject): JsonObject =
    JsonObject(values ++ that.values)

}

/**
  * JSON array []
  * @param values json values array
  */
case class JsonArray(values: List[Json]) extends Json

/**
  * true or false
  */
case class JsonBoolean(value: Boolean) extends Json

/**
  * string
  */
case class JsonString(value: String) extends Json

/**
  * number
  */
case class JsonNumber(value: String) extends Json {
  def asInt: Try[Int]        = Try(value.toInt)
  def asUnsafeInt: Int       = value.toInt
  def asDouble: Try[Double]  = Try(value.toDouble)
  def asUnsafeDouble: Double = value.toDouble
}

/**
  * null
  */
case object JsonNull extends Json
