const path = require('path')
const jsonServer = require('json-server')
const pause = require('connect-pause')
const server = jsonServer.create()
const router = jsonServer.router(path.join(__dirname, 'db.json'))
const middlewares = jsonServer.defaults()

server.use(middlewares)
server.use(pause(1000))
server.use(router)

server.listen(8080, () => {
  console.log('JSON Server is running ...')
})
