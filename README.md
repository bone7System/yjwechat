# 智能用车 API

#### 使用

* 通过 Gradle 运行
`gradle bootRun` 或者通过 IDEA 导入 Gradle 项目

* 访问 `http://localhost:8080/znyc`

* 开发时使用开发配置 `application-dev.propperties`，使用方法：
```
Edit Configurations... >  Spring Boot Settings > Active Profiles -> 设置为 'dev' 
```

#### Swagger 文档生成
```
gradle doc
```
生成的文档：`doc/znyc.json`

=========================== 开发环境设置 ================================

#### 数据库脚本,参考 [liquibase](http://www.liquibase.org/bestpractices.html) 文档
`/resource/db/changelog/*`
