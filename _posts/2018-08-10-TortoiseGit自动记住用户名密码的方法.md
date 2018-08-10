---
layout: post
title: TortoiseGit自动记住用户名密码的方法
categories: [Git]
tags: [Git]
fullview: false
comments: true
---

TortoiseGit自动记住用户名密码的方法

* 1, 编辑 `.git` 目录下的 `config` 文件 或者全局配置 在 `%USERPROFILE%` 下的 `.gitconfig` 文件， 增加

```
[credential]
    helper = store
```

* 2, 添加 `_netrc` 文件


```
%userprofile%
(e.g. c:\users\pandiyan\_netrc)
```

详细
```
machine
login
password
```

例如：

```
machine github.com
login myloginid
password mypassword
```



* 3, 使用`TortoiseGit GUI`
Right click → TortoiseGit → Settings → Git → Credential
![gui](https://i.stack.imgur.com/egH6L.png)