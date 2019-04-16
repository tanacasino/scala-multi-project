package com.github.tanacasino.example.core

trait Encoder[A] { self =>

  def encode(a: A): Json

  def contramap[B](f: B => A): Encoder[B] = (b: B) => self.encode(f(b))

}

object Encoder extends DefaultEncoders {

  def apply[A](f: A => Json): Encoder[A] = new Encoder[A] {
    override def encode(a: A): Json = f(a)
  }

  def encode[A](a: A)(implicit encoder: Encoder[A]): Json = encoder.encode(a)

  def get[A](implicit encoder: Encoder[A]): Encoder[A] = encoder

}

trait DefaultEncoders {

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
