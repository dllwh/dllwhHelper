package org.dllwh.common.property;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Java代码解析yml文件
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-05-22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public final class YmlHelper {
	private static Map<String, String> yamlMap = new HashMap<String, String>();

	/**
	 * 
	 * @方法描述: 根据指定路径文件获取yml的文件内容
	 *
	 * @param filePath 指定路径yaml 文件
	 */
	public static Map<String, String> getYmlByFileName(String filePath) {

		if (isBlank(filePath)) {
			return null;
		}

		// 配置文件路径
		InputStream inputStream = YmlHelper.class.getClassLoader().getResourceAsStream(filePath);
		if (inputStream != null) {
			Iterator<Object> result = new Yaml().loadAll(inputStream).iterator();
			while (result.hasNext()) {
				Map map = (Map) result.next();
				iteratorYml(map, null);
			}
		}
		if (inputStream != null) {
			IOUtils.closeQuietly(inputStream);
		}
		return yamlMap;
	}

	private static <K, V> void iteratorYml(Map<String, Object> map, String key) {
		Iterator<?> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<K, V> entry = (Entry<K, V>) iterator.next();
			Object key2 = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof LinkedHashMap) {
				if (key == null) {
					iteratorYml((Map) value, key2.toString());
				} else {
					iteratorYml((Map) value, key + "." + key2.toString());
				}
			}
			if (value instanceof String) {
				if (key == null) {
					yamlMap.put(key2.toString(), value.toString());
				}
				if (key != null) {
					yamlMap.put(key + "." + key2.toString(), value.toString());
				}
			}
		}
	}
}
