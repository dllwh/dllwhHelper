package org.dllwh.template.database.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class GeneratorSqlmap {
	public void generator() throws Exception {
		// MyBatis-Generator 执行过程中的警告信息
		List<String> warnings = new ArrayList<String>();
		// 当生成的代码重复时，覆盖原代码
		boolean overwrite = true;
		// 指向逆向工程配置文件
		String genCfg = "/generatorConfig.xml";
		// 读取 MyBatis-Generator 配置文件
		File configFile = new File(GeneratorSqlmap.class.getResource(genCfg).getFile());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		// 创建 MyBatis-Generator
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		// 执行生成代码
		myBatisGenerator.generate(null);
		// 输出警告信息
		warnings.forEach(warning -> System.out.println(warning));

	}

	public static void main(String[] args) throws Exception {
		try {
			GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
			generatorSqlmap.generator();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
