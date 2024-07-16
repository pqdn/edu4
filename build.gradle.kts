val serviceVersion: String by project

plugins {
	kotlin("jvm") version "1.9.24"
}

version = serviceVersion

allprojects {
	group = "edu.babanin"
}

subprojects {
	apply(plugin = "kotlin")

	repositories {
		mavenCentral()
		mavenLocal()
	}

	kotlin {
		compilerOptions {
			freeCompilerArgs.addAll("-Xjsr305=strict")
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(17)
		}
	}
}



