package org.dllwh.template.database.mybatis.custom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: MyBatis Generator 自定义comment生成器.
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-04-10
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class CustomSQLCommentGenerator implements CommentGenerator {
	/** 属性，即配置在 commentGenerator 标签之内的 Property 标签 */
	private Properties properties;
	/** 生成的注释中是否不包含时间信息，默认为false */
	private boolean suppressDate;
	/** 是否去除自动生成的注释 */
	private boolean suppressAllComments;
	/** 是否添加数据库内的注释 */
	private boolean addRemarkComments;
	/** properties配置文件 */
	private Properties systemPro;
	/** 生成的注释中，时间的显示格式 */
	private SimpleDateFormat dateFormat;
	/** 自定义属性 - 创建人 */
	private String author;
	/** 自定义属性 - 创建邮箱 */
	private String email;
	/** 自定义属性 - 版本 */
	private String version;
	/** 自定义属性 - 是否添加get注释 */
	private boolean addGetComments;
	/** 自定义属性 - 是否添加set注释 */
	private boolean addSetComments;

	public CustomSQLCommentGenerator() {
		super();
		properties = new Properties();
		systemPro = System.getProperties();
		suppressDate = false;
		suppressAllComments = false;
		addRemarkComments = true;
	}

	/**
	 * 从该配置中的任何属性添加此实例的属性CommentGenerator配置,这个方法将在任何其他方法之前被调用。
	 */
	@Override
	public void addConfigurationProperties(Properties properties) {
		// 获取自定义的 properties
		this.properties.putAll(properties);

		author = properties.getProperty("author", "");
		email = properties.getProperty("email", "");
		version = properties.getProperty("version", "V 1.0.1");
		addGetComments = "false".equalsIgnoreCase(properties.getProperty("addGetComments")) ? false : true;
		addSetComments = "false".equalsIgnoreCase(properties.getProperty("addSetComments")) ? false : true;

		String dateFormatString = properties.getProperty("dateFormat", "yyyy-MM-dd HH:mm:ss");
		if (StringUtility.stringHasValue(dateFormatString)) {
			dateFormat = new SimpleDateFormat(dateFormatString);
		}
	}

	/**
	 * 生成xx.java文件（model）属性的注释,注释为空就不给属性添加。
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		// 获取列注释
		String remarks = introspectedColumn.getRemarks();

		// 开启注释，并且数据库中comment有值
		if (addRemarkComments && StringUtils.isNotBlank(remarks)) {
			// 通过换行符分割
			String[] remarkLines = remarks.split(systemPro.getProperty("line.separator"));
			int length = remarkLines.length;
			// 如果有多行，就换行显示
			if (length > 1) {
				// 注释开始的地方
				field.addJavaDocLine("/**");
				for (int i = 0; i < length; i++) {
					field.addJavaDocLine(" * " + remarkLines[i]);
				}
				// 注释结束
				field.addJavaDocLine(" */");
			} else {
				field.addJavaDocLine("/** " + remarks + " */");
			}
		}
	}

	/**
	 * Java属性注释,注释为空就不给属性添加。
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
	}

	/**
	 * 创建的数据表对应的类添加的注释
	 */
	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments || !addRemarkComments) {
			return;
		}
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * 把今天最好的表现当作明天最新的起点．．～");
		topLevelClass.addJavaDocLine(" * ");
		topLevelClass.addJavaDocLine(" * Today the best performance as tomorrow newest starter!");
		topLevelClass.addJavaDocLine(" * ");
		topLevelClass.addJavaDocLine(" * @类描述 : TODO(这里用一句话描述这个类的作用)");

		// 数据库表名
		String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
		// 获取表注释
		String tableRemarks = introspectedTable.getRemarks();

		topLevelClass.addJavaDocLine(" * ");
		topLevelClass.addJavaDocLine(" * @数据表 : " + tableName);

		if (StringUtils.isNotBlank(tableRemarks)) {
			topLevelClass.addJavaDocLine(" * ");
			topLevelClass.addJavaDocLine(" * @数据表注释 : " + tableRemarks);
		}
		topLevelClass.addJavaDocLine(" * ");
		if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(author)) {
			topLevelClass.addJavaDocLine(" * @author : <a href=\"mailto:" + email + "\"> " + author + "</a>");
		} else if (StringUtils.isNotBlank(author) && StringUtils.isBlank(email)) {
			topLevelClass.addJavaDocLine(" * @author : " + author);
		}

		topLevelClass.addJavaDocLine(" * @创建时间 : " + getDateString());
		topLevelClass.addJavaDocLine(" * @版本 : " + version);
		topLevelClass.addJavaDocLine(" * @since : " + systemPro.getProperty("java.version"));
		topLevelClass.addJavaDocLine(" * @see <a href=\"\">TODO(连接内容简介)</a>");
		topLevelClass.addJavaDocLine(" */");
	}

	/**
	 * Java类的类注释
	 */
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
	}

	/**
	 * Java类的类注释
	 */
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (suppressAllComments) {
			return;
		}
	}

	/**
	 * 为枚举添加注释
	 */
	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
	}

	/**
	 * 数据库对应实体类的Getter方法注解
	 */
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments || !addGetComments) {
			return;
		}
		method.addJavaDocLine("/**");

		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(introspectedColumn.getRemarks());
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" *");
		}

		sb.setLength(0);
		sb.append(" * @return ");
		sb.append(introspectedColumn.getActualColumnName());

		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" - ");
			sb.append(introspectedColumn.getRemarks());

		}
		method.addJavaDocLine(sb.toString());
		method.addJavaDocLine(" */");
		return;
	}

	/**
	 * 数据库对应实体类的Setter方法注解
	 */
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments || !addSetComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" * ");
			sb.append(introspectedColumn.getRemarks());
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" *");
		}

		Parameter parm = method.getParameters().get(0);
		sb.setLength(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" ");
			sb.append(introspectedColumn.getRemarks());
		}
		method.addJavaDocLine(sb.toString());
		method.addJavaDocLine(" */");
	}

	/**
	 * 普通方法的注释，这里主要是XXXMapper.java里面的接口方法的注释
	 */
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * @方法描述 : " + method.getName());
		method.addJavaDocLine(" * ");
		List<Parameter> parameters = method.getParameters();
		parameters.forEach(parameter -> method.addJavaDocLine(" * @param " + parameter.getName()));
		method.addJavaDocLine(" * ");
		// 如果有返回类型，添加@return
		String returnType = "void";
		if (!returnType.equals(method.getReturnType().toString())) {
			method.addJavaDocLine(" * @return ");
		}
		// addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	/**
	 * 给Java文件加注释，这个注释是在文件的顶部，也就是package上面。
	 */
	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		compilationUnit.addFileCommentLine("/*");
		compilationUnit.addFileCommentLine("*");
		compilationUnit.addFileCommentLine("* " + compilationUnit.getType().getShortName() + ".java");
		compilationUnit.addFileCommentLine("* Copyright(C) 2017-2020 xxx公司");
		compilationUnit.addFileCommentLine("* ALL rights reserved.");
		compilationUnit.addFileCommentLine("* @date " + getDateString() + "");
		compilationUnit.addFileCommentLine("*/");
	}

	/**
	 * Mybatis的Mapper.xml文件里面的注释
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
		if (suppressAllComments) {
			return;
		}
	}

	/**
	 * 为调用此方法作为根元素的第一个子节点添加注释。
	 */
	@Override
	public void addRootComment(XmlElement rootElement) {

	}

	/**
	 * @方法描述 : 返回格式化的日期字符串以包含在Javadoc标记中和XML注释。 如果您不想要日期，则可以返回null在这些文档元素中。
	 * @return 格式化后的日期
	 */
	protected String getDateString() {
		if (suppressDate) {
			return null;
		} else if (dateFormat != null) {
			return dateFormat.format(new Date());
		} else {
			return new Date().toString();
		}
	}

	/**
	 * @方法描述: 此方法为其添加了自定义javadoc标签。
	 *
	 * @param javaElement
	 * @param markAsDoNotDelete
	 */
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		javaElement.addJavaDocLine(" *");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge");
		}
		String s = getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}
}
