#!/usr/bin/env bash
mvn deploy -DskipTests -P release --settings .travis/mvnsettings.xml

