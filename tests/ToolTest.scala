import org.scalatest.{FunSuite, GivenWhenThen}
import woordenapplicatie.string.Tool

class ToolTest extends FunSuite with GivenWhenThen {
  val fontys_text: String = "Een, twee, drie, vier\n" + "Hoedje van, hoedje van\n" + "Een, twee, drie, vier\n" + "Hoedje van papier\n" + "\n" + "Heb je dan geen hoedje meer\n" + "Maak er één van bordpapier\n" + "Eén, twee, drie, vier\n" + "Hoedje van papier\n" + "\n" + "Een, twee, drie, vier\n" + "Hoedje van, hoedje van\n" + "Een, twee, drie, vier\n" + "Hoedje van papier\n" + "\n" + "En als het hoedje dan niet past\n" + "Zetten we 't in de glazenkas\n" + "Een, twee, drie, vier\n" + "Hoedje van papier"

  test("The user can count the total and unique words in a text") {
    info("As a programmer")
    info("I want to be able get the total and unique words of a string")
    info("So that I can show them to the user")
    Given("A short string with special chars")
    val x = Tool.count(fontys_text)
    Then("it should have the correct number of total words")
    assert(x._1 == 68)
    And("it should have the correct number of unique words")
    assert(x._2 == 28)
  }

  test("The user can get the frequence of words in a text") {
    info("As a programmer")
    info("I want to be able get the frequence of words in a string")
    info("So that I can show them to the user")
    Given("A short string with special chars")
    val x = Tool.frequence(fontys_text)
    Then("it should return the correct text")
    assert(x == "1\tzetten\n1\tmeer\n1\ten\n1\tje\n1\tpast\n1\tmaak\n1\tglazenkas\n1\tbordpapier\n1\tde\n1\tin\n1\twe\n1\tgeen\n1\teén\n1\tniet\n1\téén\n1\theb\n1\thet\n1\ter\n1\t't\n1\tals\n2\tdan\n4\tpapier\n5\teen\n6\tdrie\n6\tvier\n6\ttwee\n9\tvan\n10\thoedje\n")
  }

  test("The user can get the concordance of words a text") {
    info("As a programmer")
    info("I want to be able get the concordance of words in a string")
    info("So that I can show them to the user")
    Given("A short string with special chars")
    val x = Tool.concordance(fontys_text)
    Then("it should return the correct text")
    assert(x == "Zetten\t[17]\nmeer\t[6]\ndrie\t[1, 3, 8, 11, 13, 18]\nje\t[6]\npast\t[16]\nvier\t[1, 3, 8, 11, 13, 18]\nglazenkas\t[17]\nEén\t[8]\nbordpapier\t[7]\nde\t[17]\nvan\t[2, 4, 7, 9, 12, 14, 19]\nin\t[17]\npapier\t[4, 9, 14, 19]\nHoedje\t[2, 4, 9, 12, 14, 19]\nwe\t[17]\ngeen\t[6]\nhoedje\t[2, 6, 12, 16]\ndan\t[6, 16]\nMaak\t[7]\nHeb\t[6]\nniet\t[16]\nEen\t[1, 3, 11, 13, 18]\néén\t[7]\nEn\t[16]\nhet\t[16]\n't\t[17]\ner\t[7]\ntwee\t[1, 3, 8, 11, 13, 18]\nals\t[16]\n")
  }

  test("The user can get the alphabetical order of words in a text") {
    info("As a programmer")
    info("I want to be able get the concordance of words in a string")
    info("So that I can show them to the user")
    Given("A short string with special chars")
    val x = Tool.sortAlphabetically(fontys_text)
    Then("it should return the correct text")
    assert(x == "één\nzetten\nwe\nvier\nvan\ntwee\npast\npapier\nniet\nmeer\nmaak\nje\nin\nhoedje\nhet\nheb\nglazenkas\ngeen\neén\ner\nen\neen\ndrie\nde\ndan\nbordpapier\nals\n't")
  }
}
