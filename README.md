# 项目安装和运行
- 本项目是在使用jdk17版本下编写的，最好装有jdk17的java版本。同时需要有maven，mysql8.0以上版本
- 相关db的安装请运行`db_install.sql`
- `mvn spring-boot:run`运行项目，初次使用该命令会从maven中央仓库拉取依赖，需要一阵子。运行前检查8082端口没有被占用

# 一、用户登录
## 1.1 登录
请求：(POST)/api/user/login
```json
{
  "username": "string",
  "password": "string"
}
```
响应:
成功200，登录失败401
```json
{
  "code": 0,
  "error_msg": "string",
  "data": {
    "_id": "string",
    "username": "string",
    "password": "string",
    "phone": "string",
    "email": "string"
  }
}
```
### 1.2 注册
请求：(POST)/api/user/register
```json
{
  "username": "string",
  "password": "string"
}
```
响应：
失败code为403
```json
{
  "code": 0,
  "error_msg": "string",
  "data": {
    "_id": "string",
    "username": "string",
    "password": "string",
    "phone": "string",
    "email": "string"
  }
}
```
## 1.3 注销登录
请求方式：
(POST)/api/user/logout
```json
{
  "code": 0,
  "error_msg": "string",
  "data": "string"
}

```
# 二、数据集模块
三个领域模型：<br/>
DataSetEntity->数据库中查出来对应的模型<br>
DataSetInfo->Service中使用的类型<br/>
DataSetModel->返回给前端展示的类型，其特点是实现了序列化，并且诸如标注类型等属性展示为中文

## 2.1 新增数据集
请求：
(POST) /api/dataset
```json
{
  "desc": "string",
  "sample_type": "string",
  "tag_type": "string"
}
```
响应：<br>
参数解释
- curPage: 当前页
- pageSize：一共有多少页
- pageContent：查询返回的是剧列表
- limitation：每页的个数
```json
{
  "code": 0,
  "error_msg": "string",
  "data": {
    "curPage": "int",
    "pageSize": "int",
    "pageContent": [
      {
        "_id": "string",
        "publisher_id": "string",
        "pub_time": "string",
        "desc": "string",
        "sample_type": "string",
        "tag_type": "string"
      }
    ],
    "limitation": "int"
  }
}
```
