<!--这里是用vue-cli脚手架默认创建的被main.js直接调用的入口
而.vue文件是指单组件文件，每一个vue都是一个组件
这个组件模板是直接被main.js核心vue监管的，-->
<template>
    <div id="app">
        <!-- 路由出口 -->
        <!-- 路由匹配到的组件将渲染在这里 -->
        <router-view></router-view>
    </div>
</template>
<style>
    @import "./assets/css/main.css";
    @import "./assets/css/color-dark.css";     /*深色主题*/
    /*@import "./assets/css/theme-green/color-green.css";   浅绿色主题*/
</style>