[TOC]

# 系统信息收集API Sigar

## Sigard的介绍

&emsp;&emsp;Sigar是Hyperic-hq产品的基础包，是Hyperic HQ主要的数据收集组件，它用来从许多平台收集系统和处理信息。这些平台包括：Linux, Windows, Solaris, AIX, HP-UX, FreeBSD and Mac OSX。

## 收集的信息

&emsp;&emsp;Sigar API 提供一个方便的接口来收集系统信息，如：

1. CPU信息，包括基本信息（vendor、model、mhz、cacheSize）和统计信息（user、sys、idle、nice、wait）
2. 文件系统信息，包括Filesystem、Size、Used、Avail、Use%、Type
3. 事件信息，类似Service Control Manager
4. 内存信息，物理内存和交换内存的总数、使用数、剩余数；RAM的大小
5. 网络信息，包括网络接口信息和网络路由信息
6. 进程信息，包括每个进程的内存、CPU占用数、状态、参数、句柄
7. IO信息，包括IO的状态，读写大小等
8. 服务状态信息
9. 系统信息，包括操作系统版本，系统资源限制情况，系统运行时间以及负载，JAVA的版本信息等.

## 使用

&emsp;&emsp;Sigar有C，C#，Java和Perl API，java版的API为sigar.jar，sigar.jar的底层是用C语言编写的，它通过本地方法来调用操作系统API来获取系统相关数据。

&emsp;&emsp;Sigar为不同平台提供的不同库文件，Windows操作系统下 Sigar.jar 依赖  **sigar-amd64-winnt.dll** 或 **sigar-x86-winnt.dll**，linux 操作系统下则依赖**libsigar-amd64-linux.so** 或 **libsigar-x86-linux.so**。具体的对应关系如下：

|File|Language|Description|Required|
|---|---|---|---|
|sigar.jar|Java|Java API|Yes (for Java only)|
|log4j.jar|Java|Java logging API|No|
|libsigar-x86-linux.so|C|Linux AMD/Intel 32-bit|*|
|libsigar-amd64-linux.so|C|Linux AMD/Intel 64-bit|*|
|libsigar-ppc-linux.so|C|Linux PowerPC 32-bit|*|
|libsigar-ppc64-linux.so|C|Linux PowerPC 64-bit|*|
|libsigar-ia64-linux.so|C|Linux Itanium 64-bit|*|
|libsigar-s390x-linux.so|C|Linux zSeries 64-bit|*|
|sigar-x86-winnt.dll|C|Windows AMD/Intel 32-bit|*|
|sigar-amd64-winnt.dll|C|Windows AMD/Intel 64-bit|*|
|libsigar-ppc-aix-5.so|C|AIX PowerPC 32-bit|*|
|libsigar-ppc64-aix-5.so|C|AIX PowerPC 64-bit|*|
|libsigar-pa-hpux-11.sl|C|HP-UX PA-RISC 32-bit|*|
|libsigar-ia64-hpux-11.sl|C|HP-UX Itanium 64-bt|*|
|libsigar-sparc-solaris.so|C|Solaris Sparc 32-bit|*|
|libsigar-sparc64-solaris.so|C|Solaris Sparc 64-bit|*|
|libsigar-x86-solaris.so|C|Solaris AMD/Intel 32-bit|*|
|libsigar-amd64-solaris.so|C|Solaris AMD/Intel 64-bit|*|
|libsigar-universal-macosx.dylib|C|Mac OS X PowerPC/Intel 32-bit|*|
|libsigar-universal64-macosx.dylib|C|Mac OS X PowerPC/Intel 64-bit|*|
|libsigar-x86-freebsd-5.so|C|FreeBSD 5.x AMD/Intel 32-bit|*|
|libsigar-x86-freebsd-6.so|C|FreeBSD 6.x AMD/Intel 64-bit|*|
|libsigar-amd64-freebsd-6.so|C|FreeBSD 6.x AMD/Intel 64-bit|*|

## Java项目操作

- 在自己的Java项目中引入sigar.jar
- 同时将对应的dll文件或者so文件添加到与sigar.jar同一位置，保证能被系统调用。