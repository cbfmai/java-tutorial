---
layout: post
title: Aliyun阿里云Maven仓库地址
categories: [Java]
tags: [Java]
fullview: false
comments: true
---

Maven作为一个项目管理工具确实非常好用，但是在国内这个网络条件下实在是让人恼火。如今阿里云公开了一个中央仓库，大家可以试试。

配置

* 修改maven根目录下的conf文件夹中的setting.xml文件，内容如下：

```xml
<mirrors>
    <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>        
    </mirror>
</mirrors>
```

* 在`Linux` 系统下， 也可修改 `~/.m2/setting.xml` 