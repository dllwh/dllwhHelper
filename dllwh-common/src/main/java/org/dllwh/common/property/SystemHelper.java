package org.dllwh.common.property;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * @类描述: 提供些获取系统信息相关的工具方法
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建日期: 2017年3月5日 下午3:36:38
 * @版本: V1.0
 * @since: JDK 1.7
 */
public class SystemHelper {
	/**
	 * JVM的版本
	 */
	public static final String	JVM_VERSION			= PropertyHelper.getSyskey(SysProperty.JAVA_VERSION);
	/**
	 * 主机架构
	 */
	public static final String	OS_ARCH				= PropertyHelper.getSyskey(SysProperty.OS_ARCH);
	/**
	 * 主机类型:取得当前OS的名称
	 */
	public static final String	OS_NAME				= PropertyHelper.getSyskey(SysProperty.OS_NAME);
	/**
	 * 主机类型版本:当前OS的版本
	 */
	public static final String	OS_VERSION			= PropertyHelper.getSyskey(SysProperty.OS_VERSION);
	/**
	 * 操作系统类型
	 */
	public static final String	SUN_DESKTOP			= PropertyHelper.getSyskey(SysProperty.SUN_DESKTOP);
	/**
	 * 当前用户
	 */
	public static final String	CURRENT_USER		= PropertyHelper.getSyskey(SysProperty.USER_NAME);

	/**
	 * 当前用户的家目录
	 */
	public static final String	CURRENT_USER_HOME	= PropertyHelper.getSyskey(SysProperty.USER_HOME);

	/**
	 * @方法描述: 获取当前所有的系统属性的名称
	 */
	public static List<Object> showKeys() {
		Properties props = System.getProperties();
		Enumeration<?> enu = props.propertyNames();
		List<Object> resultList = Lists.newArrayList();
		while (enu.hasMoreElements()) {
			Object key = enu.nextElement();
			resultList.add(key);
		}
		return resultList;
	}

	/**
	 * @方法描述: 获取主机IP
	 * @return
	 */
	public static String getHostIP() {
		String localip = "";
		InetAddress ip = null;
		List<InterfaceAddress> addressList = null;
		try {
			// 获得本机的所有网络接口
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

			while (nets.hasMoreElements()) {
				NetworkInterface netint = (NetworkInterface) nets.nextElement();

				addressList = netint.getInterfaceAddresses();
				for (InterfaceAddress interfaceAddress : addressList) {
					ip = interfaceAddress.getAddress();
					if (ip instanceof Inet4Address) { // 只关心 IPv4 地址
						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
								&& ip.getHostAddress().indexOf(":") == -1) {
							localip = ip.getHostAddress();
						} else {
							localip += ip;
						}
					}
				}

			}
			localip = localip.replaceAll("null", "");
		} catch (Exception e) {
			System.out.println("获取服务器IP出错");
		}
		return localip;
	}

	/**
	 * 获取JVM内存总量:系统总内存空间--已用内存(MB)
	 *
	 */
	public final static long JVMtotalMem() {
		return Runtime.getRuntime().totalMemory() / 1024 / 1024;
	}

	/**
	 * 虚拟机空闲内存量:系统内存的空闲空间--可用内存(MB)
	 *
	 */
	public final static long JVMfreeMem() {
		return Runtime.getRuntime().freeMemory() / 1024 / 1024;
	}

	/**
	 * 虚拟机使用最大内存量--最大内存(MB)
	 *
	 */
	public final static long JVMmaxMem() {
		return Runtime.getRuntime().maxMemory() / 1024 / 1024;
	}

	/**
	 * Sets HTTP proxy settings.
	 */
	public final static void setHttpProxy(String host, String port, String username, String password) {
		System.getProperties().setProperty("proxySet", "true");
		System.getProperties().put(SysProperty.HTTP_PROXY_HOST, host);
		System.getProperties().put(SysProperty.HTTP_PROXY_PORT, port);
		System.getProperties().put(SysProperty.HTTP_PROXY_USER, username);
		System.getProperties().put(SysProperty.HTTP_PROXY_PASSWORD, password);
	}

	/**
	 * Sets HTTP proxy settings.
	 */
	public final static void setHttpProxy(String host, String port) {
		System.getProperties().setProperty("proxySet", "true");
		System.getProperties().put(SysProperty.HTTP_PROXY_HOST, host);
		System.getProperties().put(SysProperty.HTTP_PROXY_PORT, port);
	}

	/**
	 * @方法描述: 匹配OS名称。
	 * @param osNamePrefix
	 * @return
	 */
	private final static boolean getOSMatches(String osNamePrefix) {
		return OS_NAME.startsWith(osNamePrefix);
	}

	/**
	 * @方法描述: 匹配OS名称
	 * @param osNamePrefix
	 *            OS名称前缀
	 * @param osVersionPrefix
	 *            OS版本前缀
	 * @return 如果匹配，则返回<code>true</code>
	 */
	private final static boolean getOSMatches(String osNamePrefix, String osVersionPrefix) {
		return OS_NAME.startsWith(osNamePrefix) && OS_NAME.startsWith(osVersionPrefix);
	}

	/**
	 * 判断当前OS的类型
	 */
	public static final boolean isLinux() {
		return getOSMatches("Linux") || getOSMatches("LINUX");
	}

	public final static boolean isMac() {
		return getOSMatches("Mac") || getOSMatches("os");
	}

	public final static boolean isWindows() {
		return getOSMatches("Windows") || getOSMatches("windows");
	}

	public final static boolean isWindowsME() {
		return getOSMatches("Windows", "4.9");
	}

	public final static boolean isWindowsNT() {
		return getOSMatches("Windows NT");
	}

	public final static boolean isWindowsXP() {
		return getOSMatches("Windows", "5.1");
	}

	/**
	 * @方法描述: 获取到classes目录
	 * @return
	 */
	public static String getClassPath() {
		String systemName = System.getProperty("os.name");

		// 判断当前环境，如果是Windows 要截取路径的第一个 '/'
		if (!StringUtils.isBlank(systemName) && systemName.indexOf("Windows") != -1) {
			return SystemHelper.class.getResource("/").getFile().toString().substring(1);
		} else {
			return SystemHelper.class.getResource("/").getFile().toString();
		}
	}

	/**
	 * 获取当前对象的路径
	 * 
	 * @param object
	 * @return path
	 */
	public static String getObjectPath(Object object) {
		return object.getClass().getResource(".").getFile().toString();
	}

	/**
	 * 获取到项目的路径
	 * 
	 * @return path
	 */
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	/**
	 * 获取 root目录
	 * 
	 * @return path
	 */
	public static String getRootPath() {
		return getWEB_INF().replace("WEB-INF/", "");
	}

	/**
	 * 获取 web-inf目录
	 * 
	 * @return path
	 */
	public static String getWEB_INF() {
		return getClassPath().replace("classes/", "");
	}
}
