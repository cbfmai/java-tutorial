---
layout: post
title: Git clone with username and password
categories: [Git]
tags: [Git]
fullview: false
comments: true
---

* 1, 自动输入用户名密码

```sh
git clone https://username:password@github.com/username/repository.git
```

* 2, 手动再次输入密码

```sh
git clone https://username@github.com/username/repository.git
```
It will prompt you for your password