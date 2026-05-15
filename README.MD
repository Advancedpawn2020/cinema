# Cinema — 电影院会员管理系统

<a href="https://github.com/Advancedpawn2020/cinema/blob/master/LICENSE">
  <img src="https://img.shields.io/github/license/mashape/apistatus.svg" alt="license">
</a>
<a href="https://github.com/Advancedpawn2020/cinema/releases">
  <img src="https://img.shields.io/github/release/Advancedpawn2020/cinema.svg" alt="GitHub release">
</a>

## 项目简介

一套电影院会员管理系统，包含管理员、会员管理、会员卡管理、电影票、消费记录、数据统计等模块。

- **前端**：Vue.js 2.x + Element UI
- **后端**：Spring Boot 2.2.4 + MyBatis
- **数据库**：MySQL 5.5+

> 本项目已将前端源码（`cinema-web`）整合到后端仓库中，支持 **一键构建、一键启动**，无需单独搭建前端环境。

📄 详细的模块设计说明请查看 [cinema项目文档.md](./cinema项目文档.md)

## 环境要求

| 依赖 | 版本 | 说明 |
|------|------|------|
| JDK | 1.8 | 必须是 JDK 8，不支持更高版本 |
| MySQL | 5.5+ | 需提前安装并启动 |
| Maven | 3.x | 用于构建项目 |

> 💡 **不需要单独安装 Node.js**——Maven 构建时会自动下载 Node.js 12 并完成前端打包。

## 快速启动（三步走）

### 第一步：导入数据库

确保 MySQL 已启动，然后导入项目根目录下的 `cinema.sql`：

```bash
mysql -u root -p < cinema.sql
```

也可以用 Navicat / SQLyog 等可视化工具直接打开 `cinema.sql` 执行。

> ⚠️ SQL 文件中已包含 `CREATE DATABASE IF NOT EXISTS cinema`，会自动建库，无需手动创建。

### 第二步：修改数据库配置

编辑 `src/main/resources/application.properties`，把数据库账号密码改成你自己的：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cinema?useUnicode=true&characterEncoding=utf8
spring.datasource.username=你的用户名
spring.datasource.password=你的密码
```

> 默认是 `root` / `admin`，请按实际情况修改。

### 第三步：构建并启动

#### 方式一：IDE 启动（推荐开发时使用）

项目已自带前端打包产物，无需构建前端，直接运行启动类即可：

```
src/main/java/com/zrkworld/cinema/CimenaApplication.java
```

#### 方式二：Maven 一键构建（推荐部署时使用）

```bash
mvn clean package -DskipTests
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

此命令会自动完成：下载 Node.js → `npm install` → `npm run build` → 编译 Java → 打包 JAR。

---

**启动成功后**，浏览器访问：

### 👉 http://localhost:8081/cinema

> ⚠️ 端口是 **8081**，路径必须带 `/cinema`，直接访问 `http://localhost:8081` 会 404。

## 项目结构

```
cinema/
├── cinema-web/                  # 前端 Vue.js 源码（已整合）
│   ├── src/                     # 前端源码
│   ├── public/                  # 静态资源模板
│   ├── vue.config.js            # Vue CLI 配置
│   └── package.json             # 前端依赖
├── src/main/
│   ├── java/                    # 后端 Java 代码
│   ├── resources/
│   │   ├── application.properties  # 应用配置
│   │   └── mapper/              # MyBatis XML 映射文件
│   └── webapp/                  # 前端构建产物（打包后自动生成到这里）
│       ├── index.html
│       └── static/
├── cinema.sql                   # 数据库初始化脚本
├── pom.xml                      # Maven 配置（含前端自动构建插件）
└── README.MD
```

## 常见问题

### Q：启动报数据库连接失败？

- 检查 MySQL 是否已启动
- 检查 `application.properties` 中账号密码是否正确
- 检查是否已导入 `cinema.sql`

### Q：启动报驱动类警告或时区错误？

项目用的 `mysql-connector-java` 是 8.x 版本，但配置文件中写的是旧版驱动名。如遇问题，修改 `application.properties`：

```properties
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/cinema?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
```

### Q：页面空白或 404？

- 确认地址是 `http://localhost:8081/cinema`（必须带 `/cinema`）
- 确认 `src/main/webapp` 下有 `index.html` 和 `static` 文件夹

### Q：端口 8081 被占用？

修改 `application.properties`：

```properties
server.port=8082
```

访问地址也要对应改成 `http://localhost:8082/cinema`。

### Q：Maven 构建前端时 npm install 很慢？

可以配置 npm 淘宝镜像。在 `cinema-web` 目录下创建 `.npmrc` 文件：

```
registry=https://registry.npmmirror.com
```

或者跳过前端构建（仅当 `webapp` 目录下已有打包产物时）：

```bash
mvn clean package -DskipTests -Dskip.npm -Dskip.installnodenpm
```

### Q：想单独修改前端页面怎么办？

```bash
cd cinema-web
npm install
npm run dev
```

开发服务器会启动在 `http://localhost:8080`，自动代理后端接口到 `http://localhost:8081/cinema`。

修改完成后执行 `npm run build`，构建产物会自动输出到 `src/main/webapp` 目录。

## 项目截图

### 登录页面
![登录页面](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/1.png?raw=true)

### 系统首页
![系统首页](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/2.png?raw=true)

### 会员界面
![会员界面](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/3.png?raw=true)

### 会员卡界面
![会员卡界面](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/4.png?raw=true)

### 会员关怀
![会员关怀](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/9.png?raw=true)

### 流程图
![流程图](https://github.com/Advancedpawn2020/cinema/blob/master/src/main/webapp/projectimg/flowchart.png?raw=true)

## License

[MIT](https://github.com/Advancedpawn2020/cinema/blob/master/LICENSE)
