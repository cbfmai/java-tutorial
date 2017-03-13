---
layout: post
title: MySQL installment on CentOS
categories: [linux]
tags: [linux]
fullview: false
comments: true
---

MySQL provides a Yum-style software repository for the following Linux distributions:

* EL5, EL6, and EL7-based platforms (for example, the corresponding versions of Red Hat Enterprise Linux, Oracle Linux, and CentOS)
* Fedora 24 and 25

## 1, 添加`Yum`源
这个有多种方法
* a, 从官方下载`rpm` https://dev.mysql.com/downloads/repo/yum/
* b, 创建新的`repo`文件

```sh
vim /etc/yum.repos.d/mysql-community.repo


[mysql57-community]
name=MySQL 5.7 Community Server
baseurl=http://repo.mysql.com/yum/mysql-5.7-community/el/6/$basearch/
enabled=1
gpgcheck=0
```


## 2, 安装 `MySQL`
```sh
$ sudo yum install mysql-community-server
```



## 3, 启动`MySQL Server`
用如下脚本启动
```
shell> sudo service mysqld start
Starting mysqld:[ OK ]
```

检查启动情况
```
shell> sudo service mysqld status
mysqld (pid 3066) is running.
```


## 4， 修改`Root`用户密码

A superuser account 'root'@'localhost is created. A password for the superuser is set and stored in the error log file. To reveal it, use the following command:

```
shell> sudo grep 'temporary password' /var/log/mysqld.log
```


Change the root password as soon as possible by logging in with the generated, temporary password and set a custom password for the superuser account:

```
shell> mysql -uroot -p 

mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass4!';
```


## 5， 开启远程访问
默认的情况下， `MySQL` 是不可以远程访问的
```
$ vim /etc/mysql/my.cnf
```

注释如下
```
#bind-address           = 127.0.0.1
#skip-networking
```

更新权限
```SQL
mysql> GRANT ALL PRIVILEGES ON *.* TO 'USERNAME'@'%' IDENTIFIED BY 'PASSWORD' WITH GRANT OPTION;
```

或者指定IP
```
mysql> GRANT ALL PRIVILEGES ON *.* TO 'USERNAME'@'1.2.3.4' IDENTIFIED BY 'PASSWORD' WITH GRANT OPTION;
```

## 6, For the more official documents, please refer to [Online Doc](https://dev.mysql.com/doc/refman/5.7/en/linux-installation-yum-repo.html#yum-repo-installing-mysql)