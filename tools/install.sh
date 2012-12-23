#!/bin/sh

dir=$1

cp -r dist $dir
cp workdir/common.properties $dir
cp workdir/user.properties $dir
cp workdir/magazyn.sqlite $dir
cp workdir/start.sh $dir
