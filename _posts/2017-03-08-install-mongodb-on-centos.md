---
layout: post
title: MongoDB installment on CentOS
categories: [linux]
tags: [linux]
fullview: false
comments: true
---

## 概况

本教程使用`Community Edition`安装`MongoDB`在`Red Hat Enterprise Linux`或6和7使用`CentOS`的`Linux`版本`rpm`包。

## 1， 配置 `yum` 源

创建`/etc/yum.repos.d/mongodb-org.repo`， 这样可以直接使用`yum`安装`MongoDB`.

### 1.1 官方稳定版本
```sh
[mongodb-org-3.4]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/3.4/x86_64/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-3.4.asc
```

### 1.2 阿里云版本， 由于官方版本下载比较慢，建议阿里云源
```sh
[mongodb-org]
name=MongoDB Repository
baseurl=http://mirrors.aliyun.com/mongodb/yum/redhat/6Server/mongodb-org/3.4/x86_64/
gpgcheck=0
enabled=1
```

## 2. 安装`MongoDB`包和相关的工具

```sh
sudo yum install -y mongodb-org
```

## 3. 启动`MongoDB`
```sh
sudo service mongod start
```

### 3.1 检查是否安装好

You can verify that the `mongod` process has started successfully by checking the contents of the log file 
at `/var/log/mongodb/mongod.log` for a line reading
```sh
[initandlisten] waiting for connections on port <port>
```

where `<port>` is the port configured in `/etc/mongod.conf`, 27017 by default.

You can optionally ensure that `MongoDB` will start following a system reboot by issuing the following command:
```sh
sudo chkconfig mongod on
```

## 4. 加密连接
### 4.1 连接上`Mongodb`之后， 创建`admin`账号。
```sh
use admin
db.createUser(
  {
    user: "root",
    pwd: "***************",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
  }
)
```

### 4.2 修改`/etc/mongod.conf`
添加如下
```sh
security:
    authorization: enabled
```

### 4.3 重启`MongoDB`
```sh
service mongod restart
```

### 4.4 创建相关账号
```
use user
db.createUser(
 {
   user: "user",
   pwd: "*******",
   roles: [
      { role: "readWrite", db: "user" }
   ]
 }
)
```


## 5. For more details, please refer to the official [documentation](https://docs.mongodb.com/master/tutorial/install-mongodb-on-red-hat/); 