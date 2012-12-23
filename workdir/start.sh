#!/bin/sh

cd `dirname $0`

tmpdir=upgrade.tmp

if [ "$1" = "delay" ]; then
  sleep 2
fi

if [ -d $tmpdir ]; then
  # do wykonania upgrade
  cp -rf $tmpdir/* .
  rm -rf $tmpdir
fi

# uruchom aplikacje
java -jar lightwarehouse.jar