const path = require('path');

module.exports = {
    baseUrl: './',
    assetsDir: 'static',
    // 构建产物输出到后端的 webapp 目录，实现前后端一体化部署
    outputDir: path.resolve(__dirname, '../src/main/webapp'),
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/api':{
            //目标接口域名
                //target:'http://jsonplaceholder.typicode.com',
                target:'http://localhost:8081/cinema',
                changeOrigin:true,//是否跨域
                pathRewrite:{
                    '/api':''//重写接口,后面可以使用重写的新路径，一般不做更改
                }
            }
        },
        /*vue-cli搭建的环境，用nginx做代理服务器，访问时显示：Invalid Host header
经查是因为新版的webpack-dev-server出于安全考虑，默认检查hostname，如果hostname不是配置内的就不能访问。这样有2中方法，一种是设置跳过host检查
*/
        //这里设置跳过检查
        disableHostCheck: true
    }
};