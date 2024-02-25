val scala3Version = "3.3.1"
val Http4sVersion = "0.23.25"
val CirceVersion = "0.14.6"
val MunitVersion = "0.7.29"
val LogbackVersion = "1.4.14"
val MunitCatsEffectVersion = "1.0.7"

lazy val root = (project in file("."))
  .settings(
    organization := "microservicios",
    name := "api_rest",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.3.0",
    libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-ember-server" % Http4sVersion,
      "org.http4s"      %% "http4s-ember-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "org.scalameta"   %% "munit"               % MunitVersion           % Test,
      "org.typelevel"   %% "munit-cats-effect-3" % MunitCatsEffectVersion % Test,
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion,
      "org.tpolecat" %% "doobie-hikari" % "1.0.0-RC4", // HikariCP transactor.
      "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC4",  // Postgres driver 42.6.0 + type mappings.
      "com.github.pureconfig" %% "pureconfig-http4s" % "0.17.5",
      "com.github.pureconfig" %% "pureconfig-cats" % "0.17.5",
    ),
    assembly / assemblyMergeStrategy := {
      case "module-info.class" => MergeStrategy.discard
      case x => (assembly / assemblyMergeStrategy).value.apply(x)
    }
  )
