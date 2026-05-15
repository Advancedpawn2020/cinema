import axios from 'axios';
/*
这里是抽象出来的总请求文件，所有组件的ajax请求通过api下的index.js的函数调用，最终
汇总到这里。
这里是真正的发出ajax请求，定义了axios的基础地址和超时时间，以及请求响应拦截。


  axios create(config)
        a.根据指定配置创建一个新的 axios，也就是每个新 axios都有自己的配置
        b.新 axios只是没有取消请求和批量发请求的方法，其它所有语法都是一政的
        c.为什么要设计这个语法?
            需求:项目中有部分接口需要的配置与另一部分接口需要的配置不太一样，如何处理
            解决:创建2个新axios，每个都有自己特有的配置，分别应用到不同变求的接口请求中
 */
const service = axios.create({
    // process.env.NODE_ENV === 'development' 来判断是否开发环境
    // easy-mock服务挂了，暂时不使用了
    //一个在线模拟后台的数据平台，通过官网注册账户之后，你就可以生成一个在线的API接口，
    // 然后通过ajax或者axios就可以访问这个接口了。
    // baseURL: 'https://www.easy-mock.com/mock/592501a391470c0ac1fab128',
    /*在开发环境，前端的ajax请求需要跨域，在vue.config.js中进行跨域配置，
    * 可以直接利用 Node.js 代理服务器，通过修改vue proxyTable接口实现跨域请求。
    * 步骤
    * 1.项目使用的是axios请求网络,将baseUrl修改为/api (这里是使用webpack提供的代理功能将/api代理成目标接口host)
    * 2.进入vue.config.js,在dev下增加proxyTable配置,可以匹配到/api,将其代理成目标接口host
    *
    * 在生产环境下的跨域问题解决
    * 1.同样将axios的baseURL修改为 /api, (生产环境下是使用nginx反向代理,将/api 代理成目标接口host)
    * 2.进入vue.config.js,在build下修改项目路径,将assetsPublicPath字段的 '/' 修改为 '/myproject' (名称随意,和tomcat的webapps下的项目目录一致)
    * 3.build项目,将生成的dist文件夹里的文件,复制到tomcat服务器的webapps下的项目目录下
    * 4.进入nginx/conf目录,新建文件夹myconfig,以及double-c.conf文件
    * 5.编辑double-c.conf配置文件,配置反向代理，除了默认的/路径，加入一个/api路径，转发到跨域的接口地址
    * 6.进入nginx/conf/nginx.conf配置文件,导入上面的double-c.conf配置
    * 7.此时已可以通过nginx代理访问tomcat下的项目,并且解决了跨域问题(192.168.25.128就是配置里的localhost)
    *
    * nginx等代理服务器实现跨域原理：
    * 首先，直接在浏览器地址栏中，输入某接口地址。是不会产生跨域问题的。
        只有当在某域名的页面中，由该页面发起的接口请求。才可能会跨域。
        nginx就类似于这个浏览器，它接收到外部对它的请求（ 注意，nginx只会接收别人对它的请求，而不会拦截浏览器的请求 ），再类似浏览器地址栏一样去请求某个接口。最后将请求到的内容返回回去
    * */

    //开发环境中前后端分离有跨域问题，需要使用到baseURL来跳转到js代理服务器，生产环境中不使用单独的WEB服务器的情况下，注释掉这一行
    //baseURL:'/api',
    //当部署到服务器，或者内网穿透，不是本机访问时，需要将baseURL改为外网域名地址。当本地访问时，需要该为localhost:8080
    baseURL:'http://localhost:8081/cinema',
    timeout: 5000
});

// 添加请求拦截器
service.interceptors.request.use(
    config => {
        // 在发送请求之前做某事，比如说 设置token
        // config.headers['token'] = 'token';
        return config;
    },
    error => {
        // 请求错误时做些事
        console.log(error);
        return Promise.reject();
    }
);

// 添加响应拦截器
service.interceptors.response.use(
    response => {
        // 如果返回的状态不是200 就主动报错
        if (response.status === 200) {
            return response.data;
        } else {
            Promise.reject();
        }
    },
    error => {
        console.log(error);
        // 返回接口返回的错误信息
        return Promise.reject();
    }
);

export default service;
