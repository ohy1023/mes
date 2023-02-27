const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://restapi.mystudyproject.store:8080',
      changeOrigin: true,
    })
  );
};