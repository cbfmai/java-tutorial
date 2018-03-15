---
layout: post
title: Java 进阶系列之（一）getResourceAsStream
categories: [Java]
tags: [Java]
fullview: false
comments: true
---

使用`java`读取文件， 一般情况下我们会使用到 `ClassLoader.getResourceAsStream()` 或者 `Class.getResourceAsStream()`

他们两者到底有什么区别？

我们先来看下定义

* 1, [Class.getResourceAsStream](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html#getResourceAsStream-java.lang.String-)


<div>
<pre>public&nbsp;<a href="../../java/io/InputStream.html" title="class in java.io">InputStream</a>&nbsp;getResourceAsStream(<a href="../../java/lang/String.html" title="class in java.lang">String</a>&nbsp;name)</pre>
<div class="block">Finds a resource with a given name.  The rules for searching resources
 associated with a given class are implemented by the defining class loader of the class.  This method
 delegates to this object's class loader.  If this object was loaded by
 the bootstrap class loader, the method delegates to <a href="../../java/lang/ClassLoader.html#getSystemResourceAsStream-java.lang.String-"><code>ClassLoader.getSystemResourceAsStream(java.lang.String)</code></a>.

 <p> Before delegation, an absolute resource name is constructed from the
 given resource name using this algorithm:

 <ul>

 <li> If the <code>name</code> begins with a <code>'/'</code>
 (<tt>'&#92;u002f'</tt>), then the absolute name of the resource is the
 portion of the <code>name</code> following the <code>'/'</code>.
如果名称以'/'（'\ u002f'）开始，那么资源的绝对名称是'/'后面的名称部分(从ClassPath根下获取)

 <li> Otherwise, the absolute name is of the following form:

 <blockquote>
   <code>modified_package_name/name</code>
 </blockquote>
  默认是从此类所在的包下取资源
 <p> Where the <code>modified_package_name</code> is the package name of this
 object with <code>'/'</code> substituted for <code>'.'</code>
 (<tt>'&#92;u002e'</tt>).

 </ul></div>
<dl>
<dt><span class="paramLabel">Parameters:</span></dt>
<dd><code>name</code> - name of the desired resource</dd>
<dt><span class="returnLabel">Returns:</span></dt>
<dd>A <a href="../../java/io/InputStream.html" title="class in java.io"><code>InputStream</code></a> object or <code>null</code> if
              no resource with this name is found</dd>
<dt><span class="throwsLabel">Throws:</span></dt>
<dd><code><a href="../../java/lang/NullPointerException.html" title="class in java.lang">NullPointerException</a></code> - If <code>name</code> is <code>null</code></dd>
<dt><span class="simpleTagLabel">Since:</span></dt>
<dd>JDK1.1</dd>
</dl>
</div>




* 2, [ClassLoader.getResourceAsStream](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html#getResourceAsStream-java.lang.String-)

都是获取绝对路径（classpath下， 不能以 "/" 开头）
ClassLoader.getResourceAsStream(path) will consider all paths to be absolute paths. 
So calling String.getClassLoader().getResourceAsStream("myfile.txt") and 
String.getClassLoader().getResourceAsStream("/myfile.txt") will 
both look for a file in your classpath at the following location: ./myfile.txt.


