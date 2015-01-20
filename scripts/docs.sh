#!/bin/sh

set -e

tmpdir=`mktemp -d /tmp/mori-gh-pages.XXXXXX`
trap "rm -rf $tmpdir" EXIT
cp -r docs/. $tmpdir
git checkout gh-pages
rm -rf ./*
cp -r $tmpdir/. .
git add -Av .
git commit -m "Updated docs"
echo "*** gh-pages branch updated ***"
git checkout -
echo "Run this to complete:"
echo "git push origin gh-pages:gh-pages"
