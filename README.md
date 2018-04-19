[![Codacy Badge](https://api.codacy.com/project/badge/Grade/384b4745f95f49aabbf44d36440c0abf)](https://www.codacy.com/app/sdcplatform/fwmt-legacy-gateway?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ONSdigital/fwmt-legacy-gateway&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.org/ONSdigital/fwmt-legacy-gateway.svg?branch=master)](https://travis-ci.org/ONSdigital/fwmt-legacy-gateway)

# FWMT Legacy Gateway
This repository contains the Fieldwork Management Tool (FWMT) Gateway for interfacing with the Social Survey legacy system.

## Running
    ./mvn spring-boot:run

### Run PostgreSQL in a Docker Container
    docker pull postgres:latest
    docker run -p 127:0.0.1:5432:5432 postgres

## Copyright
Copyright (C) 2018 Crown Copyright (Office for National Statistics)
