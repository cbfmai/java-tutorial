---
layout: post
title: Oracle sqlplus 使用指南
categories: [Oracle]
tags: [Oracle]
fullview: false
comments: true
---

`SQL*Plus` 定义

> SQL*Plus: Release 11.2.0.1.0 Production

  Copyright (c) 1982, 2010, Oracle.  All rights reserved.

  使用 SQL*Plus 执行 SQL, PL/SQL 和 SQL*Plus 语句。

  用法 1: sqlplus -H | -V

      -H             显示 SQL*Plus 版本和
                     用法帮助。
      -V             显示 SQL*Plus 版本。

  用法 2: sqlplus [ [<option>] [{logon | /nolog}] [<start>] ]

    <option> 为: [-C <version>] [-L] [-M "<options>"] [-R <level>] [-S]

      -C <version>   将受影响的命令的兼容性设置为
                     <version> 指定的版本。该版本具有
                     "x.y[.z]" 格式。例如, -C 10.2.0
      -L             只尝试登录一次, 而不是
                     在出错时再次提示。
      -M "<options>" 设置输出的自动 HTML 标记。选项
                     的格式为:
                     HTML [ON|OFF] [HEAD text] [BODY text] [TABLE text]
                     [ENTMAP {ON|OFF}] [SPOOL {ON|OFF}] [PRE[FORMAT] {ON|OFF}]
      -R <level>     设置受限模式, 以禁用与文件系统交互的
                      SQL*Plus 命令。级别可以
                     是 1, 2 或 3。最高限制级别为 -R 3, 该级别
                     禁用与文件系统交互的
                     所有用户命令。
      -S             设置无提示模式, 该模式隐藏
                     命令的 SQL*Plus 标帜, 提示和回显
                     的显示。

    <logon> 为: {<username>[/<password>][@<connect_identifier>] | / }
                [AS {SYSDBA | SYSOPER | SYSASM}] [EDITION=value]

      指定数据库帐户用户名, 口令和数据库连接
      的连接标识符。如果没有连接
      标识符, SQL*Plus 将连接到默认数据库。

      AS SYSDBA, AS SYSOPER 和 AS SYSASM 选项是数据库
      管理权限。

      <connect_identifier> 的形式可以是 Net 服务名
      或轻松连接。

        @[<net_service_name> | [//]Host[:Port]/<service_name>]

          <net_service_name> 是服务的简单名称, 它解析
          为连接描述符。

          示例: 使用 Net 服务名连接到数据库, 且
                   数据库 Net 服务名为 ORCL。

             sqlplus myusername/mypassword@ORCL

          Host 指定数据库服务器计算机的主机名或 IP
          地址。

          Port 指定数据库服务器上的监听端口。

          <service_name> 指定要访问的数据库的
          服务名。

          示例: 使用轻松连接连接到数据库, 且
                   服务名为 ORCL。

             sqlplus myusername/mypassword@Host/ORCL

      /NOLOG 选项可启动 SQL*Plus 而不连接到
      数据库。

      EDITION 指定会话版本的值。


    <start> 为: @<URL>|<filename>[.<ext>] [<parameter> ...]

      使用将分配给脚本中的替代变量的指定参数
      从 Web 服务器 (URL) 或本地文件系统 (filename.ext)
      运行指定的 SQL*Plus 脚本。



常用的几种 `SQL*Plus` 登录方式

```cmd
sqlplus / as sysdba   --以操作系统权限认证的oracle sys管理员登陆
```

```cmd
sqlplus /nolog    --不在cmd或者terminal当中暴露密码的登陆方式

SQL> conn /as sysdba
&
SQL> conn sys/password as sysdba

```

使用`SQL*Plus` 远程登录

```cmd
sqlplus usr/pwd@//host:port/sid
```

