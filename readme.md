# 网上法律咨询系统

### Latest News

- 网上法律咨询系统的开发项目，从需求分析，软件开发，系统测试，大部分内容都完成了，但是由于算法模型的代码还没有整理好，“智能预测”模板的功能还没有实现。

### 开发环境

- 开发语言： 后端 - Java(jdk1.8)，Python(3.7)，前端 - Html，JavaScript     
- 使用框架：Spring Boot(2.1.3)，MyBatis，Junit，pytorch(1.4)
- 数据库：mysql(8.0)
- 项目管理：Maven
- IDE：JetBrains Intelij IDEA 2020
- 开发系统：Windows10
- 网络服务器：Tomcat(9.0)
- 客户端浏览器：尽量使用Google Chrome，目前已知IE浏览器会出现前端格式错误的问题

### 代码结构

```
legalsys
├─src 
│	├─main
│		├─java
│			├─com.huidong.legalsys
│				├─aspect（权限管理）
│					├─LoginAspect.java
│				├─configuration
│					├─DataSourceConfiguration.java
│					├─UploadFileConfiguration.java
│				├─controller（业务表现层）
│					├─ConsultController.java
│					├─LoginController.java
│					├─ManageController.java
│					├─PenallawController.java
│					├─SessionController.java
│					├─StatisticsController.java
│				├─dao（数据访问层）
│					├─ConsultDao.java
│					├─ConvrDao.java
│					├─LoginDao.java
│					├─SessionDao.java
│					├─StatureDao.java
│					├─UserDao.java
│				├─domain（实体层）
│					├─Consult.java
│					├─Convr.java
│					├─ConvrContent.java
│					├─Error.java
│					├─Login.java
│					├─Session.java
│					├─Stature.java
│					├─User.java
│				├─enumeration
│					├─ConsultTypeEnum.java
│					├─ErrorEnum.java
│					├─LoginStatusEnum.java
│					├─RegisterTypeEnum.java
│					├─SessionStatusEnum.java
│				├─exception
│					├─LegalsysException.java
│				├─handle
│					├─ExceptionHandle.java
│				├─listener（在线人数监听）
│					├─HttpSessionListener.java
│					├─RequestContextListener.java
│					├─ServletContextListener.java
│				├─service（业务逻辑层）
│					├─ConsultService.java
│					├─LoginService.java
│					├─ManageService.java
│					├─PenallawService.java
│					├─SessionService.java
│					├─StatisticsService.java
│					├─UploadService.java
│				├─util
│					├─ErrorUtil.java
│				├─LegalsysApplication.java（项目启动类）
│		├─resources
│			├─mapper（Mybatis映射）
│				├─ConsultMapper.xml
│				├─ConvrMapper.xml
│				├─LoginMapper.xml
│				├─SessionMapper.xml
│				├─StatureMapper.xml
│				├─UserMapper.xml
│			├─python
│				├─preprocess.py
│			├─static
│				├─css
│					├─fonts
│					├─style.css（html配置文件）
│				├─images（存放图片文件）
│				├─stature（存放刑法数据）
│			├─templates（html文件）
│			├─application.yml（配置文件）
│			├─application.properties（配置文件）
│		├─resourcesupload（律师执照上传路径）
├─test（单元测试的一些代码，写的比较乱，所以部署的时候没放在src目录下）
├─init.sql（mysql新建数据库及表单）
├─pom.xml（Maven项目配置文件）
```

### 项目部署

1. - 安装MySQL Community Server，https://dev.mysql.com/downloads/mysql/

2. - cd legalsys目录
   - 运行mysql -u root -p，输入密码
   - 运行source init.sql建立数据库，初始化表单

3. - 安装JetBrains IntelliJ IDEA Ultimate，https://www.jetbrains.com/idea/download/#section=windows
   - 高校在读学生的话可以申请教育版，https://www.jetbrains.com/community/education/#students

4. - 下载安装Maven，http://maven.apache.org/index.html
   - 配置镜像，本地仓库

5. - IDEA中打开legalsys项目文件
   - 右键点击pom.xml，点击Maven，点击Reimport
6. - 修改项目的名字问legalsys
   - 设置Spring Boot Configuration ：
   - 点击Edit Configuration，从模板Templates新建Spring Boot，Main class选择com.huidong.legalsys.LegalsysApplication，Use classpath of modules选择legalsys文件夹
   - 设置Tomcat Configuration：
   - 从模板Templates新建Tomcat Local
   - 找到File - Project Structure - Project Settings - Artifacts，新建legelsys:war exploded
   - 在刚才新建的Tomcat Local，Before launch中添加刚才新建的legelsys:war exploded artifact
   - 添加Mysql数据库连接，输入用户名root，密码******，数据库名称legalsys
   - maven clean install 部署项目

### 文档结构

​	如果想要了解系统的设计过程，包括需求分析文档，静态模型，动态模型，数据库设计，测试文档，运行界面，可以查看“文档”目录下的“功能思维导图.xmind”和“文档信息.docx”。

​	“文档信息.docx”的结构为：

```
文档信息.docx
├─系统名称 
├─开发及运行环境
├─系统简介
│	├─功能性需求
│	├─非功能性需求
├─软件体系结构
├─系统完成情况
├─分层UseCase模型
├─类的描述
├─静态模型
│	├─分析类图
│	├─设计类图
├─动态模型
│	├─活动图
│	├─状态图
│	├─时序图
├─主要源代码以及可执行文件
├─实现模型
│	├─构件图
│	├─部署图
├─数据库表单
├─ER图
├─单元测试
├─集成测试
├─系统功能性测试
```

### Acknowledgement

- 开发之前没有相关经验，所有东西都是现学现卖，所以对spring boot的底层逻辑不清楚，前端开发的方法也不了解。虽然项目简单，但是代码中肯定有诸多不正确的地方，恳请大家能够包容，欢迎批评指正。

- 整个开发过程借鉴了[qianqianjun](https://github.com/qianqianjun)，[IT技术博客项目](https://github.com/qianqianjun/spring-boot-blog)的很多方面，该项目质量比较高，如果是想要学习spring boot开发，熟悉整个开发流程的话，可以参考该项目。