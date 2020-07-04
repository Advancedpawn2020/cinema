# cinema
  <a href="https://github.com/Advancedpawn2020/cinema/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/mashape/apistatus.svg" alt="license">
  </a>
  <a href="https://github.com/Advancedpawn2020/cinema/releases">
    <img src="https://img.shields.io/github/release/Advancedpawn2020/cinema.svg" alt="GitHub release">
  </a>

## 前言
本项目是学校布置的一道大作业，代码架构设计简单，注释齐全，结构清晰，适合同为java初学者参考。
cinema项目是一套电影院会员管理系统，使用前后端分离架构开发，前端基于Vue.js+Element UI技术，后端使用springboot+mybatis实现，包含管理员、会员管理、会员卡管理、电影票、消费记录、数据统计等模块
[cinema-web前端项目地址](https://github.com/Advancedpawn2020/cinema-web)

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

### 流程图
![Image text](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/flowchart.png?raw=true)
                         
## 使用说明
* 数据库文件cinema.sql已整合到项目中，使用mysql导入即可生成数据库
* 本项目已经整合了前后端，前端文件经过webpack构建后，生成了static文件夹和index.html放入了webapp下。
* 要对前端页面进行修改，需要同时git[cinema-web前端项目地址](https://github.com/Advancedpawn2020/cinema-web)，修改完成后
同样将生成的文件放到本项目的webapp下。    
* 需上交的程序设计文档可参考目录下:cinema项目文档.docx  

## 运行项目
* 方法一: cinema项目中已经整合了vue打包的静态资源，直接启动项目，并在浏览器上输入http://localhost:8081/cinema 即可运行。                                                                         
* 方法二: cinema-web项目是cinema项目的vue单页面前端项目，可以在文件夹目录下命令行输入：
`npm run install`                                                                                          
`npm run dev`
后，修改utils/request.js下baseURL=”/api”,开启node反向代理服务器,解决跨域问题,然后启动cinema后端项目，在浏览器上输入 http://localhost:8080 即可以前后端分离方式运行(也可以npm run build后将dist文件夹放到nginx下运行)

## License
[MIT](https://github.com/Advancedpawn2020/cinema/blob/master/LICENSE)
