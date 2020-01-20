package telegram

import org.scalatest._
import telegram.TelegramGifBot

class TelegramGifBotSpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    TelegramGifBot.greeting shouldEqual "hello"
  }
}
