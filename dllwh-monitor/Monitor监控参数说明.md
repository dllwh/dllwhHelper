[TOC]


# 1. 监控类型

|枚举类型|类型说明|
|---|---|
|**jvm**|monitor程序jvm监控|
|**cpu**|获取系统cpu信息|
|**sysmem**|获取系统内存相关信息|
|**os**|获取操作系统信息|
|**user**|获取系统用户相关信息|
|**fileSystem**|获取文件系统信息|
|**net**|获取网络相关信息|
|**directory**|获取目录的元数据信息|
|**jvmClassLoading**|获取java虚拟机类加载信息|
|**jvmCompilation**|获取java虚拟机类编译信息|
|**jvmGarbageCollector**|获取java虚拟机垃圾回收器信息|
|**jvmMemoryManager**|获取java虚拟机内存管理器信息|
|**jvMmemoryPool**|获取java虚拟机内存池信息|
|**jvmMemory**|获取java虚拟机内存信息|
|**jvmOperatingSystem**|获取java虚拟机所在操作信息信息
|**jvmRuntime**|获取java虚拟机运行时信息|
|**jvmThread**|获取java虚拟机线程相关信息|
|**httpRequest**|获取web系统request相关信息|
|**httpSession**|获取web系统session相关信息|


# 2. 操作系统基本监控

## 2.1 获取CPU信息

### 2.1.1 Cpu信息:CpuInfo

|参数|类型|说明|
|---|---|---|
|mhz|int|CPU的总量MHz|
|vendor|String|获得CPU的卖主，如：Intel|
|model|String|获得CPU的类别，如：Celeron|
|cacheSize|long|缓冲存储器数量|
|totalCores|int|总核数|
totalSockets|int|总socket的数|

### 2.1.2 Cpu使用率:CpuPerc

|参数|类型|说明|
|---|---|---|
|user|double|CPU用户使用率|
|sys|double|CPU系统使用率|
|wait|double|CPU当前等待率|
|nice|double|CPU当前错误率|
|idle|double|CPU当前空闲率|
|combined|double|CPU总的使用率|
|irq|double|CPU硬件中断时间|
|softIrq|double|CPU软件中断时间|
|stolen|double|CPU使用内部虚拟机运行任务的时间|

## 2.2 获取指定文件目录信息:directory

|参数|类型|说明|
|---|---|---|
|dirPath|String|目录路径|
|createDate|long|创建时间|
|lastAccessTime|long|最后访问时间|
|size|long|大小|
|lastModifiedTime|long|最后编辑时间|
|isSymbolicLink|int|是否是符号连接（文件快捷方式）|
|isDirectory|int|是否目录|
|isRegularFile|int|是否是普通文件|
|isHidden|int|是否隐藏|
|isExists|int|是否存在|

## 2.3 获取文件系统信息:fileSystem

|参数|类型|说明|
|---|---|---|
|devName|String|盘符名称|
|dirName|String|盘符路径|
|flags|long|盘符标志|
|options|String||
|sysTypeName|String|盘符类型|
|type|int|盘符文件系统类型|
|typeName|String|盘符类型名|
|avail|long||
|diskQueue|double||
|diskReadBytes|long|读出字节|
|diskReads|long|读出|
|diskServiceTime|double||
|diskWriteBytes|long|写入字节|
|diskWrites|long|写入|
|files|long||
|free|long|剩余大小(KB)|
|freeFiles|long||
|total|long|总大小（KB）|
|used|long|已经使用量(KB)|
|usePercent|double|资源的利用率|

## 2.4 获取系统物理内存信息:mem

|参数|类型|说明|
|---|---|---|
|total|long|内存总量（Byte）|
|ram|long|内存总量（MB）|
|used|long|当前内存使用量|
|free|long|当前内存剩余量|
|actualUsed|long|真实使用大小|
|actualFree|long|真实空闲大小|
|usedPercent|double|使用百分比|
|freePercent|double|空闲百分比|
|swapTotal|long|交换区总量|
|swapUsed|long|当前交换区使用量|
|swapFree|long|当前交换区剩余量|
|swapPageIn|long|Swap page in|
|swapPageOut|long|Swap page out|

## 2.5 获取网络信息:Net

|参数|类型|说明|
|---|---|---|
|name|String|网络设备名称|
|hwaddr|String|网卡的物理地址|
|type|String|网卡类型|
|description|String|网卡描述信息|
|address|String|IP地址|
|destination|String||
|broadcast|String|网关广播地址|
|netmask|String|子网掩码|
|flags|long||
|mtu|long|设置网卡的最大传输单元|
|metric|long||
|rxBytes|long|接收到的总字节数|
|rxPackets|long|接收的总包裹数|
|rxErrors|long|接收到的错误包数|
|rxDropped|long|接收时丢弃的包数|
|rxOverruns|long||
|rxFrame|long||
|txBytes|long|发送的总字节数|
|txPackets|long|发送的总包裹数|
|txErrors|long|发送数据包时的错误数|
|txDropped|long|发送时丢弃的包数|
|txOverruns|long||
|txCollisions|long||
|txCarrier|long||
|speed|long||

## 2.6 获取操作系统信息:operatingSystem

|参数|类型|说明|
|---|---|---|
|name|String|操作系统类型名|
|version|String|操作系统的版本号|
|arch|String|操作系统|
|machine|String||
|description|String|操作系统的描述|
|patchLevel|String||
|vendor|String|操作系统的卖主|
|vendorVersion|String|操作系统卖主类型|
|vendorName|String|操作系统名称|
|vendorCodeName|String|操作系统的卖主名|
|dataModel|String|操作系统DataModel()|
|cpuEndian|String|操作系统CpuEndian()|

## 2.7 获取系统用户信息:user

|参数|类型|说明|
|---|---|---|
|user|String|当前系统进程表中的用户名|
|device|String|用户控制台|
|host|String|用户host|
|time|long||

# 3. 获取JVM监控信息

## 3.1 获取JVM类加载信息:jVMClassLoading

|参数|类型|说明|
|---|---|---|
|loadedClassCount|long|返回当前加载到 Java 虚拟机中的类的数量|
|totalLoadedClassCount|long|返回自 Java 虚拟机开始执行到目前已经加载的类的总数|
|unloadedClassCount|long|返回自 Java 虚拟机开始执行到目前已经卸载的类的总数|
|isVerbose|boolean|测试是否已为类加载系统启用了 verbose 输出|

## 3.2 获取JVM类编译信息:jVMCompilation

|参数|类型|说明|
|---|---|---|
|name|String|返回即时 (JIT) 编译器的名称|
|totalCompilationTime|long|返回在编译上花费的累积耗费时间的近似值（以毫秒为单位）|
|isCompilationTimeMonitoringSupported|boolean|测试 Java虚拟机是否支持监视编译时间|

## 3.3 获取JVM垃圾收集信息:jVMGarbageCollector

|参数|类型|说明|
|---|---|---|
|name|String||
|collectionCount|Long|返回已发生的回收的总次数|
|collectionTime|Long|返回近似的累积回收时间（以毫秒为单位）|

## 3.4 获取JVM内存信息:jVMMemory

|参数|类型|说明|
|---|---|---|
|heapMemoryUsage|IFCJVMMemoryUsage|返回用于对象分配的堆的当前内存使用量|
|nonHeapMemoryUsage|IFCJVMMemoryUsage|返回 Java 虚拟机使用的非堆内存的当前内存使用量|
|objectPendingFinalizationCount|int|返回其终止被挂起的对象的近似数目|
|isVerbose|boolean|测试内存系统的 verbose 输出是否已启用|

## 3.5 获取内存管理器信息:jVMMemoryManager

|参数|类型|说明|
|---|---|---|
|name|String|返回表示此内存管理器的名称|
|memoryPoolNames|String[]|返回此内存管理器管理的内存池名称|

## 3.6 获取JVM内存池信息:jVMMemoryPool

|参数|类型|说明|
|---|---|---|
|edenMemoryUsage|JVMMemoryUsage|Eden区 GC信息|
|survivorMemoryUsage|JVMMemoryUsage|Survivor区 GC信息|
|codeCacheMemoryUsage|JVMMemoryUsage|代码缓冲区|
|permGenMemoryUsage|JVMMemoryUsage|Perm Gen区 GC信息|
|oldGenMemoryUsage|JVMMemoryUsage|Old Gen区 GC信息|

JVMMemoryUsage:

|参数|类型|说明|
|---|---|---|
|init|long|返回 Java 虚拟机最初从操作系统请求用于内存管理的内存量（以字节为单位）|
|used|long|返回已使用的内存量（以字节为单位）|
|committed|long|返回已提交给 Java 虚拟机使用的内存量（以字节为单位）|
|max|long|返回可以用于内存管理的最大内存量（以字节为单位）|

## 3.7 获取JVM所在操作系统信息:jVMOperatingSystem

|参数|类型|说明|
|---|---|---|
|arch|String|返回操作系统的架构|
|availableProcessors|int|返回 Java 虚拟机可以使用的处理器数目|
|name|double|返回操作系统名称|
|systemLoadAverage||返回最后一分钟内系统加载平均值|
|version|String|返回操作系统的版本|

## 3.8 获取JVM运行时参数及其它信息:jVMRuntime

|参数|类型|说明|
|---|---|---|
|name|String|返回表示正在运行的 Java 虚拟机的名称|
|bootClassPath|String|返回由引导类加载器用于搜索类文件的引导类路径|
|classPath|String|回系统类加载器用于搜索类文件的 Java 类路径|
|inputArguments|List<String>|返回传递给 Java 虚拟机的输入变量，其中不包括传递给 main方法的变量|
|libraryPath|String|返回 Java 库路径|
|managementSpecVersion|String|返回正在运行的 Java 虚拟机实现的管理接口的规范版本|
|specName|String|返回 Java 虚拟机规范名称|
|specVendor|String|返回 Java 虚拟机规范供应商|
|specVersion|String|返回 Java 虚拟机规范版本|
|startTime|long|返回 Java 虚拟机的启动时间（以毫秒为单位）|
|systemProperties|Map<String, String>|返回所有系统属性的名称和值的映射|
|uptime|long|返回 Java 虚拟机的正常运行时间（以毫秒为单位）|
|vmName|String|返回 Java 虚拟机实现名称|
|vmVendor|String|返回 Java 虚拟机实现供应商|
|vmVersion|String|返回 Java 虚拟机实现版本|
|isBootClassPathSupported|boolean|测试 Java虚拟机是否支持由引导类加载器用于搜索类文件的引导类路径机制|

## 3.9 获取JVM线程相关信息:jVMThread

|参数|类型|说明|
|---|---|---|
|findDeadlockedThreads|long[]|查找因为等待获得对象监视器或可拥有同步器而处于死锁状态的线程循环|
|findMonitorDeadlockedThreads|long[]|找到处于死锁状态（等待获取对象监视器）的线程的周期|
|allThreadIds|long[]|返回活动线程 ID|
|currentThreadCpuTime|long|返回当前线程的总 CPU 时间（以毫微秒为单位）|
|currentThreadUserTime|long|返回当前线程在用户模式中执行的 CPU 时间（以毫微秒为单位）|
|daemonThreadCount|int|返回活动守护线程的当前数目|
|peakThreadCount|int|返回自从 Java 虚拟机启动或峰值重置以来峰值活动线程计数|
|threadCount|int|返回活动线程的当前数目，包括守护线程和非守护线程|
|totalStartedThreadCountlong|返回自从 Java 虚拟机启动以来创建和启动的线程总数目|
|isCurrentThreadCpuTimeSupported|boolean|测试 Java 虚拟机是否支持当前线程的 CPU时间测量|
|isObjectMonitorUsageSupported|boolean|测试 Java 虚拟机是否支持使用对象监视器的监视|
|isSynchronizerUsageSupported|boolean|测试 Java 虚拟机是否支持使用可拥有同步器的监视|
|isThreadContentionMonitoringEnabled|boolean|测试是否启用了线程争用监视|
|isThreadContentionMonitoringSupported|boolean|测试 Java虚拟机是否支持线程争用监视|
|isThreadCpuTimeEnabled|boolean|测试是否启用了线程 CPU 时间测量|
|isThreadCpuTimeSupported|boolean|测试 Java 虚拟机实现是否支持任何线程的 CPU时间测量|
|threadInfoMap|Map<String, JVMThreadInfo>||

JVMThreadInfo:

|参数|类型|说明|
|---|---|---|
|blockedCount|long|返回与此 ThreadInfo 关联的线程被阻塞进入或重进入监视器的总次数|
|blockedTime|long|返回自从启用线程争用监视以来，与此 ThreadInfo 关联的线程被阻塞进入或重进入监视器的近似累计时间（以毫秒为单位）|
|lockName|String|返回对象的字符串表示形式，与此 ThreadInfo 关联的线程被锁定并等待该对象|
|lockOwnerId|long|返回拥有对象的线程的 ID，与此 ThreadInfo 关联的线程被阻塞并等待该对象|
|lockOwnerName|String|返回拥有对象的线程的名称，与此 ThreadInfo 关联的线程被阻塞并等待该对象|
|threadId|long|返回与此 ThreadInfo 关联的线程的 ID|
|threadName|String|返回与此 ThreadInfo 关联的线程的名称|
|threadState|Thread.State|返回与此 ThreadInfo 关联的线程的状态|
|waitedCount|long|返回与此 ThreadInfo 关联的线程等待通知的总次数|
|waitedTime|long|返回自从启用线程争用监视以来,与此 ThreadInfo 关联的线程等待通知的近似累计时间（以毫秒为单位）|
|isInNative|boolean|测试与此 ThreadInfo 关联的线程是否通过 Java 本机接口 (JNI) 执行本机代码|
|isSuspended|boolean|测试与此 ThreadInfo 关联的线程是否被挂起|
|threadCpuTime|long|该线程的总 CPU 时间（以毫微秒为单位）|
|threadUserTime|long|该线程在用户模式中执行的 CPU 时间（以毫微秒为单位）|

# 4. 获取WEB监控信息

## 4.1 获取Session请求信息:HttpSession

|参数|类型|说明|
|---|---|---|
|onlineUserCount|int|当前在线人数|
|getSessionAgeSum|long|当前在线人数平均在线时长（毫秒）|
|sessionDetailList|List<IFCSessionInformations>|Session详情|

SessionInformations

|参数|类型|说明|
|---|---|---|
|id|String|Id|
|lastAccess|Date|最后访问时间|
|age|Date|session 存活时间|
|expirationDate|Date|过期时间|
|attributeCount|int|session中存储对象属性个数|
|serializable|boolean|session对象中是否有序列化元素|
|country|String|访问用户所在国家|
|remoteAddr|String|远程地址|
|remoteUser|String|远程用户|
|serializedSize|int|session存储的序列化对象序列化字节大小|
|attributes|List<SessionAttribute>|Session中存储的属性集合|

SessionAttribute

|参数|类型|说明|
|---|---|---|
|name|String|属性名称|
|type|String|属性类型|
|content|String|属性内容String.valueOf|
|serializable|boolean|是否可以序列化|
|serializedSize|int|序列化大小|



## 4.2 获取Request相关信息:HttpRequest

|参数|类型|说明|
|---|---|---|
|globalRequestCounter|RequestCounter|全部请求统计|
|warningRequestCounter|RequestCounter|警告请求统计|
|severeRequestCounter|RequestCounter|严重请求统计|
|requestCounterList|List<IFCHttpRequestCounter>|每个请求地址统计详情|

HttpRequestCounter

|参数|类型|说明|
|---|---|---|
|Id|String|属性名称|
|name|String|请求路径名称|
|hits|long|访问次数|
|maximum|long|响应最长时间|
|cpuTimeSum|long|CPU执行时间和|
|cpuTimeMean|int|CPU平均执行时间|
|responseSizesSum|long|响应数据大小和|
|stackTrace|String|异常信息|
|mean|long|平均响应时间|
|standardDeviation|long|响应时间偏差|
|systemErrorPercentage|float|错误率|


## 4.3 获取Sql相关信息:Sql

|参数|类型|说明|
|---|---|---|
|SQL|String|执行sql语句|
|ExecuteCount|String|执行数|
|TotalTime|long|执行时间(ms)|
|MaxTimespan|long|最慢(ms)|
|InTransactionCount|long|事务中|
|ErrorCount|int|错误数|
|EffectedRowCount|long|更新行数|
|FetchRowCount|String|读取行数|
|RunningCount|long|执行中|
|ConcurrentMax|long|最大并发|
|Histogram|long[8]|执行时间分布(ms)|
|ExecuteAndResultHoldTimeHistogram|long[8]|执行+RS时分布|
|FetchRowCountHistogram|long[6]|读取行分布|
|EffectedRowCountHistogram|long[6]|更新行分布|


1. 执行时间分布参数说明

|||
|---|---|
|Histogram[0]|0-1毫秒执行次数|
|Histogram[1]|1-10毫秒执行次数|
|Histogram[2]|10-100毫秒执行次数|
|Histogram[3]|100-1000毫秒执行次数|
|Histogram[4]|1-10秒执行次数|
|Histogram[5]|10-100秒执行次数|
|Histogram[6]|100-1000秒执行次数|
|Histogram[7]|1000秒以上执行次数|

2. 执行+RS时分布参数说明

|||
|---|---|
|ExecuteAndResultHoldTimeHistogram[0]|0-1毫秒执行次数|
|ExecuteAndResultHoldTimeHistogram[1]|1-10毫秒执行次数|
|ExecuteAndResultHoldTimeHistogram[2]|10-100毫秒执行次数|
|ExecuteAndResultHoldTimeHistogram[3]|100-1000毫秒执行次数|
|ExecuteAndResultHoldTimeHistogram[4]|1-10秒执行次数|
|ExecuteAndResultHoldTimeHistogram[5]|10-100秒执行次数|
|ExecuteAndResultHoldTimeHistogram[6]|100-1000秒执行次数|
|ExecuteAndResultHoldTimeHistogram[7]|1000秒以上执行次数|

3. 读取行分布参数说明

|||
|---|---|
|FetchRowCountHistogram[0]|0-1行|
|FetchRowCountHistogram[1]|1-10行|
|FetchRowCountHistogram[2]|10-100行|
|FetchRowCountHistogram[3]|100-1000行|
|FetchRowCountHistogram[4]|1000-10000行|
|FetchRowCountHistogram[5]|10000行以上|

4. 更新行分布参数说明

|||
|---|---|
|EffectedRowCountHistogram[0]|0-1行|
|EffectedRowCountHistogram[1]|1-10行|
|EffectedRowCountHistogram[2]|10-100行|
|EffectedRowCountHistogram[3]|100-1000行|
|EffectedRowCountHistogram[4]|1000-10000行|
|EffectedRowCountHistogram[5]|10000行以上|