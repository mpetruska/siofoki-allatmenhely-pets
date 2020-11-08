
lazy val root =
  (project in file("."))
    .enablePlugins(PlayScala)
    .settings(
      name         := "siofoki-pets",
      organization := "com.example",

      version      := "1.0-SNAPSHOT",

      scalaVersion := "2.13.3",

      libraryDependencies ++= Seq(
        guice,
        // Test
        "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
      )

    )

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
