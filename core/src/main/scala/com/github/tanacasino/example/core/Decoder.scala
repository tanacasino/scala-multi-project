package com.github.tanacasino.example.core

import scala.util.{Failure, Success}

case class DecodeError(message: String)

sealed trait DecodeResult[A]
case class DecodeSuccess[A](value: A)           extends DecodeResult[A]
case class DecodeFailure[A](error: DecodeError) extends DecodeResult[A]
object DecodeResult {
  def success[A](value: A): DecodeResult[A]        = DecodeSuccess(value)
  def fail[A](error: DecodeError): DecodeResult[A] = DecodeFailure(error)
  def fail[A](message: String): DecodeResult[A]    = fail(DecodeError(message))
}

trait Decoder[A] {

  def decode(json: Json): DecodeResult[A]

}

object Decoder extends DefaultDecoders {

  def apply[A](f: Json => DecodeResult[A]): Decoder[A] = new Decoder[A] {
    override def decode(json: Json): DecodeResult[A] = f(json)
  }

}

trait DefaultDecoders {

  implicit object IntDecoder extends Decoder[Int] {
    override def decode(json: Json): DecodeResult[Int] = json match {
      case num: JsonNumber =>
        num.asInt match {
          case Success(value)     => DecodeResult.success(value)
          case Failure(exception) => DecodeResult.fail(exception.getMessage)
        }
      case _ => DecodeResult.fail("not a number")
    }
  }

  implicit object StringDecoder extends Decoder[String] {
    override def decode(json: Json): DecodeResult[String] = json match {
      case JsonString(value) => DecodeResult.success(value)
      case _                 => DecodeResult.fail("not a string")
    }
  }

  implicit object BooleanDecoder extends Decoder[Boolean] {
    override def decode(json: Json): DecodeResult[Boolean] = json match {
      case JsonBoolean(value) => DecodeResult.success(value)
      case _                  => DecodeResult.fail("not a boolean")
    }
  }

}
