package telegram

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._


class HtmlParser(gifUrl: String) {

  def call(Url: String) = {
    val browser = JsoupBrowser()
    val doc = browser.get("https://giphy.com/gifs/friday-friyay-1APaqOO5JHnWKLc7Bi")
    val items = doc >> elementList("img[src*=\"giphy\"]")
    items
  }

  def parseGifUrl = {
    val regx = "-([a-zA-Z0-9]*$)".r
    val result = regx.findAllIn(gifUrl)
    s"https://i.giphy.com/${result.group(1)}.gif"
  }

}
