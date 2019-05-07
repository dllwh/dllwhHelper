
> **为避免生成的bat 脚本乱码，请以 ==ANSI== 编码 保存**

```
@echo off
rem DevTools
cls 
color 0a 
TITLE Redis 操作工具

SET NGINX_PATH=D:
:nginx 路径
SET NGINX_DIR=D:\DevInstall\redis


%NGINX_PATH% 

cd "%NGINX_DIR%" 

redis-server.exe redis.windows.conf
```