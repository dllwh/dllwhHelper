package org.dllwh.utils.application.email.model;

import lombok.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: 邮件发送相关信息
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2023-03-20 01:10
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MailSenderInfo extends BaseMailConfig {
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 收件人邮箱地址
     */
    private String receiver;
    /**
     * 抄送人邮箱地址
     */
    private String ccReceiver;
    /**
     * 密送人邮箱地址
     */
    private String bccReceiver;
    /**
     * 收件人邮箱地址列表
     */
    private String[] receivers;
    /**
     * 抄送人邮箱地址列表
     */
    private String[] ccReceivers;
    /**
     * 密送人邮箱地址列表
     */
    private String[] bccReceivers;
    /**
     * 邮件附件地址
     */
    String filePath;
    /**
     * 邮件图片地址
     */
    String imagePath;
}
