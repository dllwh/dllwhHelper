package org.dllwh.utils.application.email.entity;

import java.io.Serializable;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-08-09 11:03:02 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class MailDetail implements Serializable {
	private static final long serialVersionUID = 7856611117848190588L;
	/** 邮箱中总共有多少封信 */
	private int messageCount;
	/** 邮箱中新邮件的封数 */
	private int newMessageCount;
	/** 邮箱中未读邮件的封数 */
	private int unreadMessageCount;
	/** 邮箱中删除邮件数 */
	private int deletedMessageCount;

	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}

	public int getNewMessageCount() {
		return newMessageCount;
	}

	public void setNewMessageCount(int newMessageCount) {
		this.newMessageCount = newMessageCount;
	}

	public int getUnreadMessageCount() {
		return unreadMessageCount;
	}

	public void setUnreadMessageCount(int unreadMessageCount) {
		this.unreadMessageCount = unreadMessageCount;
	}

	public int getDeletedMessageCount() {
		return deletedMessageCount;
	}

	public void setDeletedMessageCount(int deletedMessageCount) {
		this.deletedMessageCount = deletedMessageCount;
	}

	@Override
	public String toString() {
		return "MailDetail [messageCount=" + messageCount + ", newMessageCount=" + newMessageCount
				+ ", unreadMessageCount=" + unreadMessageCount + ", deletedMessageCount=" + deletedMessageCount + "]";
	}

}