package org.dllwh.template.database.mybatis.custom;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: myBatis-generator 集成 lomBok 的插件,自定义的lomBok注解配置
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-04-16
 * @版本: V 1.0.2
 * @since: JDK 1.8
 */
public class CustomLombokPlugin extends PluginAdapter {
	private final Collection<Annotations> annotations;
	/** 继承的父类 */
	private String supperClass;
	/** 过滤的属性字段 */
	private String ignoreFields;
	/** 自定义属性 - 创建人 */
	private String author;
	/** 自定义属性 - 创建邮箱 */
	private String email;
	/** 自定义属性 - 版本 */
	private String version;
	/** 判断继承类是否存在 */
	private static boolean fatherClassIsAvailable = false;

	public CustomLombokPlugin() {
		annotations = new LinkedHashSet<>(Annotations.values().length);
	}

	@Override
	public boolean validate(List<String> warnings) {
		boolean valid = true;
		supperClass = properties.getProperty("supperClass");
		ignoreFields = properties.getProperty("ignoreFields");
		author = properties.getProperty("author", "");
		email = properties.getProperty("email", "");
		version = properties.getProperty("version", "V 1.0.1");

		try {
			fatherClassIsAvailable = null != Class.forName(supperClass);
		} catch (ClassNotFoundException e) {
			warnings.add(getString("ValidationError.18", "CustomLombokPlugin", "supperClass"));
		}

		return valid;
	}

	/**
	 * 获取表
	 */
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		addAnnotations(topLevelClass);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		addAnnotations(topLevelClass);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		addAnnotations(topLevelClass);
		return true;
	}

	/**
	 * 设置get方法(使用lombok不需要,直接返回false)
	 */
	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}

	/**
	 * 设置set方法(使用lombok不需要,直接返回false)
	 */
	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}

	/**
	 * 设置lombok注解 <br>
	 */
	private void addAnnotations(TopLevelClass topLevelClass) {
		for (Annotations annotation : annotations) {
			// 添加domain的import
			topLevelClass.addImportedType(annotation.javaType);
			// 添加domain的注解
			topLevelClass.addAnnotation(annotation.asAnnotation());
		}

		/**
		 * 设置父类
		 */
		if (fatherClassIsAvailable) {
			FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(supperClass);
			topLevelClass.addImportedType("lombok.EqualsAndHashCode");
			topLevelClass.addImportedType(fullyQualifiedJavaType.getFullyQualifiedName());
			topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = false)");
			topLevelClass.setSuperClass(fullyQualifiedJavaType.getShortName());
		}

		/**
		 * 将需要忽略生成的属性过滤掉
		 */
		List<Field> fields = topLevelClass.getFields();
		if (StringUtils.isNotBlank(ignoreFields) && fatherClassIsAvailable) {
			String[] field = ignoreFields.split(",");
			for (String ignoreField : field) {
				for (int i = 0; i < fields.size(); i++) {
					Field tableField = fields.get(i);
					if (ignoreField.equalsIgnoreCase(tableField.getName())) {
						fields.remove(tableField);
						i--;
					}
				}
			}
		}
	}

	/**
	 * entity类设置
	 * 
	 * @param properties
	 */
	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);

		// @Data is default annotation
		annotations.add(Annotations.DATA);
		annotations.add(Annotations.ALL_ARGS_CONSTRUCTOR);
		annotations.add(Annotations.NO_ARGS_CONSTRUCTOR);
		annotations.add(Annotations.BUILDER);

		for (String annotationName : properties.stringPropertyNames()) {
			if (annotationName.contains(".")) {
				continue;
			}
			String value = properties.getProperty(annotationName);
			if (!Boolean.parseBoolean(value)) {
				// The annotation is disabled, skip it
				continue;
			}
			Annotations annotation = Annotations.getValueOf(annotationName);
			if (annotation == null) {
				continue;
			}
			String optionsPrefix = annotationName + ".";
			for (String propertyName : properties.stringPropertyNames()) {
				if (!propertyName.startsWith(optionsPrefix)) {
					// A property not related to this annotation
					continue;
				}
				String propertyValue = properties.getProperty(propertyName);
				annotation.appendOptions(propertyName, propertyValue);
				annotations.add(annotation);
				annotations.addAll(Annotations.getDependencies(annotation));
			}
		}
	}

	/**
	 * mapper类设置注解以及Mapper文件的注释
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		interfaze.addJavaDocLine("/**");
		interfaze.addJavaDocLine(" * 把今天最好的表现当作明天最新的起点．．～");
		interfaze.addJavaDocLine(" * ");
		interfaze.addJavaDocLine(" * Today the best performance as tomorrow newest starter!");
		interfaze.addJavaDocLine(" * ");
		interfaze.addJavaDocLine(" * @类描述 : TODO(这里用一句话描述这个类的作用)");

		// 获取表注释
		String tableRemarks = introspectedTable.getRemarks();

		if (stringHasValue(tableRemarks)) {
			interfaze.addJavaDocLine(" * ");
			interfaze.addJavaDocLine(" * @数据表注释 : ");
			String[] remarkLines = tableRemarks.split(System.getProperty("line.separator"));
			for (String remarkLine : remarkLines) {
				interfaze.addJavaDocLine(" * " + remarkLine);
			}
		}
		if (stringHasValue(tableRemarks)) {
			interfaze.addJavaDocLine(" * ");
			interfaze.addJavaDocLine(" * @数据表注释 : ");
			String[] remarkLines = tableRemarks.split(System.getProperty("line.separator"));
			for (String remarkLine : remarkLines) {
				interfaze.addJavaDocLine(" * " + remarkLine);
			}
		}
		interfaze.addJavaDocLine(" * ");

		if (stringHasValue(email) && stringHasValue(author)) {
			interfaze.addJavaDocLine(" * @author : <a href=\"mailto:" + email + "\"> " + author + "</a>");
		} else if (stringHasValue(author) && StringUtils.isBlank(email)) {
			interfaze.addJavaDocLine(" * @author : " + author);
		}

		// interfaze.addJavaDocLine(" * @创建时间 : " + getDateString());
		interfaze.addJavaDocLine(" * @版本 : " + version);
		interfaze.addJavaDocLine(" * @since : " + System.getProperty("java.version"));

		interfaze.addJavaDocLine(" * @see <a href=\"\">TODO(连接内容简介)</a>");
		interfaze.addJavaDocLine(" */");

		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
		interfaze.addAnnotation("@Mapper");
		return true;
	}

	/**
	 * entity字段设置
	 */
	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, Plugin.ModelClassType modelClassType) {
		if (field.getType().getShortNameWithoutTypeArguments().equals("Date")) {
			field.getAnnotations().add(Annotations.DATE_TIME_FORMAT.asAnnotation());
			field.getAnnotations().add(Annotations.JSON_FORMAT.asAnnotation());
			topLevelClass.addImportedType(Annotations.DATE_TIME_FORMAT.javaType);
			topLevelClass.addImportedType(Annotations.JSON_FORMAT.javaType);
		}
		return true;
	}

	enum Annotations {

		DATA("data", "@Data", "lombok.Data"), BUILDER("builder", "@Builder", "lombok.Builder"),
		ALL_ARGS_CONSTRUCTOR("allArgsConstructor", "@AllArgsConstructor", "lombok.AllArgsConstructor"),
		NO_ARGS_CONSTRUCTOR("noArgsConstructor", "@NoArgsConstructor", "lombok.NoArgsConstructor"),
		ACCESSORS("accessors", "@Accessors", "lombok.experimental.Accessors"),
		TO_STRING("toString", "@ToString", "lombok.ToString"),
		DATE_TIME_FORMAT("dateTimeFormat", "@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")",
				"org.springframework.format.annotation.DateTimeFormat"),
		JSON_FORMAT("jsonFormat", "@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")",
				"com.fasterxml.jackson.annotation.JsonFormat");

		private final String paramName;
		private final String name;
		private final FullyQualifiedJavaType javaType;
		private final List<String> options;

		Annotations(String paramName, String name, String className) {
			this.paramName = paramName;
			this.name = name;
			this.javaType = new FullyQualifiedJavaType(className);
			this.options = new ArrayList<String>();
		}

		public static Annotations getValueOf(String paramName) {
			for (Annotations annotation : Annotations.values()) {
				if (String.CASE_INSENSITIVE_ORDER.compare(paramName, annotation.paramName) == 0) {
					return annotation;
				}
			}

			return null;
		}

		public static Collection<Annotations> getDependencies(Annotations annotation) {
			if (annotation == ALL_ARGS_CONSTRUCTOR) {
				return Collections.singleton(NO_ARGS_CONSTRUCTOR);
			} else {
				return Collections.emptyList();
			}
		}

		// A trivial quoting.
		// Because Lombok annotation options type is almost String or boolean.
		private static String quote(String value) {
			if (Boolean.TRUE.toString().equals(value) || Boolean.FALSE.toString().equals(value))
			// case of boolean, not passed as an array.
			{
				return value;
			}
			return value.replaceAll("[\\w]+", "\"$0\"");
		}

		public void appendOptions(String key, String value) {
			String keyPart = key.substring(key.indexOf(".") + 1);
			String valuePart = value.contains(",") ? String.format("{%s}", value) : value;
			this.options.add(String.format("%s=%s", keyPart, quote(valuePart)));
		}

		public String asAnnotation() {
			if (options.isEmpty()) {
				return name;
			}
			StringBuilder sb = new StringBuilder();
			sb.append(name);
			sb.append("(");
			boolean first = true;
			for (String option : options) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				sb.append(option);
			}
			sb.append(")");
			return sb.toString();
		}
	}
}
