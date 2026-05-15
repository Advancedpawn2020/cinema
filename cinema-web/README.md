# cinema-web

<a href="https://github.com/vuejs/vue">
    <img src="https://img.shields.io/badge/vue-2.6.10-brightgreen.svg" alt="vue">
  </a>
  <a href="https://github.com/ElemeFE/element">
    <img src="https://img.shields.io/badge/element--ui-2.8.2-brightgreen.svg" alt="element-ui">
  </a>
  <a href="https://github.com/Advancedpawn2020/cinema-web/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/mashape/apistatus.svg" alt="license">
  </a>
  <a href="https://github.com/Advancedpawn2020/cinema-web/releases">
    <img src="https://img.shields.io/github/release/Advancedpawn2020/cinema-web.svg" alt="GitHub release">
  </a>

本项目是电影院会员管理系统的web端，是基于Vue + Element UI 的后台管理系统解决方案的一个应用。参考后台管理模板:[vue-manage-system项目地址](https://lin-xin.gitee.io/example/work/)

## 项目截图

### 登录页面

![Image text](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/1.png?raw=true)

### 系统首页

![Image text](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/2.png?raw=true)

### 会员界面

![Image text](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/3.png?raw=true)

### 会员卡界面

![Image text](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/4.png?raw=true)

### 会员关怀

![Image text](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/9.png?raw=true)

## 前言

本项目基于Web Management System模板开发。基于 vue.js，使用 vue-cli3 脚手架，引用 Element UI 组件库。

## 安装步骤

```
git clonegit@github.com:Advancedpawn2020/cinema-web.git      // 把前端项目下载到本地
cd cinema-web    // 打开命令行，进入项目目录
npm install         // 安装项目依赖，等待安装完成之后，安装失败可用 cnpm 或 yarn

// 开发环境，开启服务器，浏览器访问 http://localhost:8080，
npm run serve

// 生产环境，执行构建命令，生成的dist文件夹下文件复制到webapp下
npm run build
```
## 各文件夹作用
* api：集合了所有页面的ajax的请求
* assets：所有的css和img等静态资源
* components：单文件组件
   * common：公共的组件，包括头、首页布局、事件路由器、侧边栏等
   * page：各可以跳转的单文件组件
* config：全局配置文件
* router：实例化前端路由，包含所有可以在路由中被跳转的地址
* utils：axios的封装，供api文件夹使用
* App.vue：其模板是被核心Vue挂载的，是整个单页面应用的根模板
* main.js：核心Vue实例的位置，是脚手架中默认启动的入口，是整个应用的核心

## 其他注意事项
* 针对于本项目的具体修改过程，可参考[vue-manage-system项目地址](https://lin-xin.gitee.io/example/work/)
* 使用本前端项目进行前后端分离开发时，需要解决跨域问题，在utils/request.js中将baseURL设置为/api，并在vue.config.js中设置/api所映射的路径，
以期使用node的代理服务器解决跨域问题（如使用默认设置，请将cinema后端项目的端口设置为8081，路径设置为/cinema）

## License

[MIT](https://github.com/Advancedpawn2020/cinema-web/blob/master/LICENSE)
