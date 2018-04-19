# fwmt-legacy-gateway

DO NOT USE THE KEYSTORE FILE IN src/main/resources/keystore.p12 FOR ANYTHING BUT TRIVIAL TESTS

To start docker on local machine:

$ docker pull postgres:latest
$ docker run -p 127.0.0.1:5432:5432 postgres

To run the project:

$ mvn spring-boot:run
