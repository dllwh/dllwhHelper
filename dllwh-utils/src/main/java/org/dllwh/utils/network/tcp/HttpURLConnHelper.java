package org.dllwh.utils.network.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.dllwh.common.browser.UserAgentType;
import org.dllwh.common.constanst.ConstantHelper;
import org.dllwh.common.exception.ExceptionHelper;
import org.dllwh.common.network.HttpMethod;
import org.dllwh.common.network.MyX509TrustManager;
import org.dllwh.common.network.UrlHelper;
import org.dllwh.utils.message.unicode.CharsetHelper;

/**
 * @描述:
 * 		<ul>
 *      <li>HttpURLConnection模拟HTTP请求网页内容</li>
 *      <li>Https协议工具类:封装了采用HttpURLConnection发送HTTP请求的GET\POST方法</li>
 *      <li>get请求可以获取静态页面，也可以把参数放在URL字串后面</li>
 *      <li>post的参数不是放在URL字串里面，而是放在http请求的正文内</li>
 *      </ul>
 * @author: 独泪了无痕
 * @date: 2015年6月14日 下午2:18:58
 * @version: V1.2
 */
public class HttpURLConnHelper {
	protected static Logger				logger		= Logger.getLogger(HttpURLConnHelper.class.getName());
	// 定义数据分隔线
	private static final String			BOUNDARY	= "---------7d4a6d158c9";
	private static final String			POST_HTTP	= HttpMethod.POST.getValue();
	private static final String			GET_HTTP	= HttpMethod.GET.getValue();
	/** 请求编码，默认使用utf-8 */
	private String						URLCHARSET;
	private static HttpURLConnHelper	instance;

	/** 转码 */
	private void setUrlCharset(String urlCharset) {
		URLCHARSET = urlCharset;
	}

	private HttpURLConnHelper() {

	}

	/**
	 * @方法描述: 初始化连接相关信息的Post、Get访问请求参数设置
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年10月12日 上午7:53:09
	 * @param url
	 *            请求地址
	 * @param RequestMethod
	 *            请求方法
	 * @param isUseProxy
	 *            是否代理
	 * @return
	 */
	private HttpURLConnection initConn(String url, String ReqMethod) throws Exception {
		HttpURLConnection httpConn = null;
		URLConnection urlConn = null;
		// 打开HttpURLConnection
		URL realUrl = new URL(url);

		urlConn = realUrl.openConnection();

		/** 设置http头通用的请求属性 */
		urlConn.setRequestProperty(HttpHeaders.ACCEPT, "*/*");
		urlConn.setRequestProperty(HttpHeaders.CONNECTION, "Keep-Alive");
		// 请求表示提交内容类型或返回返回内容的MIME类型
		urlConn.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
		// 设置 HttpURLConnection的字符编码,从而避免出现乱码
		urlConn.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, URLCHARSET);

		// 浏览页面的访问者在用什么操作系统(包括版本号)、浏览器(包括版本号)等
		urlConn.setRequestProperty(HttpHeaders.USER_AGENT, UserAgentType.PC_Firefox.getValue());

		boolean useHttps = url.startsWith("https");
		if (useHttps) {
			HttpsURLConnection httpsConn = (HttpsURLConnection) realUrl.openConnection();
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			httpsConn.setSSLSocketFactory(ssf);
			httpConn = (HttpsURLConnection) httpsConn;

		} else {
			httpConn = (HttpURLConnection) urlConn;
		}
		boolean caches = true;
		/** 发送POST请求必须设置如下所示 */
		if ("POST".equalsIgnoreCase(ReqMethod)) {
			/**
			 * 1.设置是否向httpUrlConnection输出,默认情况下是false<BR/>
			 * 2.是否打开输出流 true|false,表示向服务器写数据
			 */
			httpConn.setDoOutput(true);
			/**
			 * 1.设置是否从httpUrlConnection读入,默认情况下是true<br/>
			 * 2.是否打开输入流true|false,表示从服务器获取数据
			 */
			httpConn.setDoInput(true);
			/**
			 * 1.是否缓存true|false<BR/>
			 * 2.Post 请求不能使用缓存
			 */
			caches = false;
		}
		// 是否缓存true|false
		httpConn.setUseCaches(caches);
		// 设置连接超时时间，单位毫秒
		httpConn.setConnectTimeout(10 * 1000);
		// 设置读取数据超时时间，单位毫秒
		httpConn.setReadTimeout(60 * 1000);

		// 设置 HttpURLConnection的请求方式-->POST|GET,默认是GET
		if (POST_HTTP.equalsIgnoreCase(ReqMethod)) {
			httpConn.setRequestMethod(POST_HTTP);
		} else if (GET_HTTP.equalsIgnoreCase(ReqMethod)) {
			httpConn.setRequestMethod(GET_HTTP);
		}

		/** 参数配置必须要在connect之前完成 */
		return httpConn;
	}

	/** 利用 HttpURLConnection发送代理服务器的POST()方法的请求 */
	private String sendProxyRequest(String url, String params) throws Exception {
		String result = "";// 响应内容
		OutputStream outStrm = null;
		BufferedReader reader = null;
		HttpURLConnection httpConn = null;

		httpConn = initConn(url, POST_HTTP);

		// 建立实际的连接
		httpConn.connect();
		try {

			if (StringUtils.isNoneBlank(params)) {
				// 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。
				outStrm = httpConn.getOutputStream();

				// 向对象输出流写出数据，这些数据将存到内存缓冲区中
				outStrm.write(params.getBytes(URLCHARSET));

				// 刷新对象输出流，将任何字节都写入潜在的流中(此处为ObjectOutputStream)
				outStrm.flush();
				// 关闭流对象
				outStrm.close();
			}

			// HTTP 状态码(只有是200的时候才说明请求成功,其余皆失败)
			reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), URLCHARSET));
			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				String str = CharsetHelper.UnicodeToString(line);
				sb.append(str);
			}

			result = sb.toString();

		} catch (Exception e) {
			ExceptionHelper.catchHttpUtilException(e, url);
		} finally {
			// 释放资源
			IOUtils.closeQuietly(reader);
			IOUtils.close(httpConn);
		}

		return result;
	}

	/*--------------------------私有方法 end-------------------------------*/
	/*--------------------------公有方法 start-------------------------------*/

	public static HttpURLConnHelper getInstance() {
		init(ConstantHelper.UTF_8.name());
		return instance;
	}

	public static HttpURLConnHelper getInstance(String urlCharset) {
		init(urlCharset);
		return instance;
	}

	private static synchronized void init(String urlCharset) {
		if (instance == null) {
			instance = new HttpURLConnHelper();
		}
		if (StringUtils.isBlank(urlCharset)) {
			urlCharset = ConstantHelper.UTF_8.name();
		}
		// 设置默认的url编码
		instance.setUrlCharset(urlCharset);
	}

	/**
	 * @Title: sendGetRequest
	 * @Description: 利用 HttpURLConnection 模拟发送http get方法的请求
	 * @param url
	 *            发送请求的URL(服务器地址)
	 * @return
	 */
	public String sendGetRequest(String url) throws Exception {
		return sendGetRequest(url, null);
	}

	/**
	 * @Title：sendGetRequest
	 * @Description：利用 HttpURLConnection 模拟发送http get方法的请求
	 * @param url
	 *            发送请求的URL(服务器地址)
	 * @param paramsMap
	 *            参数位置在 urlParam 类型 请求参数
	 * @param headerMap
	 *            参数位置在 header 类型 请求参数
	 * @return
	 * @return：String 返回类型
	 */
	public String sendGetRequest(String url, Map<String, String> headerMap) throws Exception {
		String result = "";
		BufferedReader reader = null;
		HttpURLConnection httpConn = null;

		try {

			httpConn = initConn(url, HttpMethod.GET.getValue());
			if (MapUtils.isNotEmpty(headerMap)) {
				for (Map.Entry<String, String> entry : headerMap.entrySet()) {
					httpConn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			// 打开连接端口,建立实际的连接
			httpConn.connect();

			// HTTP 状态码(只有是200的时候才说明请求成功,其余皆失败)
			// 定义 BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), URLCHARSET));
			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				String str = CharsetHelper.UnicodeToString(line);
				sb.append(str);
			}

			result = sb.toString();

		} catch (Exception e) {
			ExceptionHelper.catchHttpUtilException(e, url);
		} finally {
			// 释放资源
			IOUtils.closeQuietly(reader);
			IOUtils.close(httpConn);
		}

		return result;
	}

	/**
	 * @方法名称: sendPostRequest
	 * @方法描述:利用 HttpURLConnection 模拟发送http POST方法的请求
	 * @param url
	 *            发送请求的 URL(服务器地址)
	 * @return 所代表远程资源的响应结果
	 */
	public String sendPostRequest(String url) throws Exception {
		return sendProxyRequest(url, null);
	}

	/**
	 * @方法名称: sendPostRequest
	 * @方法描述:利用 HttpURLConnection 模拟发送http POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL(服务器地址)
	 * @param isproxy
	 *            是否使用代理模式
	 * @return 所代表远程资源的响应结果
	 */
	public String sendPostRequest(String url, String params) throws Exception {
		return sendProxyRequest(url, params);
	}

	/**
	 * @Title: sendPostRequest
	 * @Description: 利用 HttpURLConnection 模拟发送http POST方法的请求
	 * @param url
	 *            发送请求的 URL(服务器地址)
	 * @param paramsMap
	 *            请求参数
	 * @return
	 */
	public String sendPostRequest(String url, Map<String, Object> paramsMap) throws Exception {
		String parameters = "";
		if (MapUtils.isNotEmpty(paramsMap)) {
			parameters = UrlHelper.formatParameters(paramsMap);
		}
		return sendProxyRequest(url, parameters);
	}

	/**
	 * 
	 * @Title: FileUpload
	 * @Description: 通过HTTP协议向指定的网络地址发送文件
	 * @author: 独泪了无痕
	 * @param url
	 *            设置要请求的服务器地址，即上传文件的地址
	 * @param file
	 * @return
	 */
	public String FileUpload(String url, File file) {
		if (!file.exists()) {
			return "";
		}
		BufferedReader br = null; // 请求后的返回信息的读取对象。
		OutputStream out = null;
		DataInputStream datain = null;
		HttpURLConnection httpConn = null;

		try {
			httpConn = (HttpURLConnection) new URL(url).openConnection();
			httpConn.setUseCaches(false);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod(POST_HTTP);
			httpConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);

			out = new DataOutputStream(httpConn.getOutputStream());
			// 定义最后数据分隔线
			byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			/**
			 * 设置文件数据
			 */
			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
			/**
			 * /获取文件的上传类型
			 */
			sb.append("Content-Type:application/octet-stream\r\n\r\n");

			byte[] data = sb.toString().getBytes(URLCHARSET);
			out.write(data);
			datain = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = datain.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			out.write(end_data);
			datain.close();
			out.flush();
			out.close();

			br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), URLCHARSET));

		} catch (FileNotFoundException fe) {
			if (httpConn != null) {
				InputStream err = ((HttpURLConnection) httpConn).getErrorStream();
				if (err == null) {
					br = new BufferedReader(new InputStreamReader(err));
				}
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(datain);
		}

		/**
		 * 返回提示信息
		 */
		StringBuffer response = new StringBuffer();
		String line;
		if (br != null) {
			try {
				while ((line = br.readLine()) != null) {
					response.append(line + "\n");
				}
			} catch (IOException ioe) {
				ioe.getStackTrace();
			} finally {
				IOUtils.closeQuietly(br);
				IOUtils.close(httpConn);
			}
		}
		return response.toString();
	}

	/**
	 * @方法描述: 检测当前URL是否可连接或是否有效,最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用
	 * @创建者: 皇族灬战狼
	 * @创建时间: 2016年10月12日 上午8:20:35
	 * @param url
	 *            指定URL网络地址
	 * @return
	 */
	public boolean isConnect(String url) {
		int count = 0;
		if (StringUtils.isEmpty(url)) {
			return false;
		}

		while (count < 5) {
			try {
				URL realUrl = new URL(url);
				HttpURLConnection httpConn = (HttpURLConnection) realUrl.openConnection();
				if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					return true;
				}
			} catch (Exception e) {
				count++;
				continue;
			}
		}
		return false;
	}

	public String getFileEncoding(URL url) {
		String regex = "charset=[\"']?([\\w-]+?)([^\\w-]|$)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		BufferedReader br = null;
		HttpURLConnection httpConn = null;

		try {
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestProperty(HttpHeaders.USER_AGENT, UserAgentType.PC_Firefox.getValue());
			String temp = httpConn.getContentType();
			httpConn.disconnect();
			if (StringUtils.isNotBlank(temp)) {

				Matcher matcher = pattern.matcher(temp);
				if (matcher.find()) {
					return matcher.group(1);
				}
			}

			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestProperty(HttpHeaders.USER_AGENT, UserAgentType.PC_Firefox.getValue());
			httpConn.setConnectTimeout(152000);
			httpConn.setReadTimeout(288000);
			httpConn.connect();
			br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			String line = br.readLine();
			while (line != null && (line.indexOf("charset=") == -1)) {
				line = br.readLine();
			}
			if (line != null) {
				line = line.trim();
				if (line.indexOf("<script") == -1) {

					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
						return matcher.group(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
			IOUtils.close(httpConn);
		}
		return null;
	}

	/**
	 * @方法描述: 获取sessionID
	 * @param url
	 * @return
	 */
	public String getSessionByPost(String url) {
		String sessionId = "";
		HttpURLConnection httpConn = null;
		try {
			URL realUrl = new URL(url);
			httpConn = (HttpURLConnection) realUrl.openConnection();
			httpConn.setRequestMethod("POST");
			String cookieValue = httpConn.getHeaderField("Set-Cookie");
			sessionId = cookieValue.substring(0, cookieValue.indexOf(";"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpConn != null) {
				httpConn.disconnect();
			}
		}
		return sessionId;
	}

	/**
	 * @方法描述: 带有sessionID的请求
	 * @param url
	 * @param sessionId
	 * @return
	 */
	public static String connectionBySession(String url, String sessionId) {
		String result = "";// 响应内容
		BufferedReader reader = null;
		HttpURLConnection con = null;
		try {
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Cookie", sessionId);
			reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String line;
			StringBuffer sb = new StringBuffer();

			while ((line = reader.readLine()) != null) {
				String str = CharsetHelper.UnicodeToString(line);
				sb.append(str);
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.close(con);
		}
		return result;
	}
}