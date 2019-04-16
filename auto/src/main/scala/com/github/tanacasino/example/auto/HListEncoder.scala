package com.github.tanacasino.example.auto

import shapeless.labelled.FieldType
import shapeless.{::, HList, HNil, Lazy, Witness}

import com.github.tanacasino.example.core.{Encoder, Json, JsonObject, JsonObjectEncoder}

trait HListEncoder {

  implicit object HNilJsonObjectEncoder extends JsonObjectEncoder[HNil] {
    override def encode(a: HNil): JsonObject = Json.obj()
  }

  implicit def hListJsonObjectEncoder[K <: Symbol, H, T <: HList](
      implicit
      witness: Witness.Aux[K],
      hEncoder: Lazy[Encoder[H]],
      tEncoder: JsonObjectEncoder[T]
  ): JsonObjectEncoder[FieldType[K, H] :: T] = {
    val fieldName: String = witness.value.name
    JsonObjectEncoder {
      case head :: tail =>
        val headJson = hEncoder.value.encode(head)
        val tailJson = tEncoder.encode(tail)
        JsonObject(Map(fieldName -> headJson)) ++ tailJson
    }
  }

}
