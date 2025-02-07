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
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "ProfitX"
include(":app")
include(":core")
include(":feature")
include(":feature:register")
include(":feature:auth")
include(":feature:auth:api")
include(":feature:auth:impl")
include(":feature:register:api")
include(":feature:register:impl")
include(":feature:profile")
include(":feature:analysis")
include(":feature:profile:api")
include(":feature:profile:impl")
include(":feature:analysis:api")
include(":feature:analysis:impl")
include(":feature:income")
include(":feature:income:api")
include(":feature:income:impl")
include(":feature:expenses")
include(":feature:expenses:api")
include(":feature:expenses:impl")
