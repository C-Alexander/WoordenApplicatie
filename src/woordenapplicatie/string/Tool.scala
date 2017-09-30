package woordenapplicatie.string

import java.util
import java.util.concurrent.atomic.AtomicInteger

import scala.collection.mutable.{HashMap, ListBuffer}
import scala.collection.immutable.SortedSet
import scala.collection.mutable

object Tool {
  private val REGEX:String = "\\, |\\. |\\.\n\n|\\.\n| |\n\n|\n|\\."

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
    (SortedSet[String]()(Ordering[String].reverse) ++ text.toLowerCase.split(REGEX)).mkString("\n")
    //    var words:SortedSet[String] = new SortedSet[String](text.toLowerCase.split("\\, |\\. |\\.\n\n|\\.\n| |\n\n|\n|\\."))
    //   text.toLowerCase.split("\\, |\\. |\\.\n\n|\\.\n| |\n\n|\n|\\.").to[SortedSet].toString() cool!
    // text.toLowerCase.split("\\, |\\. |\\.\n\n|\\.\n| |\n\n|\n|\\.").toSet.
  }

  def frequence(text:String): String = {
    val map:mutable.HashMap[String, AtomicInteger] = new mutable.HashMap[String, AtomicInteger]()
    text.toLowerCase.split(REGEX).foreach(s =>  map.getOrElseUpdate(s.toLowerCase, new AtomicInteger(0)).incrementAndGet())

    val sortedMap:mutable.SortedMap[Int, ListBuffer[String]] = mutable.SortedMap[Int, ListBuffer[String]]()
    map.foreach(m => sortedMap.getOrElseUpdate(m._2.intValue(), ListBuffer()) += (m._2.toString + "\t" + m._1 + "\n"))

     sortedMap.foldLeft(new StringBuilder) {(s, m) => s.append(m._2.mkString(""))}.toString() //m._2.mkString(s"\t ${m._1} \n") + "\n") pretty, but flawed
   // sortedMap.foreach(m => m._1.toString)


    //   map.foreach(m => sortedMap += m._2.intValue() -> m._1)
    //    sortedMap += 1 -> ListBuffer("ok!")
    //    sortedMap(1) += "kay"
    //    if (sortedMap.contains(1)) sortedMap.get(1).head += "kay" else sortedMap += 1 -> ListBuffer("as")
    //    map.foreach(m => if(sortedMap.contains(m._2.intValue())) println(sortedMap.get(m._2.intValue()).head) else sortedMap += m._2.intValue() -> ListBuffer(m._1))
    //    (SortedSet[Int]()(Ordering[Int]) ++ map).mkString("\n")
    //var map:SortedMap[String, AtomicInteger] = SortedMap[String, AtomicInteger]()(Ordering[Int])
  }
}
