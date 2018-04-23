# fwmt-legacy-gateway

DO NOT USE THE KEYSTORE FILE IN keystore.p12 FOR ANYTHING BUT TRIVIAL TESTS
DO NOT MERGE THE EXAMPLE WEB PAGE IN src/main/java/.../debug/HelloWorldWebPage INTO MASTER

To start docker on local machine:

$ docker pull postgres:latest
$ docker run -p 127.0.0.1:5432:5432 postgres

To run the project:

$ mvn spring-boot:run
