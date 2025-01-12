# Kosh - Kotlin-written interactive shell
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

Kosh is a Kotlin-written interactive shell, supporting Linux, macOS and Windows.

The only runtime dependency is **Java 21**, which can be omitted by building a native image of the jar with [GraalVM Native Image](https://www.graalvm.org/latest/reference-manual/native-image/)

## BUILDING JAR

To compile Kosh you need:
- Java SDK 21

after cloning the repo, use `./gradlew fatJar` which will build the jar in ``build/libs/kosh-X.Y.Z-standalone.jar``, executable with `java -jar kosh-X.Y.Z-standalone.jar`
