const multipleEntry = require('react-app-rewire-multiple-entry')([
  {
    entry:    'src/index.js',
    template: 'public/index.html',
    outPath:  '/index.html'
  },
  {
    entry:    'src/search/search.js',
    template: 'public/search.html',
    outPath:  '/search.html'
  }
]);

module.exports = {
  webpack: function(config, env) {
    multipleEntry.addMultiEntry(config);
    return config;
  }
};