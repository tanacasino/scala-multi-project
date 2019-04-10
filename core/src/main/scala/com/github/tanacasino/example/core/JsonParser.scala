package com.github.tanacasino.example.core

import org.typelevel.jawn.{RawFacade, SimpleFacade, SupportParser}

object JsonParser extends SupportParser[JsonValue] {

  implicit val facade: RawFacade[JsonValue] = new SimpleFacade[JsonValue] {
    def jnull()                                                        = JsonNull
    def jfalse()                                                       = JsonBoolean(false)
    def jtrue()                                                        = JsonBoolean(true)
    def jarray(vs: List[JsonValue])                                    = JsonArray(vs)
    def jobject(vs: Map[String, JsonValue])                            = JsonObject(vs)
    def jnum(s: CharSequence, decIndex: Int, expIndex: Int): JsonValue = JsonNumber(s.toString)
    def jstring(s: CharSequence): JsonValue                            = JsonString(s.toString)
  }

}
