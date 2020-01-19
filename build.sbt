import Dependencies._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "telegramGifBot",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "com.bot4s" %% "telegram-core" % "4.4.0-RC2",
    libraryDependencies += "io.github.cdimascio" % "java-dotenv" % "5.1.3"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
