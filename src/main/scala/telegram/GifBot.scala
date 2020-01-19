package telegram
import java.nio.file.{Files, Paths}

import cats.instances.future._
import cats.syntax.functor._
import com.bot4s.telegram.api.RequestHandler
import com.bot4s.telegram.api.declarative.Commands
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.future.{Polling, TelegramBot}
import com.bot4s.telegram.methods.SendAnimation
import com.bot4s.telegram.models.{InputFile, Message}
import slogging.{LogLevel, LoggerConfig, PrintLoggerFactory}

import scala.concurrent.Future
import scala.util.Try

class GifBot(val token: String, val gifPath: String) extends TelegramBot with Polling with Commands[Future] {
  LoggerConfig.factory = PrintLoggerFactory()
  // set log level, e.g. to TRACE
  LoggerConfig.level = LogLevel.TRACE

  // Use sttp-based backend
  override val client: RequestHandler[Future] = new ScalajHttpClient(token)

  val rng = new scala.util.Random(System.currentTimeMillis())


  def handleMessage(message: Message) = {

    val byteArray = Files.readAllBytes(Paths.get(gifPath))
    val inputFile = InputFile.apply("my.gif", byteArray)
    request(SendAnimation(message.chat.id, inputFile)).void
  }

  onMessage(handleMessage)


  onCommand("coin" or "flip") { implicit msg =>
    reply(if (rng.nextBoolean()) "Head!" else "Tail!").void
  }
  onCommand('real | 'double | 'float) { implicit msg =>
    reply(rng.nextDouble().toString).void
  }
  onCommand("/dice" | "roll") { implicit msg =>
    reply("⚀⚁⚂⚃⚄⚅" (rng.nextInt(6)).toString).void
  }
  onCommand("random" or "rnd") { implicit msg =>
    withArgs {
      case Seq(Int(n)) if n > 0 =>
        reply(rng.nextInt(n).toString).void
      case _ => reply("Invalid argumentヽ(ಠ_ಠ)ノ").void
    }
  }
  onCommand('choose | 'pick | 'select) { implicit msg =>
    withArgs { args =>
      replyMd(if (args.isEmpty) "No arguments provided." else args(rng.nextInt(args.size))).void
    }
  }

  // Int(n) extractor
  object Int {
    def unapply(s: String): Option[Int] = Try(s.toInt).toOption
  }
}
