# THIS REPO HAS BEEN DEPRECATED!
The services contained in this repo have been broken out into separate repos of their own:

* [fwmt-resource-service](https://github.com/ONSdigital/fwmt-resource-service)
* [fwmt-job-service](https://github.com/ONSdigital/fwmt-job-service)
* [fwmt-staff-service](https://github.com/ONSdigital/fwmt-staff-service)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7107f0729e5447c4b3c537f35a47386e)](https://www.codacy.com/app/spdiaz1000/fwmt-legacy-gateway?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ONSdigital/fwmt-legacy-gateway&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.org/ONSdigital/fwmt-legacy-gateway.svg?branch=master)](https://travis-ci.org/ONSdigital/fwmt-legacy-gateway)

# FWMT Legacy Gateway
This repository contains the Fieldwork Management Tool (FWMT) Gateway for interfacing between the Social Survey legacy system and the Fieldwork Management Tool.

## Running
    ./mvn spring-boot:run

### Run PostgreSQL in a Docker Container
    docker pull postgres:latest
    docker run -p 127:0.0.1:5432:5432 postgres

## API
See [API.md](https://github.com/ONSdigital/fwmt-legacy-gateway/blob/master/API.md) for API documentation.

## Copyright
Copyright (C) 2018 Crown Copyright (Office for National Statistics)
