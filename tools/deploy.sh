#!/bin/sh

dir=~/Desktop/lwhtest

rm -rf $dir

cp -r dist $dir
cp workdir/common.properties $dir
cp workdir/user.properties $dir
cp workdir/magazyn.sqlite $dir
cp workdir/start.sh $dir
