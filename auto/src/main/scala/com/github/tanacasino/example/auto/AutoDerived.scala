package com.github.tanacasino.example.auto

import com.github.tanacasino.example.core.{Encoder, Json}
import shapeless.{::, HList, HNil}

object AutoDerived {

  // TODO Use ObjectEncoder[A], not Encoder[A]

  implicit object HNilEncoder extends Encoder[HNil] {
    override def encode(a: HNil): Json = Json.obj()
  }

  implicit def hListEncoder[H, T <: HList](implicit hEncoder: Encoder[H], tEncoder: Encoder[T]): Encoder[H :: T] = {
    Encoder.instance {
      case h :: t =>
        hEncoder.encode(h) ++ tEncoder.encode(t)
    }
  }

  implicit val hogeEncoder: Encoder[String :: Int :: Boolean :: HNil] = Encoder.apply

  val hogeJson = hogeEncoder.encode("hoge" :: 30 :: true :: HNil)

}
