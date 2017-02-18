This article provides practical examples for 50 most frequently used commands in Linux / UNIX.

注意：这不是一个全面的列表，只是给你介绍一些常见的Linux命令，以供将来参考。

## Table of contents
[tar](#tar)


### 1. `tar`
`tar`，相当于我们`windows`下的`zip`, `unzip`, 注意用来压缩打包

#### 1.1 创建一个新的 `tar` 文件

```sh
$ tar cvf archive_name.tar dirname/
```
* c – 创建一个新的存档
* v – 详细列出已处理的文件
* f – 以下是归档文件名


### 1.2 解压一个存在的 `tar` 文件
```sh
$ tar xvf archive_name.tar
```
* x – 从档案中提取文件


### 1.3 创建一个`gzip`文件
```sh
$ tar cvzf archive_name.tar.gz dirname/
```
* 通过gzip过滤归档


### 1.4 解压一个`gzip`文件
```sh
$ tar xvfz archive_name.tar.gz
```
