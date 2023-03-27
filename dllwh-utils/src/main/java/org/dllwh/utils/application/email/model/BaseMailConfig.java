package org.dllwh.utils.application.email.model;

import lombok.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: 邮件服务器需要使用的基本信息
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2023-03-25 23:51
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BaseMailConfig {
    /**
     * 服务器是否要验证用户的身份信息
     */
    private boolean validate = false;
    /**
     * 是否开启Session的debug模式，开启后可以查看到程序发送Email的运行状态
     */
    private boolean debugMode = false;
    /**
     * 发送邮件的服务器的IP或主机地址
     */
    private String mailServerHost;
    /**
     * 发送邮件的服务器的端口
     */
    private int mailServerPort = 25;
    /**
     * 邮件发送服务器的用户名(指的是邮箱地址)
     */
    private String mailServerUser;
    /**
     * 邮件发送服务器的密码或者授权码
     */
    private String mailServerPassword;
    /**
     * 发件人昵称
     */
    private String nickName;
    /**
     * 邮件发送的协议
     */
    private String transportType = "smtp";
    /**
     * 邮件存储的协议
     */
    private String storeType = "pop3";
}
