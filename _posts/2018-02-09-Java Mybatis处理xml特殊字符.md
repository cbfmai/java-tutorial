---
layout: post
title: Mybatis处理xml特殊字符
categories: [JAVA,Mybatis]
tags: [JAVA,Mybatis]
fullview: false
comments: true
---

`Mybatis`在`xml`文件中处理大于号小于号的方法

1, 转义字符把`>`和`<`替换掉

```markdown
| 项目        | 价格   |  数量  |
| --------    | ----   | ----  |
| &lt;        | <      |   小于号  |
| &gt;        |   >    |   大于号  |
| &le;        | <=     |   小于等于号  |
| &ge;        |   >=   |   大于等于号  |
| &amp;       |    &   |    和  |
| &apos;      |    '   |  单引号  |
| &quot;      |    "   |  双引号  |
```

2, 使用`CDATA`

```xml
<![CDATA[   ]]>
```