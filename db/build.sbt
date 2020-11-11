
val slickVersion = "3.3.3"

lazy val dbGenerate =
  (project in file("."))
    .settings(
      scalaVersion := "2.13.3",

      scalacOptions ++= Seq("-feature"),

      libraryDependencies ++= Seq(
        "com.typesafe.slick" %% "slick"                % slickVersion,
        "com.typesafe.slick" %% "slick-codegen"        % slickVersion,
        "mysql"              %  "mysql-connector-java" % "8.0.22",
      )
    )
