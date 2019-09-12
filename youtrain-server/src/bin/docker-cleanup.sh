#!/bin/bash

exited=$(docker ps -q -a -f status=exited | wc -l | tr -d '[:space:]')

if [ "$exited" != "0" ]; then
    docker rm $(docker ps -q -a -f status=exited)
fi

tagref=$(docker images -q -f dangling=true | wc -l | tr -d '[:space:]')

if [ "$tagref" != "0" ]; then
    docker rmi $(docker images -q -f dangling=true)
fi