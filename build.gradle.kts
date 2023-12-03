plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.4.0"
  id("xyz.jpenilla.run-paper") version "2.0.1" // Adds runServer and runMojangMappedServer tasks for testing
}

group = "de.elia"
version = "1.1.1"
description = "The Boss Fight Plugin für Soul"

java {
  // Configure the java toolchain. This allows gradle to auto-provision JDK 19 on systems that only have JDK 8 installed for example.
  toolchain.languageVersion.set(JavaLanguageVersion.of(19))
}

repositories {
  mavenCentral()
  maven { setUrl("https://maven.enginehub.org/repo/")}
  flatDir {
    dirs("library")
  }
}

dependencies {
  paperDevBundle("1.20.1-R0.1-SNAPSHOT")
  implementation("com.sk89q.worldedit:worldedit-bukkit:7.2.16-SNAPSHOT")
  implementation("de.elia.api:SoullLibrary:3.0.0")
  implementation("org.apache.logging.log4j:log4j-api:2.20.0")
  implementation("org.apache.logging.log4j:log4j-core:2.20.0")
  paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.20.1-R0.1-SNAPSHOT")
  // paperweightDevBundle("com.example.paperfork", "1.19.4-R0.1-SNAPSHOT")

  // You will need to manually specify the full dependency if using the groovy gradle dsl
  // (paperDevBundle and paperweightDevBundle functions do not work in groovy)
}

tasks {
  // Configure reobfJar to run when invoking the build task
  assemble {
    dependsOn(reobfJar)
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

    // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
    // See https://openjdk.java.net/jeps/247 for more information.
    options.release.set(19)
  }
  javadoc {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
  }
  processResources {
    filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
  }

}
