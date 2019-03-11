# 重写request的getSession方法实现集群session共享

```
<filter>
	<filter-name>sessionFilter</filter-name>
	<filter-class>org.dllwh.utils.web.sessoion.SessionFilter</filter-class>
	<init-param>
		<param-name>sessionHandler</param-name>
		<!-- 需实现SessionDAO接口的类的位置 -->
		<param-value>xx.xx.xx.SessionImpl</param-value>
	</init-param>
	<init-param>
		<!-- 因依赖于浏览器的cookie，这个地方设置cookie的有效时间 ,缺省值是1天，也就是24小时-->
		<param-name>expireTime</param-name>
		<param-value>3600</param-value>
	</init-param>
</filter>
```