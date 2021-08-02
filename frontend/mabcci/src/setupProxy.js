const { createProxyMiddleware } = require('http-proxy-middleware');

// eslint-disable-next-line func-names
// module.exports = function (app) {
//   app.use(
//     '/auth/login',
//     createProxyMiddleware({
//       target: 'http://localhost:8080',
//       changeOrigin: true,
//     }),
//   );
// };
