---
layout: post
title: fastjson 转换 List
categories: [Java]
tags: [Java]
fullview: false
comments: true
---

### `fastjson`主要的`API`有哪些?

fastjson入口类是com.alibaba.fastjson.JSON，主要的API是JSON.toJSONString，和parseObject。
```java
package com.alibaba.fastjson;
public abstract class JSON {
      public static final String toJSONString(Object object);
      public static final <T> T parseObject(String text, Class<T> clazz, Feature... features);
}
```

序列化：
```java
String jsonString = JSON.toJSONString(obj);
```

反序列化：
```java
VO vo = JSON.parseObject("...", VO.class);
```

泛型反序列化：像 `List` 对象
```java
import com.alibaba.fastjson.TypeReference;
List<VO> list = JSON.parseObject("...", new TypeReference<List<VO>>() {});
```