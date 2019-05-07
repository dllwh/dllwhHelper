package org.dllwh.utils.application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Desktop 类允许 Java 应用程序启动已在本机桌面上注册的关联应用程序，以处理 URI 或文件。
 * 
 *       <pre>
 *       1: getDesktop() :返回当前浏览器上下文的 Desktop 实例
*        2: isDesktopSupported()测试当前平台是否支持此类 
*        3: isSupported(Desktop.Action action):测试当前平台是否支持某一动作
*        4: browse(URI uri) 启动默认浏览器来显示 URI
*        5: open(File file):启动关联应用程序来打开文件
*        6: edit(File file) 启动关联编辑器应用程序并打开用于编辑的文件 
*        7: print(File file)使用关联应用程序的打印命令，用本机桌面打印设施来打印文件
*        8: mail(URI mailtoURI):启动用户默认邮件客户端的邮件组合窗口
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月8日 上午12:35:08
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class DesktopHelper {

	/**
	 * @方法描述:判断当前平台是否支持此类
	 * @return
	 */
	public static boolean isDesktopSupported() {
		return Desktop.isDesktopSupported();
	}

	/**
	 * @方法描述:获取与当前平台关联的 Desktop 实例
	 * @return
	 */
	public static Desktop getDesktop() {
		return Desktop.getDesktop();
	}

	/**
	 * @方法描述: 启动默认浏览器来显示 URI
	 * @param url
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void browse(String url) throws IOException, URISyntaxException {
		browse(new URI(url));
	}

	/**
	 * @方法描述:启动默认浏览器来显示 URI
	 * @param uri
	 * @throws IOException
	 */
	public void browse(URI uri) throws IOException {
		getDesktop().browse(uri);
	}

	/**
	 * @方法描述:启动关联应用程序来打开文件
	 * @param file
	 * @throws IOException
	 */
	public void open(String pathName) throws IOException {
		open(new File(pathName));
	}

	/**
	 * @方法描述:启动关联应用程序来打开文件
	 * @param file
	 * @throws IOException
	 */
	public void open(File file) throws IOException {
		getDesktop().open(file);
	}

	/**
	 * @方法描述: 启动关联编辑器应用程序并打开用于编辑的文件
	 * @param file
	 * @throws IOException
	 */
	public void edit(String pathName) throws IOException {
		edit(new File(pathName));
	}

	/**
	 * @方法描述: 启动关联编辑器应用程序并打开用于编辑的文件
	 * @param file
	 * @throws IOException
	 */
	public void edit(File file) throws IOException {
		getDesktop().edit(file);
	}

	/**
	 * @方法描述: 使用关联应用程序的打印命令, 用本机桌面打印设备来打印文件
	 * @param file
	 * @throws IOException
	 */
	public void print(String pathName) throws IOException {
		print(new File(pathName));
	}

	/**
	 * @方法描述: 使用关联应用程序的打印命令, 用本机桌面打印设备来打印文件
	 * @param file
	 * @throws IOException
	 */
	public void print(File file) throws IOException {
		getDesktop().print(file);
	}

	/**
	 * @throws IOException
	 * @方法描述:启动用户默认邮件客户端的邮件组合窗口
	 */
	public void mail() throws IOException {
		getDesktop().mail();
	}

	/**
	 * @方法描述: 启动用户默认邮件客户端的邮件组合窗口
	 * @param mailtoURI
	 *            指定的消息字段
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void mail(String mailtoURI) throws IOException, URISyntaxException {
		mail(new URI(mailtoURI));
	}

	/**
	 * @方法描述: 启动用户默认邮件客户端的邮件组合窗口
	 * @param mailtoURI
	 *            指定的消息字段
	 * @throws IOException
	 */
	public void mail(URI mailtoURI) throws IOException {
		getDesktop().mail(mailtoURI);
	}
}