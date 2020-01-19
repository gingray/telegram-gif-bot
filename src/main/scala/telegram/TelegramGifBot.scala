package telegram

import scala.concurrent.Await
import scala.concurrent.duration.Duration


object TelegramGifBot extends Greeting with App {
  println(greeting)

  import io.github.cdimascio.dotenv.Dotenv

  val dotenv = Dotenv.load
  val bot = new GifBot(dotenv.get("TELEGRAM_KEY"), dotenv.get("FILE_PATH_GIF"))
  val eol = bot.run()
  println("Press [ENTER] to shutdown the bot, it may take a few seconds...")
  println(dotenv.get("FILE_PATH_GIF"))

  scala.io.StdIn.readLine()
  bot.shutdown() // initiate shutdown
  // Wait for the bot end-of-life
  Await.result(eol, Duration.Inf)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
