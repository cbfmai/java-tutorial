---
layout: post
title: Maven Build 指定jdk版本.md
categories: [Java]
tags: [Java, Maven]
fullview: false
comments: true
---

很多情况下， 用 `Eclipse` 或者 `IDEA` 导入工程的时候， 你想自动使用关联的jdk版本

有两种方法。

* 1， 修改`pom.xml`

```xml
<properties>
   <maven.compiler.source>1.8</maven.compiler.source>
   <maven.compiler.target>1.8</maven.compiler.target>
</properties>
```

* 2, 或者添加 `maven-compile-plugin` 并指定相应的版本

```xml
<build>
<plugins>
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.2</version> <!-- or whatever current version -->
    <configuration>
      <source>1.8</source>
      <target>1.8</target>
    </configuration>
  </plugin>
</plugins>
</build>
```