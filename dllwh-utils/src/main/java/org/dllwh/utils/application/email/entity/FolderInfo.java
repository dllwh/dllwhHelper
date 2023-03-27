package org.dllwh.utils.application.email.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 邮箱列表信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-08-09 11:10:04 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class FolderInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String folderName;
	private String messageCount;

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(String messageCount) {
		this.messageCount = messageCount;
	}

	@Override
	public String toString() {
		return "FolderInfo [folderName=" + folderName + ", messageCount=" + messageCount + "]";
	}

}