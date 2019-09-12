# youtrain

Showcase Vue.js + Vuetify.

This SPA communicates with the youtrain-server, a Spring Boot application providing a REST API.

The app is build by webpack and served on port 8090 (unless you change it).
Since the Spring Boot backend (youtrain-server) runs on port 8080 a proxy is
set up wich handles the forwarding/rewriting (see vue.config.js).

By Mark Vz

## Project setup

```
npm install
```

### Compiles and hot-reloads for development

```
npm run serve
```

### Compiles and minifies for production

```
npm run build
```

### Lints and fixes files

```
npm run lint
```

### Run your unit tests

```
npm run test:unit
```

### Run your end-to-end tests

(not yet available)

```
npm run test:e2e
```

### Customize configuration

See [Configuration Reference](https://cli.vuejs.org/config/).

## Docker

### Build image

    docker build -t registry.gitlab.com/tarent/youtrain:latest .

### Run as container
Requires that a docker image was built (see above) and that a youtrain-server instance is running on port 8080

    $(bin/set_host_ip.sh) # once for each new shell / after each network change
    docker-compose up -d

You can view logs this way:

    docker-compose logs -f