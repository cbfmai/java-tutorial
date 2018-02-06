---
layout: post
title: CentOS 安装 Jdk
categories: [JAVA,Linux]
tags: [JAVA,Linux]
fullview: false
comments: true
---

### 1， Get Oracle JDK 8

1.1 打开`Oracle` jdk 官方网站 http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

1.2 下载

```sh

$ cd ~

$ wget --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u161-b12/2f38c3b165be4555a1fa6e98c45e0808/jdk-8u161-linux-x64.rpm

```


### 2, 安装 Oracle JDK 8
2.1 这里有两种方法， 一种 `rmp`, 另外 `yum localinstall`

```sh
rpm -ivh jdk-8u161-linux-x64.rpm
```

或者
```sh
yum localinstall jdk-8u161-linux-x64.rpm
```

2.2 检查安装地址
```
[root@localhost ~]# cd /usr/java/
[root@localhost java]# ls
default  jdk1.8.0_161  latest
[root@localhost java]# ls -lsah
total 12K
4.0K drwxr-xr-x.  3 root root 4.0K Feb  7 00:23 .
4.0K drwxr-xr-x. 14 root root 4.0K Dec 20 08:27 ..
   0 lrwxrwxrwx.  1 root root   16 Feb  7 00:23 default -> /usr/java/latest
4.0K drwxr-xr-x.  9 root root 4.0K Feb  7 00:22 jdk1.8.0_161
   0 lrwxrwxrwx.  1 root root   22 Feb  7 00:23 latest -> /usr/java/jdk1.8.0_161
```

### 3. 设置JAVA_HOME

3.1 编辑 `.bash_profile`
```sh
vim ~/.bash_profile


# Get the aliases and functions
if [ -f ~/.bashrc ]; then
	. ~/.bashrc
fi

# User specific environment and startup programs

export JAVA_HOME=/usr/java/jdk1.8.0_161/
export JRE_HOME=/usr/java/jdk1.8.0_161/jre

PATH=$PATH:$HOME/bin:$JAVA_HOME/bin

export PATH

```

3.2 测试`JAVA_HOME`

```sh
$ source ~/.bash_profile

$ echo $JRE_HOME
/usr/java/jdk1.8.0_161/jre

$ echo $JAVA_HOME
/usr/java/jdk1.8.0_161/

$ echo $PATH
/...:/usr/local/bin:/usr/X11R6/bin:/home/mkyong/bin:/usr/java/jdk1.8.0_161//bin
```

### 4. 多`JDK`

4.1 下载安装jdk 1.7
4.2 使用 `alternatives`

```sh
# alternatives --install /usr/bin/java java /usr/java/jdk1.7.0_80/bin/java 2
# alternatives --config java

There are 2 programs which provide 'java'.

  Selection    Command
-----------------------------------------------
*+ 1           /usr/java/jdk1.8.0_161/jre/bin/java
   2           /usr/java/jdk1.7.0_80/bin/java

Enter to keep the current selection[+], or type selection number: 2

``` 


At this point JAVA 8 has been successfully installed on your system. We also recommend to setup javac and jar commands path using alternatives

```sh
# alternatives --install /usr/bin/jar jar /usr/java/jdk1.7.0_80/bin/jar 2
# alternatives --install /usr/bin/javac javac /usr/java/jdk1.7.0_80/bin/javac 2
# alternatives --set jar /usr/java/jdk1.7.0_80/bin/jar
# alternatives --set javac /usr/java/jdk1.7.0_80/bin/javac
```


4.3 检查 Java version
4.4 设置 `JAVA_HOME` `JRE_HOME` & `PATH`





Other files and location where you can set Java variable and what are the difference
(1) `/etc/profile` = To set environment variable to all users

(2) `$HOME/.bashrc` = To set environment for login user.
(3) `$HOME/.bash_profile` = To set environment for login user

Note: `.bash_profile` is executed for login shells, while `.bashrc` is executed for interactive non-login shells.

(4) Create a shell script inside `/etc/profile.d/` with `.sh` extension. and make the file executable.

(5) Create a shell script in some other location and give its path in `/etc/rc.local`