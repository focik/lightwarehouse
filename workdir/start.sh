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
  echo zrobic upgrade
  # rm -rf $tmpdir
fi

java -jar magazyn.jar
