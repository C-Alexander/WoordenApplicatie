package woordenapplicatie.string

import java.util.concurrent.atomic.AtomicInteger

import scala.collection.mutable.{HashMap, ListBuffer}
import scala.collection.immutable.SortedSet
import scala.collection.{SetProxy, mutable}


object Tool {
  private val time:Stopwatch = Stopwatch()
  private val REGEX:String = "\\, |\\. |\\.\n\n|\\.\n| |\n\n|\n|\\."// using \\W+ is better, but stutters at accents

  /**
    *
    * @param text text to count words of
    * @return tuple containing (count, countUnique) as ints.
    */
  def count(text:String): (Int, Int) = {
    time.measureTime {
      val strs: Array[String] = text.toLowerCase.split(REGEX)
      (strs.length, strs.toSet.size)
    }
  }

  def sortAlphabetically(text:String): String = {
    time.measureTime {
      (SortedSet[String]()(Ordering[String].reverse) ++ text.toLowerCase.split(REGEX)).mkString("\n")
    }
  }

  def frequence(text:String): String = {
    time.measureTime {
      val map: mutable.HashMap[String, AtomicInteger] = mutable.HashMap[String, AtomicInteger]() //sadly has to be mutable if we want don't want to manually count every word later...
      text.toLowerCase.split(REGEX).foreach(s => map.getOrElseUpdate(s, new AtomicInteger(0)).incrementAndGet()) //record occurrence of each word
      // AtomicInteger method results for 290000 words: 195, 100, 104, 101, 113, 135, standard put results: 288, 232, 236, 204, 265, 262. Winner winner chicken dinner!

      val sortedMap: mutable.SortedMap[Int, ListBuffer[String]] = mutable.SortedMap[Int, ListBuffer[String]]()
      map.foreach(m => sortedMap.getOrElseUpdate(m._2.intValue(), ListBuffer()) += (m._2.toString + "\t" + m._1 + "\n"))

      sortedMap.foldLeft(new StringBuilder) { (s, m) => s.append(m._2.mkString("")) }.toString()
    }
  }

  def concordance(text:String): String = {
    time.measureTime {
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
