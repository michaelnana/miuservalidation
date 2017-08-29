name := """architech-user"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.mockito" % "mockito-all" % "1.9.5",
  cache,
  javaWs
)
