# cinema-web — 电影院会员管理系统（前端）

<a href="https://github.com/vuejs/vue">
  <img src="https://img.shields.io/badge/vue-2.6.10-brightgreen.svg" alt="vue">
</a>
<a href="https://github.com/ElemeFE/element">
  <img src="https://img.shields.io/badge/element--ui-2.8.2-brightgreen.svg" alt="element-ui">
</a>
<a href="https://github.com/Advancedpawn2020/cinema-web/blob/master/LICENSE">
  <img src="https://img.shields.io/github/license/mashape/apistatus.svg" alt="license">
</a>

> ⚠️ **你不需要单独克隆本项目。** 本前端源码已经整合到后端仓库 [cinema](https://github.com/Advancedpawn2020/cinema) 的 `cinema-web` 目录中，支持 **一键构建、一键部署**。
>
> 👉 请直接前往 [cinema 主仓库](https://github.com/Advancedpawn2020/cinema) 按照 README 操作即可，无需额外搭建前端环境。

## 技术栈

- **Vue.js 2.x** + **Vue CLI 3**
- **Element UI** 组件库
- **Axios** 网络请求
- 参考模板：[vue-manage-system](https://lin-xin.gitee.io/example/work/)

## 前端开发（仅需修改前端页面时使用）

如果你只是想跑起来看效果，**不需要看这一节**，直接去主仓库启动后端即可。

以下步骤仅适用于需要修改前端页面的开发者：

```bash
cd cinema-web
npm install
npm run dev
```

开发服务器会启动在 `http://localhost:8080`，已配置好代理，自动将接口请求转发到后端 `http://localhost:8081/cinema`。

修改完成后执行构建：

```bash
npm run build
```

构建产物会自动输出到后端的 `src/main/webapp` 目录，无需手动复制。

## 目录结构

```
src/
├── api/            # 接口请求封装（按模块拆分）
├── assets/         # 静态资源（CSS、图片等）
├── components/
│   ├── common/     # 公共组件（头部、侧边栏、布局等）
│   └── page/       # 页面组件（会员、会员卡、统计等）
├── router/         # 前端路由配置
├── utils/          # Axios 封装（request.js）
├── App.vue         # 根组件
└── main.js         # 应用入口
```

## 跨域说明

开发模式下，`vue.config.js` 中已配置 `/api` 代理到 `http://localhost:8081/cinema`，无需额外处理跨域问题。

如需修改后端地址，编辑 `vue.config.js` 中 `devServer.proxy` 的 `target` 字段即可。

## License

[MIT](https://github.com/Advancedpawn2020/cinema-web/blob/master/LICENSE)
