module.exports = {
  devServer: {
    proxy:
    {
      "/rest": { target: "http://localhost:5000", ws: true, changeOrigin: true, secure: false },
      "/api": { target: "http://localhost:5000", ws: true, changeOrigin: true, secure: false },
    }
  }
}