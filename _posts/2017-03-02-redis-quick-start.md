---
layout: post
title: Redis快速入门
categories: [Linux]
tags: [Linux, Java]
fullview: false
comments: true
---


这是一个快速入门文档，面向没有Redis先前经验的人。阅读本文档将帮助你：
* `redis-cli` 使用方法
* 在程序中使用`Redis`
* `Redis` 持久层是如果工作
* `Redis` 安装

# `Redis` 安装

下载最新稳定版，并编译
```sh
wget http://download.redis.io/redis-stable.tar.gz
tar xvzf redis-stable.tar.gz
cd redis-stable
make
```


安装

```sh
 $ sudo make install
```

或者如下名单也可以（与如上只需要执行一个就可以了）
```sh
sudo cp src/redis-server /usr/local/bin/
sudo cp src/redis-cli /usr/local/bin/
```

---

# 启动`Redis Server`

最简单的方法就是执行`redis-server`
```sh
$ redis-server
[28550] 01 Aug 19:29:28 # Warning: no config file specified, using the default config. In order to specify a config file use 'redis-server /path/to/redis.conf'
[28550] 01 Aug 19:29:28 * Server started, Redis version 2.2.12
[28550] 01 Aug 19:29:28 * The server is now ready to accept connections on port 6379
... more logs ...
```

---
# 检查`Redis` 是否正常工作
```sh
$ redis-cli ping
PONG
```

你可以用`redis-cli`来进行更多的测试
```sh
$ redis-cli                                                                
redis 127.0.0.1:6379> ping
PONG
redis 127.0.0.1:6379> set mykey somevalue
OK
redis 127.0.0.1:6379> get mykey
"somevalue"
```

---
# 更加合适的安装方法
* 创建自定义数据路径
```sh
sudo mkdir /etc/redis
sudo mkdir /var/redis
```


* 复制初始化脚本到 `/etc/init.d`
```sh
sudo cp utils/redis_init_script /etc/init.d/redis
```

* 编辑这个初始脚本
```sh
sudo vi /etc/init.d/redis
```

* Copy the template configuration file you'll find in the root directory of the Redis distribution into /etc/redis/ using the port number as name, for instance:
```sh
sudo cp redis.conf /etc/redis/6379.conf
```

* 创建`Redis`实例工作路径
```
sudo mkdir /var/redis/6379
```

* Edit the configuration file, making sure to perform the following changes:
> * Set `daemonize` to yes (by default it is set to no).
> * Set the `pidfile` to `/var/run/redis_6379.pid` (modify the port if needed).
> * Change the `port` accordingly. In our example it is not needed as the default port is already 6379.
> * Set your preferred `loglevel`.
> * Set the `logfile` to /var/log/redis_6379.log
> * Set the `dir` to /var/redis/6379 (very important step!)

* You are done! Now you can try running your instance with:
```sh
sudo /etc/init.d/redis_6379 start
```


# 加密登录

```sh
sudo vim /etc/init.d/redis


--- 在stop命令中加上密码
$CLIEXEC -a "yourpassword" -p ${REDISPORT} shutdown
```