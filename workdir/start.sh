#!/bin/sh

cd `dirname $0`

tmpdir=upgrade.tmp

if [ -z "$delayrun" ]; then
  delayrun=0
fi

if [ $delayrun -gt 0 ]; then
  sleep $delayrun
fi

if [ -d $tmpdir ]; then
  # plik start.sh zostal przeniesiony i usuniety wczesniej
  cp -f $tmpdir/* .
  rm -rf $tmpdir
fi

# uruchom aplikacje
java -jar lightwarehouse.jar
