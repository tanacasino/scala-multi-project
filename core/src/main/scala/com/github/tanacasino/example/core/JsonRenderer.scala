package com.github.tanacasino.example.core

import scala.collection.mutable

/**
  * See https://github.com/typelevel/jawn/blob/master/ast/src/main/scala/jawn/ast/Renderer.scala
  */
object JsonRenderer {

  final def render(jv: Json): String = {
    val sb = new StringBuilder
    render(sb, depth = 0, jv = jv)
    sb.toString
  }

  final def render(sb: StringBuilder, depth: Int, jv: Json): Unit =
    jv match {
      case JsonNull       => sb.append("null")
      case JsonBoolean(b) => if (b) sb.append("true") else sb.append("false")
      case JsonNumber(n)  => sb.append(n)
      case JsonString(s)  => renderString(sb, s)
      case JsonArray(vs)  => renderArray(sb, depth, vs)
      case JsonObject(vs) => renderObject(sb, depth, canonicalizeObject(mutable.Map(vs.toSeq: _*)))
    }

  def canonicalizeObject(vs: mutable.Map[String, Json]): Iterator[(String, Json)] =
    vs.iterator

  def renderString(sb: StringBuilder, s: String): Unit =
    escape(sb, s, unicode = false)

  final def renderArray(sb: StringBuilder, depth: Int, vs: List[Json]): Unit = {
    if (vs.isEmpty) return {
      sb.append("[]")
      ()
    }
    sb.append("[")
    render(sb, depth + 1, vs.head)
    var i = 1
    while (i < vs.length) {
      sb.append(",")
      render(sb, depth + 1, vs(i))
      i += 1
    }
    sb.append("]")
  }

  final def renderObject(sb: StringBuilder, depth: Int, it: Iterator[(String, Json)]): Unit = {
    if (!it.hasNext) return {
      sb.append("{}")
      ()
    }
    val (k0, v0) = it.next
    sb.append("{")
    renderString(sb, k0)
    sb.append(":")
    render(sb, depth + 1, v0)
    while (it.hasNext) {
      val (k, v) = it.next
      sb.append(",")
      renderString(sb, k)
      sb.append(":")
      render(sb, depth + 1, v)
    }
    sb.append("}")
  }

  final def escape(sb: StringBuilder, s: String, unicode: Boolean): Unit = {
    sb.append('"')
    var i   = 0
    val len = s.length
    while (i < len) {
      (s.charAt(i): @scala.annotation.switch) match {
        case '"'  => sb.append("\\\"")
        case '\\' => sb.append("\\\\")
        case '\b' => sb.append("\\b")
        case '\f' => sb.append("\\f")
        case '\n' => sb.append("\\n")
        case '\r' => sb.append("\\r")
        case '\t' => sb.append("\\t")
        case c =>
          if (c < ' ' || (c > '~' && unicode)) sb.append("\\u%04x" format c.toInt)
          else sb.append(c)
      }
      i += 1
    }
    sb.append('"')
  }

}
