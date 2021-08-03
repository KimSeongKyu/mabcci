const { createProxyMiddleware } = require('http-proxy-middleware');

// eslint-disable-next-line func-names
module.exports = function (app) {
  app.use(
    '/auth/login',
    createProxyMiddleware({
      target: 'http://localhost:8080',
      changeOrigin: true,
    }),
  );

  app.use(
    '/api/members',
    createProxyMiddleware({
      target: 'http://localhost:8080',
      changeOrigin: true,
    }),
  );
};

// const proxy = require('http-proxy-middleware');

// module.exports = function (app) {
//   app.use(
//     proxy('/api/members', {
//       target: 'http://localhost:8080',
//       changeOrigin: true,
//     }),
//   );
// };
