const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    // 端口配置在这里
    port: 8083,
    // ...其他代理配置
  },
})
