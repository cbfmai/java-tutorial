---
layout: post
title: MongoDB installment on CentOS
categories: [Linux]
tags: [Linux]
fullview: false
comments: true
---

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

### 3.2 开启远程访问

#### 3.2.1 创建用户
First `ssh` into your server and enter the `mongo` shell by typing mongo. 
For this example, I will set up a user named `ian` and give that user read & write access to the `cool_db` database.

```sh
use cool_db

db.createUser({
    user: 'ian',
    pwd: 'secretPassword',
    roles: [{ role: 'readWrite', db:'cool_db'}]
})

```

#### 3.2.2 关闭绑定本地`localhost` 及开启密码访问

```sh
# network interfaces
net:
  port: 27017
  #bindIp: 127.0.0.1  # Listen to local interface only, comment to listen on all interfaces.

security:
  authorization: 'enabled'

```

#### 3.2.3 重启服务
```sh
[root@*** etc]# service mongod restart
Stopping mongod:                                           [  OK  ]
Starting mongod:                                           [  OK  ]
```

注意： 如果是`centos 7.X`, 需要修改防火墙

```sh
iptables -A INPUT -p tcp --dport 27017 -j ACCEPT
```

或者只有指定ip能访问
```sh
iptables -A INPUT -s <ip-address> -p tcp --destination-port 27017 -m state --state NEW,ESTABLISHED -j ACCEPT
iptables -A OUTPUT -d <ip-address> -p tcp --source-port 27017 -m state --state ESTABLISHED -j ACCEPT

iptables -A INPUT -s 192.168.161.200 -p tcp --destination-port 27017 -m state --state NEW,ESTABLISHED -j ACCEPT
iptables -A OUTPUT -d 192.168.161.200 -p tcp --source-port 27017 -m state --state ESTABLISHED -j ACCEPT
```

防火墙参考 [文档](https://docs.mongodb.com/manual/tutorial/configure-linux-iptables-firewall/)



## 4. For more details, please refer to the official [documentation](https://docs.mongodb.com/master/tutorial/install-mongodb-on-red-hat/); 