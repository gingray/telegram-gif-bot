package telegram

import java.net.URL

import org.apache.commons.io.IOUtils


class GiphyDownloader(gifUrl: String) {
  final val urlTemplate = "https://i.giphy.com/%s.gif"

  def call = {
    val url = generateDownloadUrl(gifUrl)
    val byteArray = url match {
      case Some(value) => IOUtils.toByteArray(new URL(value))
      case None => throw new IllegalArgumentException
    }
    byteArray
  }

  private[telegram] def generateDownloadUrl(url: String): Option[String] = {
    val regx = "-([a-zA-Z0-9]*$)".r
    val result = regx.findAllIn(url)
    if (result.groupCount > 0) {
      Option(urlTemplate.format(result.group(1)))
    }else{
      None
    }
  }

}

object GiphyDownloader {
  val urlTemplate = "https://i.giphy.com/%s.gif"
}
