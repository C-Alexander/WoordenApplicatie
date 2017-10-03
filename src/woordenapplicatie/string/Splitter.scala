package woordenapplicatie.string

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer}

/**
  * Useful trait for splitting springs (particularly, words) that respects unicode and offers mind-blowingly better performance than Regex
  */
trait Splitter {
  this: Stopwatch =>

  /**
    *
    * @param string to split up
    * @return an array of strings, with ' ' | '\n' | ',' | '.' | '\t' used to split them.
    */
  protected def split(string: String): Array[String] = {
    measureTime ({
      val word = new mutable.StringBuilder()
      val arrayBuffer = new ArrayBuffer[String]()
        for (char <- string.toLowerCase + ',') {
          char match {
            case ' ' | '\n' | ',' | '.' | '\t' =>
              if (word.nonEmpty) {
                arrayBuffer += word.toString
                word.clear()
              }
            case letter => word append letter
          }
        }
      val a = new Array[String](arrayBuffer.size)
      for (i <- arrayBuffer.indices) {
        a(i) = arrayBuffer(i)
      }
      a
    }, "Splitting Strings")
  }
}
