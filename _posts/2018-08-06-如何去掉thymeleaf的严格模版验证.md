---
layout: post
title: 如何去掉thymeleaf的严格模版验证
categories: [Java]
tags: [Spring BOOT]
fullview: false
comments: true
---

### 问题：
使用`springboot`的`thymeleaf`模板时默认会对HTML进行严格的检查，导致当你的标签没有闭合时就会通不过，例如：

```html
//要想通过，后面的闭合必须写成  />
<meta charset="UTF-8">
```

### 解决方法：

* 1, 引入`nokohtml`库

```xml
 <dependency>
    <groupId>net.sourceforge.nekohtml</groupId>
    <artifactId>nekohtml</artifactId>
    <version>1.9.22</version>
 </dependency>
```

* 2, 修改配置文件

```
spring.thymeleaf.mode=LEGACYHTML5
```