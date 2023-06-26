# D&D Colosseum

## Introduction

D&D War is a full stack application where players can make teams out of the most famous of D&D monsters and have them fight other teams of monsters. Battles are carried out using official D&D 5E edition stats as provided by http://www.dnd5eapi.co/ . Players will be presented these monsters in the form of interactable cards. Stop By and have fun!
 

## Installation

To use this project, you need to have Maven installed. Follow the instructions below to install Maven on your machine.

Prerequisites
Java Development Kit (JDK) version 8 or later
Internet connection (for downloading Maven)
Installation Steps
Download Maven: Visit the official Apache Maven website at https://maven.apache.org and navigate to the "Download" page.

Choose Maven Version: Select the version of Maven you want to install. It's recommended to download the latest stable version unless you have specific requirements for an older release.

Download Maven: Click on the download link for the desired version. You will be redirected to a mirror site.

Select Distribution: On the mirror site, select the distribution package appropriate for your operating system. For example, if you're using Windows, download the .zip file.

Extract Maven: Once the download is complete, extract the contents of the downloaded archive to a directory of your choice. This directory will be referred to as <maven_home> in further steps.

Set Environment Variables: To use Maven from the command line, you need to set the PATH environment variable to include <maven_home>/bin. 

run `mvn --version`

If Maven is installed properly, you should see the version and other details printed on the console.

Create a application.properties file, and set up the necessary dependencies. You will need this to connect to your Postgres Server. Make sure to include a Secret token such as

jwt.secret=c2206ffb-01cf-4a2f-8bf-d4bb3043847b

# Backend Logic
This backend consists of several endpoints which the frontend api as seen here https://github.com/052223-java-angular/Soudry-P1-Frontend/tree/continuous-deployment can make HTTP Get and Post requests to login, register, as well as pull monster data and item data. Users must first login to receive a jwt token, which is required to be valid to access site data.

## Tech Stacks

- **Java**: The programming language used to build the backend.
- **PostgreSQL**: Used as the database to persist .
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **JUnit, Mockito, and okhttp**: Used for unit and integration testing.
- **Git and GitHub**: Used for version control.
- **Spring Boot** For creating, managing and interacting with postgres database and the third party api.
-- **Elastic Bean Stalk** For Hosting it online