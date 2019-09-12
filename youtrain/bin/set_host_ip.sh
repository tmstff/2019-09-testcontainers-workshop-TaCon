#!/bin/bash

find_docker_ip() {
  unamestr="$(uname)"
  if [[ $DOCKER_HOST ]]; then
    echo $DOCKER_HOST | grep -oE "([0-9]{1,3}\.){3}[0-9]{1,3}"
  elif [[ "$unamestr" == 'Darwin' ]]; then
    ifconfig | grep -E "([0-9]{1,3}\.){3}[0-9]{1,3}" | grep -v 127.0.0.1 | awk '{ print $2 }' | cut -f2 -d: | head -n1
  else
    ip addr show docker0 | grep 'inet ' | cut -d/ -f1 | awk '{print $2}'
  fi
}

export HOST_IP="$(find_docker_ip)"
echo "export HOST_IP=$HOST_IP"