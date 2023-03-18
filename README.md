# 一、用户登录
## 1.1 tagger module--标注者模块
1.1.1 登录
请求：(POST)/api/tagger/login
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
1.1.2注册
请求：(POST)/api/tagger/register
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
