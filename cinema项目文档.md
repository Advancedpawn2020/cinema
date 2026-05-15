# 电影院会员管理系统 — 项目设计文档

## 一、技术方案

| 层级 | 技术选型 |
|------|---------|
| 后端框架 | Spring Boot 2.2.4 + MyBatis |
| 前端框架 | Vue.js 2.x + Element UI |
| 数据库 | MySQL 5.5+ |
| 构建工具 | Maven 3.x（后端）/ npm（前端） |

项目采用**前后端分离**架构，前端使用 Vue CLI 脚手架创建单页面应用（SPA），通过 Ajax 请求与后端 RESTful 接口交互。本仓库已将前端源码（`cinema-web` 目录）整合到后端项目中，支持一键构建部署。

## 二、项目结构概览

```
cinema/
├── cinema-web/                     # 前端 Vue.js 源码
│   ├── src/
│   │   ├── api/                    # 接口请求封装
│   │   ├── components/             # 页面组件
│   │   ├── utils/request.js        # Axios 请求配置
│   │   └── main.js                 # 入口文件
│   ├── vue.config.js               # Vue CLI 配置 & 代理
│   └── package.json
├── src/main/
│   ├── java/com/zrkworld/cinema/   # 后端 Java 代码
│   ├── resources/
│   │   ├── application.properties  # 应用配置
│   │   └── mapper/                 # MyBatis 映射文件
│   └── webapp/                     # 前端构建产物
├── cinema.sql                      # 数据库初始化脚本
├── pom.xml                         # Maven 配置
└── README.MD                       # 项目说明
```

## 三、功能模块设计

### 3.1 会员模块

| 功能 | 说明 |
|------|------|
| 会员列表 | 展示系统中所有已注册的会员信息 |
| 查询 | 支持按会员姓名、性别、ID 进行模糊查询 |
| 注册 | 填写表单提交后，后端校验是否已注册过，通过后写入数据库 |
| 修改 | 点击会员项的「修改」按钮，弹出模态窗口编辑会员信息 |
| 删除 | 点击「删除」按钮，确认后发送请求删除该会员数据 |

**会员表字段**：`username`、`password`、`name`、`sex`、`birthday`

### 3.2 会员卡模块

| 功能 | 说明 |
|------|------|
| 会员卡列表 | 展示当前用户所持有的所有会员卡信息及状态 |
| 查询 | 根据卡号 ID 查询指定会员卡 |
| 注册 | 用户可注册多张会员卡 |
| 充值 | 输入金额对会员卡余额进行充值，同时生成一条充值交易记录 |
| 消费 | 使用余额购买电影票，扣减卡内余额，同时生成一条消费交易记录 |
| 挂失 | 冻结会员卡，将数据库中的 `lose` 字段设为 `1` |
| 解挂 | 解除挂失状态，将 `lose` 字段恢复为 `0` |
| 补卡 | 挂失状态下可申请补卡，系统自动将原卡数据迁移到新卡 |
| 积分兑换 | 使用积分购买电影票（在购买方式中选择「积分」） |
| 交易记录 | 以表格形式展示当前用户的所有充值和消费记录 |

**会员卡表字段**：`cardid`、`username`、`balance`（余额）、`integral`（积分）、`lose`（挂失状态）

**交易记录表字段**：`id`、`cardid`、`value`（金额）、`time`（时间）、`spendtype`（交易类型）

### 3.3 电影票模块

| 功能 | 说明 |
|------|------|
| 电影票列表 | 在会员卡界面点击「购买」后，弹窗展示所有可购买的电影票 |
| 购买 | 支持两种方式：**余额购买**和**积分兑换**，后端根据提交的 `type` 参数调用不同的业务逻辑 |

**电影票表字段**：`movieid`、`name`（片名）、`price`（价格）、`integral`（所需积分）、`time`（放映时间）

### 3.4 会员关怀模块

| 功能 | 说明 |
|------|------|
| 生日提醒 | 进入系统时自动判断当天是否为会员生日，如果是则在会员名旁显示生日祝福 |
| 生日查询 | 可查询未来 7 天内过生日的会员，或指定某一天查询 |
| 倒计时排序 | 按距离生日天数升序排列，方便提前准备 |

### 3.5 数据统计模块

提供会员数据、消费数据的可视化统计图表，帮助管理员了解运营情况。

## 四、运行项目

详见 [README.MD](./README.MD) 中的「快速启动」章节。

简要步骤：

1. **导入数据库**：执行 `cinema.sql`
2. **修改配置**：编辑 `application.properties` 中的数据库账号密码
3. **启动项目**：运行 `CimenaApplication.java` 或执行 `mvn clean package -DskipTests && java -jar target/demo-0.0.1-SNAPSHOT.jar`
4. **访问系统**：浏览器打开 `http://localhost:8081/cinema`

## 五、项目截图

### 登录页面
![登录页面](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/1.png?raw=true)

### 系统首页
![系统首页](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/2.png?raw=true)

### 会员页面
![会员页面](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/3.png?raw=true)

### 会员卡管理
![会员卡管理](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/4.png?raw=true)

### 会员关怀
![会员关怀](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/9.png?raw=true)

### 流程图
![流程图](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/flowchart.png?raw=true)
