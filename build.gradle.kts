plugins {
    `version-catalog`
    `maven-publish`
    signing
}

group = "kr.disdong"
version = "0.0.8"

catalog {
    versionCatalog {
        from(files("libs.version.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            groupId = project.group as String
            artifactId = "spring-version-catalog"
            version = project.version as String
            from(components["versionCatalog"])
        }
    }

    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/disdong123/version-catalog")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("DISDONG_USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("DISDONG_TOKEN")
            }
        }
    }
}