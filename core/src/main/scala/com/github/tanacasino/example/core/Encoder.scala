package com.github.tanacasino.example.core

trait Encoder[A] { self =>

  def encode(a: A): Json

  def contramap[B](f: B => A): Encoder[B] = (b: B) => self.encode(f(b))

}

object Encoder {

  def apply[A](implicit encoder: Encoder[A]): Encoder[A] = encoder

  def encode[A](a: A)(implicit encoder: Encoder[A]): Json = encoder.encode(a)

  def instance[A](f: A => Json): Encoder[A] = new Encoder[A] {
    override def encode(a: A): Json = f(a)
  }

  implicit object StringEncoder extends Encoder[String] {
    override def encode(a: String): Json = Json.str(a)
  }

  implicit object BooleanEncoder extends Encoder[Boolean] {
    override def encode(a: Boolean): Json = Json.bool(a)
  }

  implicit object IntEncoder extends Encoder[Int] {
    override def encode(a: Int): Json = Json.num(a)
  }

  implicit object DoubleEncoder extends Encoder[Double] {
    override def encode(a: Double): Json = Json.num(a)
  }

}
