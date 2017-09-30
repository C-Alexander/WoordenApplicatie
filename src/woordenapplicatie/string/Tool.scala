package woordenapplicatie.string

import java.util
import java.util.concurrent.atomic.AtomicInteger

import scala.collection.SortedMap
import scala.collection.immutable.SortedSet
import scala.collection.mutable.HashMap

object Tool {
  private val REGEX:String = "\\, |\\. |\\.\n\n|\\.\n| |\n\n|\n|\\."

  def occurences(text:String): (Int, Int) = {
//  var map:HashMap[String, AtomicInteger] = new mutable.HashMap[String, AtomicInteger]()
//  text.split(Array('\n', ',', ' ')).foreach(s =>  map.getOrElseUpdate(s.toLowerCase, new AtomicInteger(0)).incrementAndGet())
  var s:String = new String()
   var strs:Array[String] = text.toLowerCase.split("\\, | |\n\n|\n|\\.")
   (strs.length, strs.toSet.size)

}
  /**
    *
    * @param text text to count words of
    * @return tuple containing (count, countUnique) as ints.
    */
  def count(text:String): (Int, Int) = {
    val strs: Array[String] = text.toLowerCase.split(REGEX)
    (strs.length, strs.toSet.size)
  }

  def sortAlphabetically(text:String): String = {
//    var words:SortedSet[String] = new SortedSet[String](text.toLowerCase.split("\\, |\\. |\\.\n\n|\\.\n| |\n\n|\n|\\."))
    //   text.toLowerCase.split("\\, |\\. |\\.\n\n|\\.\n| |\n\n|\n|\\.").to[SortedSet].toString() cool!
   // text.toLowerCase.split("\\, |\\. |\\.\n\n|\\.\n| |\n\n|\n|\\.").toSet.
    (SortedSet[String]()(Ordering[String].reverse) ++ text.toLowerCase.split(REGEX)).mkString("\n")
  }

  def frequence(text:String): String = {
//      var map:HashMap[String, AtomicInteger] = new HashMap[String, AtomicInteger]()
//      text.split(REGEX).foreach(s =>  map.getOrElseUpdate(s.toLowerCase, new AtomicInteger(0)).incrementAndGet())
//    (SortedSet[Int]()(Ordering[Int]) ++ map).mkString("\n")
var map:SortedMap[String, AtomicInteger] = SortedMap[String, AtomicInteger]()(Ordering[Int])
  }
}
