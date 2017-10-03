package woordenapplicatie.string

import java.util.concurrent.atomic.AtomicInteger

import scala.collection.mutable.{HashMap, ListBuffer}
import scala.collection.immutable.SortedSet
import scala.collection.{SetProxy, mutable}

/**
  * A tool to get various useless statistics from text
  */
object Tool extends Splitter with Stopwatch {

  /**
    * Function that counts the words in a string
    * @param text text to count words of
    * @return tuple containing (count, countUnique) as ints.
    */
  def count(text:String): (Int, Int) = {
    measureTime {
      val strs: Array[String] = split(text)
      (strs.length, strs.toSet.size)
    }
  }

  /**
    * Function that sorts the text's words alphabetically (with Z at first, A at the end), newlines separating each word.
    * @param text The text to sort alphabetically
    * @return The sorted text
    */
  def sortAlphabetically(text:String): String = {
    measureTime {
      (SortedSet[String]()(Ordering[String].reverse) ++ split(text)).mkString("\n")
    }
  }

  /**
    * Calculates the frequence of words in text
    * @param text The text to get the frequence from
    * @return A string of the words, with their frequence next to them, separated by newlines
    */
  def frequence(text:String): String = {
    measureTime {
      val map: mutable.HashMap[String, AtomicInteger] = mutable.HashMap[String, AtomicInteger]() //sadly has to be mutable if we want don't want to manually count every word later...
      split(text).foreach(s => map.getOrElseUpdate(s, new AtomicInteger(0)).incrementAndGet()) //record occurrence of each word
      // AtomicInteger method results for 290000 words: 195, 100, 104, 101, 113, 135, standard put results: 288, 232, 236, 204, 265, 262. Winner winner chicken dinner!

      val sortedMap: mutable.SortedMap[Int, ListBuffer[String]] = mutable.SortedMap[Int, ListBuffer[String]]()
      map.foreach(m => sortedMap.getOrElseUpdate(m._2.intValue(), ListBuffer()) += (m._2.toString + "\t" + m._1 + "\n"))

      sortedMap.foldLeft(new StringBuilder) { (s, m) => s.append(m._2.mkString("")) }.toString()
    }
  }

  /**
    * Calculates the concordance of words in a text
    * @param text to get the word concordance of
    * @return a string of words, with their concordance next to them
    */
  def concordance(text:String): String = {
    measureTime {
      val map: mutable.HashMap[String, mutable.SortedSet[Int]] = new mutable.HashMap[String, mutable.SortedSet[Int]]
      val word: mutable.StringBuilder = new mutable.StringBuilder() //5.1 is the average amount of letters per world, is it too insane to make this the initial size and make the normal increase the deviation?
      var line: Int = 1

      for (char <- text + ',') {
        char match {
          case '\n' | ',' | ' ' | '.' =>
            if (word.nonEmpty) {
              map.getOrElseUpdate(word.toString(), mutable.SortedSet[Int]()) += line
              word.clear()
            }
            if (char == '\n') line += 1
          case letter => word append letter
        }
      }
      word.clear()
      for (x <- map) {
        word.append(x._1 + '\t' + x._2.mkString("[", ", ", "]" + '\n'))
      }
      word.toString()
    }
  }
    //overriding a set is easy, just use setproxy! Sadly now deprecated. Damn you, BDFL Odersky!
}
