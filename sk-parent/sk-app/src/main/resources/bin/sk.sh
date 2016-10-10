#!/bin/bash

java -classpath ${HOME}/sk/bin/dependencies/*:${HOME}/sk/bin/modules/*:${HOME}/sk/bin/sk-app-${project.version}.jar sk.app.Bootstrap $1