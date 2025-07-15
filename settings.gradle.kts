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
// This for modules to make it possible use implementation(projects.data), etc
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":presentation")
include(":domain")
include(":data")
include(":commonUi")
include(":kotlinlang")
include(":pl")
