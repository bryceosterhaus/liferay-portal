export default {
  entry: { 
    "underscore": "underscore"
  },
  mode: "production",
  output: {
    filename: "[name].js",
    module: true,
    libraryTarget: "module",
  },
  optimization: {
    minimize: true,
  },
  experiments: {
    outputModule: true,
  },
};
