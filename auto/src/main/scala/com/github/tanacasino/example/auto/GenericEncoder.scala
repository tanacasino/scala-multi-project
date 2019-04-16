package com.github.tanacasino.example.auto

import shapeless.{LabelledGeneric, Lazy}

import com.github.tanacasino.example.core.{Encoder, JsonObjectEncoder}

trait GenericEncoder {

  implicit def genericJsonObjectEncoder[A, H](
      implicit
      generic: LabelledGeneric.Aux[A, H],
      hEncoder: Lazy[JsonObjectEncoder[H]]
  ): Encoder[A] =
    JsonObjectEncoder { value =>
      hEncoder.value.encode(generic.to(value))
    }

}
