package org.dllwh.utils.network.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: ftp工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年6月10日 下午10:12:45
 * @版本: V1.0
 * @since: JDK 1.7
 */
public final class FtpHelper {

	/** FTP 登录账号 */
	private String ftpUsername;
	/** FTP 登录密码 */
	private String passWord;
	/** FTP 服务器地址IP地址 */
	private String hostName;
	/** FTP服务器端口 */
	private int ftpPort;

	public FtpHelper(String hostName, int ftpPort, String ftpUsername, String passWord) throws Exception {
		super();
		this.ftpUsername = ftpUsername;
		this.passWord = passWord;
		this.hostName = hostName;
		this.ftpPort = ftpPort;
	}

	/**
	 * 连接FTP服务器
	 *
	 * @throws IOException
	 */
	private FTPClient login() throws IOException {
		FTPClient ftpClient = new FTPClient();

		try {
			// 连接FTP服务器
			ftpClient.connect(hostName, ftpPort);
		} catch (Exception e) {
			throw new IOException("连接服务器失败,请检查主机[" + hostName + "],端口[" + ftpPort + "],用户名[" + ftpUsername + "],端口["
					+ ftpPort + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");

		}

		try {
			// 登录
			ftpClient.login(ftpUsername, passWord);
		} catch (Exception e) {
			throw new IOException("连接服务器失败,请检查主机[" + hostName + "],端口[" + ftpPort + "],用户名[" + ftpUsername
					+ "],密码是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
		}

		int reply = ftpClient.getReplyCode();
		// 根据状态码判断是否登录成功
		if (!FTPReply.isPositiveCompletion(reply)) {
			logout(ftpClient);
			throw new IOException("连接FTP服务器失败！！！");
		}
		return ftpClient;
	}

	/**
	 * 结束连接
	 *
	 * @param ftpClient
	 */
	private static void logout(FTPClient ftpClient) {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
			} catch (IOException ioe) {
			}
		}
	}

	/**
	 * 向FTP服务器上传文件
	 * 
	 * @param basePath FTP服务器基础目录
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2018/01/01。文件的路径为basePath+filePath
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input    输入流
	 * @return 成功返回true，否则返回false
	 * @throws IOException
	 */
	public boolean uploadFile(String basePath, String filePath, String filename, InputStream input) throws IOException {
		boolean result = false;
		FTPClient ftpClient = login();
		try {

			// 将客户端设置为被动模式
			ftpClient.enterLocalPassiveMode();
			// 切换到上传目录
			if (!ftpClient.changeWorkingDirectory(basePath + filePath)) {
				// 如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (StringUtils.isBlank(dir)) {
						continue;
					}
					tempPath += "/" + dir;
					// 进不去目录，说明该目录不存在
					if (!ftpClient.changeWorkingDirectory(tempPath)) {
						// 生成路径（一定要生成路径，不然如果路径不存在的话，怎么传都只会传到根目录下）
						if (!ftpClient.makeDirectory(tempPath)) {
							// 如果创建文件目录失败，则返回
							System.out.println("创建文件目录" + tempPath + "失败");
							return result;
						} else {
							// 目录存在，则直接进入该目录
							ftpClient.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			// 设置上传文件的类型为二进制类型
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 上传文件 成功true 失败 false
			if (!ftpClient.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftpClient.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(input);
			logout(ftpClient);
		}
		return result;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param remotePath FTP服务器上的相对路径
	 * @param fileName   要下载的文件名
	 * @param localPath  下载后保存到本地的路径
	 * @return
	 * @throws IOException
	 */
	public boolean downloadFile(String remotePath, String fileName, String localPath) throws IOException {
		boolean result = false;
		// 创建对象
		FTPClient ftpClient = login();
		try {

			// 将客户端设置为被动模式
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftpClient.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftpClient.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			logout(ftpClient);
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			logout(ftpClient);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		InputStream inputStream = new FileInputStream(new File("/Users/dllwh/Downloads/linux系统Jenkins自动部署说明.docx"));
		// uuid生成唯一名字 号称同一次元生成的uuid绝对不会重复
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		FtpHelper ftpHelper = new FtpHelper("172.20.98.98", 22, "root","YiPs1I9T&q");
		ftpHelper.login();
		// ftpToolHelper.uploadFile("/home/smartdot/", "/", uuid + "获取文件后缀名",
		// inputStream);

	}
}
