package com.github.tanacasino.example.core

object JsonRenderer {

  final def render(json: Json): String = {
    val sb = new StringBuilder
    render(json, sb)
    sb.toString()
  }

  final def render(json: Json, sb: StringBuilder): Unit = {
    json match {
      case JsonObject(values) =>
        sb.append("{")
        var i = 0
        values.foreach {
          case (key, value) =>
            sb.append("\"")
            sb.append(key)
            sb.append("\"")
            sb.append(":")
            render(value, sb)
            i += 1
            if (i < values.size) {
              sb.append(",")
            }
        }
        sb.append("}")
      case JsonArray(values) =>
        sb.append("[")
        var i = 0
        values.foreach { value =>
          render(value, sb)
          i += 1
          if (i < values.length) {
            sb.append(",")
          }
        }
        sb.append("]")
      case JsonString(str) =>
        renderString(str, sb)
      case JsonNumber(value) =>
        sb.append(value)
      case JsonBoolean(value) =>
        if (value) {
          sb.append("true")
        } else {
          sb.append("false")
        }
      case JsonNull =>
        sb.append("null")
    }
  }

  def renderString(value: String, sb: StringBuilder): Unit = {
    sb.append("\"")
    var i   = 0
    val len = value.length
    while (i < len) {
      value.charAt(i) match {
        case '"'  => sb.append("\\\"")
        case '\\' => sb.append("\\\\")
        case '\b' => sb.append("\\b")
        case '\f' => sb.append("\\f")
        case '\n' => sb.append("\\n")
        case '\r' => sb.append("\\r")
        case '\t' => sb.append("\\t")
        case c =>
          if (c < ' ') {
            sb.append("\\u%04x" format c.toInt)
          } else {
            sb.append(c)
          }
      }
      i += 1
    }
    sb.append("\"")
  }

}
