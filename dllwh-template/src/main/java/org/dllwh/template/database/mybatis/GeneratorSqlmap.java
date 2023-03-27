package org.dllwh.template.database.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: MyBatis Generator（MBG）是MyBatis MyBatis 和iBATIS的代码生成器
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-04-12
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */

public class GeneratorSqlmap {
	public void defaultCommentGenerator() throws Exception {
		// MyBatis-Generator 执行过程中的警告信息
		List<String> warnings = new ArrayList<String>();
		// 当生成的代码重复时，覆盖原代码
		boolean overwrite = true;
		// 指向逆向工程配置文件
		String genCfg = "/generatorConfig.xml";
		// 读取 MyBatis-Generator 配置文件
		File configFile = new File(GeneratorSqlmap.class.getResource(genCfg).getFile());
		// 初始化配置解析器
		ConfigurationParser cp = new ConfigurationParser(warnings);
		// 调用配置解析器创建配置对象
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		// 创建一个MyBatisGenerator对象。MyBatisGenerator类是真正用来执行生成动作的类
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		// 执行生成代码
		myBatisGenerator.generate(null);

		// 输出警告信息
		warnings.forEach(warning -> System.out.println(warning));
	}

	public static void main(String[] args) throws Exception {
		try {
			GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
			generatorSqlmap.defaultCommentGenerator();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
