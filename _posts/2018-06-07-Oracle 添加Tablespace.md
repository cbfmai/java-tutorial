---
layout: post
title: Oracle 表空间
categories: [Oracle]
tags: [Oracle]
fullview: false
comments: true
---

Oarcle表空间（tablespaces）实际上是一个逻辑的概念，他在物理上是并不存在的，那么把一组data files 捻在一起就成为一个表空间；

在ORACLE数据库中，所有数据从逻辑结构上看都是存放在表空间当中，当然表空间下还有段、区、块等逻辑结构；

从物理结构上看是放在数据文件中。一个表空间可由多个数据文件组成；

#### 1. 表空间属性
* 一个数据库可以包含多个表空间，一个表空间只能属于一个数据库
* 一个表空间包含多个数据文件，一个数据文件只能属于一个表空间
* 表这空间可以划分成更细的逻辑存储单元


#### 2. 基本的表空间（系统中默认创建的几个表空间：）

* SYSTEM：　系统表表空间
* SYSAUX： 索引表空间
* USERS： 用户表空间
* UNDOTBS1： 回滚表空间
* EXAMPLE： 工具表空间
* TEMP： 临时表空间

#### 3. 表空间的分类

* 永久表空间                存放永久性数据，如表，索引等。
* 临时表空间                不能存放永久性对象，用于保存数据库排序，分组时产生的临时数据。
* UNDO表空间             保存数据修改前的镜象。

#### 4. 表空间的管理

* 字典管理：全库所有的空间分配都放在数据字典中。容易引起字典争用，而导致性能问题。
* 本地管理：空间分配不放在数据字典，而在每个数据文件头部的第3到第8个块的位图块，来管理空间分配。

#### 5. 基本操作

##### 5.1： 创建表空间

```
CREATE TABLESPACE TBS_NAME
DATAFILE '/oradata/TBS_NAME_001.dbf'
SIZE 100M
EXTENT MANAGEMENT LOCAL
SEGMENT SPACE MANAGEMENT AUTO ONLINE;
```


##### 5.2： 表空间管理

###### 5.2.1 ： 基本的使用情况

```sql
-- 包含数据库中所有表空间的描述信息
SELECT * FROM DBA_TABLESPACES

-- 包含当前用户的表空间的描叙信息
SELECT * FROM USER_TABLESPACES

--包含从控制文件中获取的表空间名称和编号信息
SELECT * FROM V$TABLESPACE;

--包含数据文件以及所属的表空间的描述信息
SELECT * FROM DBA_DATA_FILES

--包含临时数据文件以及所属的表空间的描述信息
SELECT * FROM DBA_TEMP_FILES

--包含从控制文件中获取的数据文件的基本信息，包括它所属的表空间名称、编号等
SELECT * FROM V$DATAFILE

--包含所有临时数据文件的基本信息
SELECT * FROM V$TEMPFILE
```
###### 5.2.2 ： 查看表空间使用情况

```sql
----查询表空间使用情况---
SELECT UPPER(F.TABLESPACE_NAME) "表空间名",
D.TOT_GROOTTE_MB "表空间大小(M)",
D.TOT_GROOTTE_MB - F.TOTAL_BYTES "已使用空间(M)",
TO_CHAR(ROUND((D.TOT_GROOTTE_MB - F.TOTAL_BYTES) / D.TOT_GROOTTE_MB * 100,2),'990.99') "使用比",
F.TOTAL_BYTES "空闲空间(M)",
F.MAX_BYTES "最大块(M)"
FROM (SELECT TABLESPACE_NAME,
ROUND(SUM(BYTES) / (1024 * 1024), 2) TOTAL_BYTES,
ROUND(MAX(BYTES) / (1024 * 1024), 2) MAX_BYTES
FROM SYS.DBA_FREE_SPACE
GROUP BY TABLESPACE_NAME) F,
(SELECT DD.TABLESPACE_NAME,
ROUND(SUM(DD.BYTES) / (1024 * 1024), 2) TOT_GROOTTE_MB
FROM SYS.DBA_DATA_FILES DD
GROUP BY DD.TABLESPACE_NAME) D
WHERE D.TABLESPACE_NAME = F.TABLESPACE_NAME
ORDER BY 4 DESC;
```

###### 5.2.3， 查询当前用户所在的表空间

```sql
select username, default_tablespace from user_users;
```


```sql
-- 查询表空间存储路径
select tablespace_name,file_id,bytes/1024/1024,file_name from dba_data_files order by tablespace_name;
```

```sql
-- 表空间的详细信息
SELECT T.TABLESPACE_NAME,D.FILE_NAME,
D.AUTOEXTENSIBLE,D.BYTES,D.MAXBYTES,D.STATUS
FROM DBA_TABLESPACES T,DBA_DATA_FILES D
WHERE T.TABLESPACE_NAME =D.TABLESPACE_NAME
 ORDER BY TABLESPACE_NAME,FILE_NAME;
```


##### 5.3： 维护表空间

```sql
1. 表空间状态有下面几种状态：online、offline、read only、read write

SELECT FILE#, STATUS, ENABLED FROM V$DATAFILE

2. 设置为脱机状态

SQL>ALTER TABLESPACE TBS_DM_DAT OFFLINE IMMEDIATE;

3. 设置为联机状态

SQL> ALTER TABLESPACE TBS_DM_DAT ONLINE;

4. 设置为只读状态

SQL>ALTER TABLESPACE TBS_DM_DAT READY ONLY;

5. 设置为读写状态

SQL>ALTER TABLESPACE TBS_DM_DAT READ WRITE;

6. 修改表空间名称

在ORACLE 10g 之前，表空间的名称是不能被修改的。在ORACLE 11G中，通过ALTER TABLESPACE 语句中使用RENAME子句，数据库管理员可以修改表空间的名称

7. 修改表空间自动增加打开

ALTER DATABASE DATAFILE '/database/oracle/oradata/gsp/tbs_dm_data_002.dbf' AUTOEXTEND ON;

8. 修改表空间自动增加关闭

ALTER DATABASE DATAFILE '/database/oracle/oradata/gsp/tbs_dm_data_002.dbf' AUTOEXTEND OFF;

9. 修改表空间的数据库文件大小

ALTER DATABASE DATAFILE  '/database/oracle/oradata/gsp/tbs_dm_data_002.dbf' resize 1921m;

10 新增表空间的一个数据库文件



ALTER TABLESPACE TBS_DM_DAT ADD DATAFILE '/database/oracle/oradata/gsp/tbs_dm_data_003.dbf' size 1921m;
```

##### 5.5： 操作

连接oracle linux server
```
su - oracle

sqlplus as / sysdba
```




