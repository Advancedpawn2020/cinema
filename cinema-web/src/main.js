import Vue from 'vue';
//导入默认挂载dom的组件
import App from './App.vue';
//相当于导入名为router的文件，但如果是文件夹的话，就导入其下的index.js
import router from './router';
import ElementUI from 'element-ui';
//导入国际化组件
import VueI18n from 'vue-i18n';
//这是典型的导入文件的非默认函数
import { messages } from './components/common/i18n';
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
// import './assets/css/theme-green/index.css'; // 浅绿色主题
import './assets/css/icon.css';
//导入自定义指令文件
import './components/common/directives';
//ie9和一些低版本的高级浏览器对es6新语法并不支持，导入此文件可以解决此问题
import 'babel-polyfill';
//导入日期格式化组件，并注册全局变量
import moment from "moment";
Vue.prototype.$moment = moment;
//阻止启动生产消息，常用作指令。false表示生产模式，true表示开发模式，会显示额外的提示
Vue.config.productionTip = false;
/// 通过插件的形式挂载，通过全局方法 Vue.use() 使用插件
Vue.use(VueI18n);
Vue.use(ElementUI, {
    size: 'small'
});
//语言标识 //this.$i18n.locale // 通过切换locale的值来实现语言切换
const i18n = new VueI18n({
    locale: 'zh',
    messages
});

/*
* 在前端路由跳转中，路由跳转前都是会经过beforeEach，
* 而beforeEach可以通过next来控制到底去哪个路由。
* 根据这个特性我们就可以在beforeEach中设置一些条件来控制路由的重定向。
* 常见的使用场景有：
* 1、验证用户是否登录（若未登录，且当前非登录页面，则自动重定向登录页面）;
* 2、用户权限；
* 3、用户输入的路径是否存在，不存在的情况下如何处理，重定向到哪个页面。
*
* 当一个导航触发时，全局前置守卫按照创建顺序调用。守卫是异步解析执行，此时导航在所有守卫 resolve 完之前一直处于 等待中。
* 每个守卫方法接收三个参数：
* to: Route: 即将要进入的目标 路由对象
* from: Route: 当前导航正要离开的路由
* next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。
* next(): 进行管道中的下一个钩子。如果全部钩子执行完了，则导航的状态就是 confirmed (确认的)。
* next(false): 中断当前的导航。如果浏览器的 URL 改变了 (可能是用户手动或者浏览器后退按钮)，那么 URL 地址会重置到 from 路由对应的地址。
* next('/') 或者 next({ path: '/' }): 跳转到一个不同的地址。当前的导航被中断，然后进行一个新的导航。你可以向 next 传递任意位置对象，且允许设置诸如 replace: true、name: 'home' 之类的选项以及任何用在 router-link 的 to prop 或 router.push 中的选项。
* next(error): (2.4.0+) 如果传入 next 的参数是一个 Error 实例，则导航会被终止且该错误会被传递给 router.onError() 注册过的回调。
* 确保要调用 next 方法，否则钩子就不会被 resolved。
*/

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title} | cinema`;
    //localStorage主要是用来作为本地存储来使用的，其中的setItem可以设置一个map值（在Login.vue中设置的值）
    //getItem可以从浏览器拿到数组，这里拿到用户名
    //逻辑：当用户名没取到，且当前要跳转的组件不是login，则跳转到login
    const role = localStorage.getItem('managerId');
    if (!role && to.path !== '/login') {
        next('/login');
    } else if (to.meta.permission) {
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === 'admin' ? next() : next('/403');
    } else {
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        } else {
            next();
        }
    }
});
// 4. 创建和挂载根实例。创建根组件
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能
new Vue({
    router,//省略了components:
    i18n,
    //render函数是渲染一个视图，然后提供给el挂载，如果没有render那页面什么都不会出来
    render: h => h(App)
    /*1.这是 es 6 的语法，表示 Vue 实例选项对象的 render 方法作为一个函数，接受传入的参数 h 函数，返回 h(App) 的函数调用结果。
      2.其次，Vue 在创建 Vue 实例时，通过调用 render 方法来渲染实例的 DOM 树。
      3.最后，Vue 在调用 render 方法时，会传入一个 createElement 函数作为参数，也就是这里的 h 的实参是 createElement 函数，然后 createElement 会以 APP 为参数进行调用，关于 createElement 函数的参数说明参见：Element-Arguments*/
}).$mount('#app');
//$mount和el:都是将实例化的vue挂载到指定的Dom元素上
//整体含义是：在main.js入口js中创建唯一的Vue核心实例，而后导入启动所必需的组件，
//用render来渲染一个组件使其可被挂载后，将本实例挂载到App.vue中
