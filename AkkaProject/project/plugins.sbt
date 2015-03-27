addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.2.0")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += Classpaths.sbtPluginReleases

//resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.0.4")
