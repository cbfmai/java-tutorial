---
layout: post
title: How to check linux kernel version?
categories: [linux]
tags: [linux]
fullview: false
comments: true
---

### 1, Use `uname`
```sh
$ uname -r
```
Sample outout
```sh
3.10.0-229.el7.x86_64
```
Try to print all info
```
$ uname -a
```
Sample output:
```
Linux ip-*.us-west-2.compute.internal 3.10.0-229.el7.x86_64 #1 SMP Thu Jan 29 18:37:38 EST 2015 x86_64 x86_64 x86_64 GNU/Linux
```
---
###  2, Use `/proc/version` file
Type the following command to see Linux version info:
```sh
$ cat /proc/version
```

output
```sh
Linux version 3.10.0-229.el7.x86_64 (mockbuild@x86-035.build.eng.bos.redhat.com) (gcc version 4.8.3 20140911 (Red Hat 4.8.3-7) (GCC) )

```
---
### 3, How about the distribution version?
Type the following command:
```sh
cat /etc/*release
```

```sh
NAME="Red Hat Enterprise Linux Server"
VERSION="7.2 (Maipo)"
ID="rhel"
ID_LIKE="fedora"
VERSION_ID="7.2"
PRETTY_NAME="Red Hat Enterprise Linux Server 7.2 (Maipo)"
ANSI_COLOR="0;31"
CPE_NAME="cpe:/o:redhat:enterprise_linux:7.2:GA:server"
HOME_URL="https://www.redhat.com/"
BUG_REPORT_URL="https://bugzilla.redhat.com/"

REDHAT_BUGZILLA_PRODUCT="Red Hat Enterprise Linux 7"
REDHAT_BUGZILLA_PRODUCT_VERSION=7.2
REDHAT_SUPPORT_PRODUCT="Red Hat Enterprise Linux"
REDHAT_SUPPORT_PRODUCT_VERSION="7.2"
Red Hat Enterprise Linux Server release 7.2 (Maipo)
Red Hat Enterprise Linux Server release 7.2 (Maipo
```
