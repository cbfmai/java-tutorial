---
layout: post
title: Windows下使用cmd运行jar日志乱码
categories: [Java]
tags: [Spring BOOT]
fullview: false
comments: true
---

### 问题：
`spring boot`打包后在`windows` 使用 `cmd` 启动乱码

### 解决方法：

* 1, 修改 `pom`

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <!-- spring-boot:run 中文乱码解决 -->
                <jvmArguments>-Dfile.encoding=UTF-8</jvmArguments>
            </configuration>
        </plugin>
    </plugins>
</build>
```

* 2, 命令行

```
java -Dfile.encoding=utf-8
```