package com.github.tanacasino.example.core

import org.typelevel.jawn.{RawFacade, SimpleFacade, SupportParser}

object JsonParser extends SupportParser[Json] {

  implicit val facade: RawFacade[Json] = new SimpleFacade[Json] {
    def jnull()                                                   = JsonNull
    def jfalse()                                                  = JsonBoolean(false)
    def jtrue()                                                   = JsonBoolean(true)
    def jarray(vs: List[Json])                                    = JsonArray(vs)
    def jobject(vs: Map[String, Json])                            = JsonObject(vs)
    def jnum(s: CharSequence, decIndex: Int, expIndex: Int): Json = JsonNumber(s.toString)
    def jstring(s: CharSequence): Json                            = JsonString(s.toString)
  }

}
