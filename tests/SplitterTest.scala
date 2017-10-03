
import org.scalatest.{FunSuite, GivenWhenThen}
import woordenapplicatie.string.{Splitter, Stopwatch}

//noinspection ScalaDeprecatedIdentifier
class SplitterTest extends FunSuite with GivenWhenThen with Stopwatch with Splitter {

  val fontys_text: String = "Een, twee, drie, vier\n" + "Hoedje van, hoedje van\n" + "Een, twee, drie, vier\n" + "Hoedje van papier\n" + "\n" + "Heb je dan geen hoedje meer\n" + "Maak er één van bordpapier\n" + "Eén, twee, drie, vier\n" + "Hoedje van papier\n" + "\n" + "Een, twee, drie, vier\n" + "Hoedje van, hoedje van\n" + "Een, twee, drie, vier\n" + "Hoedje van papier\n" + "\n" + "En als het hoedje dan niet past\n" + "Zetten we 't in de glazenkas\n" + "Een, twee, drie, vier\n" + "Hoedje van papier"

  test("The user can split a string into words") {
    info("As a programmer")
    info("I want to be able to split a string into words")
    info("So that I can get use them for further manipulations")
      Given("A short string with special chars")
        val b = split(fontys_text)
      Then("it should have the correct number of words")
      assert(b.length == 68)
      And("it should still have the word één")
      assert(b.contains("één"))
  }
}
