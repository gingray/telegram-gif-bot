package telegram

import org.scalatest.{FlatSpec, Matchers}

class GiphyDownloaderSpec extends FlatSpec with Matchers {
  "GiphyDownloader" should "create url properly" in {
    val url = "https://giphy.com/gifs/friday-friyay-1APaqOO5JHnWKLc7Bi"
    val parser = new GiphyDownloader(url)
    parser.generateDownloadUrl(url) shouldEqual Some("https://i.giphy.com/1APaqOO5JHnWKLc7Bi.gif")
  }

  "GiphyDownloader" should "download gif and return byte array" in {
    val url = "https://giphy.com/gifs/friday-friyay-1APaqOO5JHnWKLc7Bi"
    val parser = new GiphyDownloader(url)

    parser.call shouldBe a[Array[Byte]]
  }
}
