import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	kotlin("jvm") version "1.3.40"
	idea
	id("fabric-loom") version "0.2.5-SNAPSHOT"
}

base {
	archivesBaseName = ext["archive-base-name"].toString()
}

val minecraft: String by ext


val minecraft_version: String by project
val yarn_mappings: String by project
val loader_version: String by project
val fabric_version: String by project
val fabric_api: String by project
val fabric_kotlin: String by project

val towelette_version: String by project
val cotton_version: String by project
val artifice_version: String by project
val rei_version: String by project

val modVersion = ext["mod-version"] ?: error("Version was null")
val localBuild = ext["local-build"].toString().toBoolean()
version = "$modVersion+$minecraft" + if (localBuild) "-local" else ""

if (localBuild) {
	println("Note: local build mode enabled in gradle.properties; all dependencies might not work!")
}

allprojects {
	apply(plugin = "java")

	java {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	repositories {
		mavenCentral()
		if (localBuild) {
			mavenLocal()
		}

        maven (url = "https://maven.fabricmc.net/")
        maven(url = "https://minecraft.curseforge.com/api/maven") {
            name = "CurseForge"
        }

		// For cotton, polyester and json-factory
		maven(url = "http://server.bbkr.space:8081/artifactory/libs-release")
		maven(url = "http://server.bbkr.space:8081/artifactory/libs-snapshot")

		// For Artifice
		maven (url = "https://maven.swordglowsblue.com" )

        maven (url = "http://maven.sargunv.s3-website-us-west-2.amazonaws.com/")
        jcenter()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}

	tasks.getByName<ProcessResources>("processResources") {
		inputs.property("version", project.version)
		filesMatching("fabric.mod.json") {
			expand(
					mutableMapOf(
							"version" to project.version
					)
			)
		}
	}
}

minecraft {
}

inline fun DependencyHandler.modCompileAndInclude(str: String, block: ExternalModuleDependency.() -> Unit = {}) {
	modCompile(str, block)
	include(str, block)
}

inline fun DependencyHandler.includedMod(str: String, block: ExternalModuleDependency.() -> Unit = {}) {
    modImplementation(str, block)
    include(str, block)
}

inline fun DependencyHandler.includedMod(group: String, name: String, version: String, block: ExternalModuleDependency.() -> Unit = {}) {
    modImplementation(group, name, version, dependencyConfiguration = block)
    include(group, name, version, dependencyConfiguration = block)
}

dependencies {
	/**
	 * Gets a version string with the [key].
	 */
	fun v(key: String) = ext[key].toString()

	minecraft("com.mojang:minecraft:$minecraft")
	mappings("net.fabricmc:yarn:$yarn_mappings")

	// Fabric
    modCompile("net.fabricmc:fabric-loader:$loader_version")
    modCompile("net.fabricmc.fabric-api:fabric-api:$fabric_api")
	modCompile("net.fabricmc:fabric-language-kotlin:$fabric_kotlin")
	compileOnly("net.fabricmc:fabric-language-kotlin:$fabric_kotlin")

	// Other mods
	modCompileAndInclude("towelette:Towelette:$towelette_version")
	modCompileAndInclude("io.github.cottonmc:cotton:$cotton_version")

    // Artifice
    modImplementation("artificemc:artifice:$artifice_version")
    include("artificemc:artifice:$artifice_version")

	// Roughly Enough Items
	modCompile("me.shedaniel:RoughlyEnoughItems:$rei_version")

	// Other libraries
	compileOnly("org.apiguardian:apiguardian-api:1.0.0")
}