package com.github.tanacasino.example.core

trait JsonObjectEncoder[A] extends Encoder[A] { self =>

  def encode(a: A): JsonObject

}

object JsonObjectEncoder {

  def apply[A](f: A => JsonObject): JsonObjectEncoder[A] =
    new JsonObjectEncoder[A] {
      override def encode(a: A): JsonObject = f(a)
    }

}
