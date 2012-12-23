#!/bin/sh

cd `dirname $0`/..

dir=$1
zip=$dir.zip

if mkdir $dir; then
  cp -r dist/lib $dir
  cp dist/lightwarehouse.jar $dir
  cp workdir/common.properties $dir
  cp workdir/start.* $dir

  cd $dir
  zip -r $zip *

  rm -rf $dir

  echo Pack: $zip
fi
