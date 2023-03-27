package org.dllwh.monitor.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取指定文件目录信息
 * @创建者: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-11-30 11:27:36 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class Directory implements Serializable {
	
	private static final long serialVersionUID = 29037974841681275L;
	/** 目录路径 */
	private String dirPath;
	/** 创建时间 */
	private long createDate;
	/** 最后访问时间 */
	private long lastAccessTime;
	/** 大小 */
	private long size;
	/** 最后编辑时间 */
	private long lastModifiedTime;
	/** 是否是符号连接（文件快捷方式） */
	private int isSymbolicLink;
	/** 是否目录 */
	private int isDirectory;
	/** 是否是普通文件 */
	private int isRegularFile;
	/** 是否隐藏 */
	private int isHidden;
	/** 是否存在 */
	private int isExists;
}
