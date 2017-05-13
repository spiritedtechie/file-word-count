## Overview

This is a simple word count program I created to count the number of words or lines in a supplied file.

It is written using TDD, and has unit tests and an integration test creating using Junit and JMock.

## Setup

1. Install Java JDK 8
2. Install Maven 2 or 3
3. Import into IntelliJ IDEA as a Maven project and use a Java 8 project SDK.

## Test
```
mvn clean test
```
## Run
From the base project directory:
```
mvn clean package
java -jar target/word-count-0.0.1-SNAPSHOT.jar -w src/test/resources/sampleFile_fourLines.txt
```
