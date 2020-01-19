package example

import org.scalatest.{FlatSpec, Matchers}
import telegram.HtmlParser

class HtmlParserSpec extends FlatSpec with Matchers {
  "Parser" should "create url properly" in {
    val parser = new HtmlParser("https://giphy.com/gifs/friday-friyay-1APaqOO5JHnWKLc7Bi")
    parser.parseGifUrl shouldEqual "https://i.giphy.com/1APaqOO5JHnWKLc7Bi.gif"
  }
}
