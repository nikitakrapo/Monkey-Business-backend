rootProject.name = "monkey-business-backend"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            "io.ktor".let { ktorGroup ->
                "2.2.1".let { ktorVersion ->
                    library("ktor-server-core-jvm", ktorGroup, "ktor-server-core-jvm").version(ktorVersion)
                    library("ktor-server-content-negotiation-jvm", ktorGroup, "ktor-server-content-negotiation-jvm").version(ktorVersion)
                    library("ktor-serialization-kotlinx-json-jvm", ktorGroup, "ktor-serialization-kotlinx-json-jvm").version(ktorVersion)
                    library("ktor-server-netty-jvm", ktorGroup, "ktor-server-netty-jvm").version(ktorVersion)
                    library("ktor-server-tests-jvm", ktorGroup, "ktor-server-tests-jvm").version(ktorVersion)
                }
            }
        }
    }
}
