
lazy val root =
  (project in file("."))
    .enablePlugins(PlayScala)
    .settings(
      name         := "siofoki-pets",
      organization := "com.example",

      version      := "1.0-SNAPSHOT",

      scalaVersion := "2.13.3",

      scalacOptions := Seq(
        // Feature options
        "-encoding", "utf-8",
        "-explaintypes",
        "-feature",
        "-Ymacro-annotations",

        // Warnings as errors!
        "-Xfatal-warnings",
        "-Wconf:src=app/models/db/.*:s,site=models\\.db\\.Tables.*:s,cat=unused-imports&src=.*/twirl/main/views/.*\\.template\\.scala:s",

        // Linting options
        "-unchecked",
        "-Xcheckinit",
        "-Xlint:adapted-args",
        "-Xlint:constant",
        "-Xlint:delayedinit-select",
        // "-Xlint:deprecation", // causes unfilterable slick generated code warning at the moment...
        "-Xlint:doc-detached",
        "-Xlint:inaccessible",
        "-Xlint:infer-any",
        "-Xlint:missing-interpolator",
        "-Xlint:nullary-unit",
        "-Xlint:option-implicit",
        "-Xlint:package-object-classes",
        "-Xlint:poly-implicit-overload",
        "-Xlint:private-shadow",
        "-Xlint:stars-align",
        "-Xlint:type-parameter-shadow",
        "-Wdead-code",
        "-Wextra-implicit",
        "-Wnumeric-widen",
        "-Wunused:implicits",
        "-Wunused:imports",
        "-Wunused:locals",
        "-Wunused:params",
        "-Wunused:patvars",
        "-Wunused:privates",
        "-Wvalue-discard",
      ),

      libraryDependencies ++= Seq(
        guice,
        "com.typesafe.play"      %% "play-slick"            % "5.0.0",
        "com.typesafe.play"      %% "play-slick-evolutions" % "5.0.0",
        "com.typesafe.slick"     %% "slick"                 % "3.3.3",
        "org.scalaz"             %% "scalaz-core"           % "7.3.2",
        // Test
        "org.scalatestplus.play" %% "scalatestplus-play"    % "5.0.0" % Test
      )

    )

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
