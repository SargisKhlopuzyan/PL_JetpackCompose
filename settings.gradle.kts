pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PL_JetpackCompose"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") //TODO

include(":app")
include(":presentation")
include(":domain")
include(":data")
include(":commonUi")
include(":kotlinlang")
