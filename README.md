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
响应：
```json
{
  "code": 0,
  "error_msg": "string",
  "data": {
    "_id": "string",
    "publisher_id": "string",
    "pub_time": "string",
    "desc": "string",
    "sample_type": "string",
    "tag_type": "string"
  }
}
```
