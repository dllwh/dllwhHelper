package org.dllwh.webCrawler.other.crawlercity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dllwh.common.browser.UserAgentType;
import org.dllwh.webCrawler.other.crawlercity.model.AreaInfo;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 利用网络爬虫端抓取行政区域数据
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-08-20 7:01:57 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */

public class CrawlerCityHelper {
	private static final Logger logger = LoggerFactory.getLogger(CrawlerCityHelper.class);

	/**
	 * @方法描述: 通过地址得到document对象
	 * @param url
	 * @return
	 */
	public static Document getDocument(String url) {
		Connection conn = null;
		Document document = null;
		try {
			conn = Jsoup.connect(url);// 获取连接
			conn.header("Connection", "keep-alive");
			conn.header("User-Agent", UserAgentType.PC_Firefox.getValue());// 配置模拟浏览器
			conn.method(Method.POST);
			conn.timeout(10000);
			document = conn.execute().parse();// 获取响应
			// 表示ip被拦截或者其他情况,反正没有获取document对象
			if (null == document || "".equals(document.toString())) {
				// IpUtilHelper.setProxyIp(); // 换代理ip
				getDocument(url);// 继续爬取网页
			}
			return document;
		} catch (Exception e) { // 链接超时等其他情况
			logger.error("", e);
			// IpUtilHelper.setProxyIp(); // 换代理ip
			getDocument(url);// 继续爬取网页
		}
		return getDocument(url);
	}

	/**
	 * @方法描述: 获取基本路径
	 * @return  返回最新基本路径
	 */
	public String getCrawlerBaseUrl() {
		Document document = getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/");
		Elements elements = document.getElementsByAttributeValue("class", "center_list_contlist");
		return elements.first().getElementsByTag("li").first().getElementsByTag("a").first().absUrl("href");

	}

	/**
	 * @方法描述: 省级行政区(省、自治区、直辖市、特别行政区)
	 * @param crawlerBaseUrl 请求路径
	 * @return 返回省级行政区数据
	 */
	public List<AreaInfo> getProvinceInfo(String crawlerBaseUrl) {
		Document document = getDocument(crawlerBaseUrl);
		Elements elements = document.getElementsByAttributeValue("class", "provincetr");
		List<AreaInfo> resultList = Lists.newArrayList();
		AreaInfo areaInfo = null;
		for (Element element : elements) {
			for (Element ele02 : element.getElementsByTag("a")) {
				areaInfo = new AreaInfo();
				areaInfo.setAreaCode(getProvinceCode(ele02.attr("href")));
				areaInfo.setAreaName(ele02.text());
				areaInfo.setAreaUrl(ele02.absUrl("href"));
				resultList.add(areaInfo);
			}
		}
		return resultList;
	}

	/**
	 * @方法描述: 获取特别地区数据
	 * @return 返回特别地区数据
	 */
	public List<AreaInfo> getSpecialArea() {
		List<AreaInfo> resultList = Lists.newArrayList();
		resultList.add(new AreaInfo("台湾省", "710000"));
		resultList.add(new AreaInfo("香港特别行政区", "810000"));
		resultList.add(new AreaInfo("澳门特别行政区", "820000"));
		resultList.add(new AreaInfo("钓鱼岛", "900000"));
		return resultList;
	}

	/**
	 * @方法描述: 地级行政区划单位(地级市、地区、自治州、盟)
	 * @param parentId   父级节点
	 * @param crawlerUrl 请求路径
	 * @return 返回地级行政区划单位数据
	 */
	public List<AreaInfo> getCityInfo(int parentId, String crawlerUrl) {
		Document document = getDocument(crawlerUrl);
		Elements elements = document.getElementsByAttributeValue("class", "citytr");
		List<AreaInfo> resultList = Lists.newArrayList();
		AreaInfo areaInfo = null;

		for (Element element : elements) {
			Elements eles = element.getElementsByTag("a");
			if (StringUtils.isBlank(eles.toString().trim())) {// 表示没有a标签
				continue;
			}
			areaInfo = new AreaInfo();
			areaInfo.setAreaCode(eles.get(0).text());
			areaInfo.setAreaName(eles.get(1).text());
			areaInfo.setAreaUrl(eles.get(0).absUrl("href"));
			areaInfo.setParentId(parentId);
		}
		return resultList;
	}

	/**
	 * @方法描述: 县级行政区划单位(市辖区、县级市、县、自治县、旗、自治旗、特区、林区)
	 * @param parentId   父级节点
	 * @param crawlerUrl 请求路径
	 * @return 返回县级行政区数据
	 */
	public List<AreaInfo> getCountyInfo(int parentId, String crawlerUrl) {
		Document document = getDocument(crawlerUrl);

		Elements elements = document.getElementsByAttributeValue("class", "countytr");

		List<AreaInfo> resultList = Lists.newArrayList();
		AreaInfo areaInfo = null;

		for (Element element : elements) {
			Elements eles = element.getElementsByTag("a");
			if (StringUtils.isBlank(eles.toString().trim())) {// 表示没有a标签
				continue;
			}
			areaInfo = new AreaInfo();
			areaInfo.setAreaCode(eles.get(0).text());
			areaInfo.setAreaName(eles.get(1).text());
			areaInfo.setAreaUrl(eles.get(0).absUrl("href"));
			areaInfo.setParentId(parentId);
		}
		return resultList;
	}

	/**
	 * @方法描述: 乡级行政区划单位(区公所、街道、镇、乡、民族乡、苏木、民族苏木)
	 * @param parentId   父级节点
	 * @param crawlerUrl 请求路径
	 * @return 返回乡级行政区数据
	 */
	public List<AreaInfo> getTownInfo(int parentId, String crawlerUrl) {
		Document document = getDocument(crawlerUrl);
		Elements elements = document.getElementsByAttributeValue("class", "towntr");

		List<AreaInfo> resultList = Lists.newArrayList();
		AreaInfo areaInfo = null;

		for (Element element : elements) {
			Elements eles = element.getElementsByTag("a");
			if (StringUtils.isBlank(eles.toString().trim())) {// 表示没有a标签
				continue;
			}
			areaInfo = new AreaInfo();
			areaInfo.setAreaCode(eles.get(0).text());
			areaInfo.setAreaName(eles.get(1).text());
			areaInfo.setAreaUrl(eles.get(0).absUrl("href"));
			areaInfo.setParentId(parentId);
		}
		return resultList;
	}

	/**
	 * @方法描述: 村级行政单位(村民委员会、居民委员会、类似村民委员会、类似居民委员)
	 * @param parentId   父级节点
	 * @param crawlerUrl 请求路径
	 * @return 返回村级行政数据
	 */
	public List<AreaInfo> getVillageInfo(int parentId, String crawlerUrl) {
		Document document = getDocument(crawlerUrl);
		Elements elements = document.getElementsByAttributeValue("class", "villagetr");
		List<AreaInfo> resultList = Lists.newArrayList();
		AreaInfo areaInfo = null;

		for (Element element : elements) {
			Elements eles = element.getElementsByTag("td");
			areaInfo = new AreaInfo();
			areaInfo.setAreaCode(eles.get(0).text());
			areaInfo.setAreaName(eles.get(1).text());
			areaInfo.setAreaUrl(eles.get(0).absUrl("href"));
			areaInfo.setItemCode(eles.get(1).text());
			areaInfo.setParentId(parentId);
			resultList.add(areaInfo);
		}
		return resultList;
	}

	/**
	 * @方法描述: 获取省份代码
	 * @return
	 */
	private static String getProvinceCode(String val) {

		if (val.indexOf(".") == -1)
			return val;
		val = val.substring(0, val.indexOf(".")) + "000000";
		return (String) val.substring(0, 6);
	}
}
