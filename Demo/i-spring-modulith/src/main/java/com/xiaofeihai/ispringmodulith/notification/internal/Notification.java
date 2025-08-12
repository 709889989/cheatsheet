package com.xiaofeihai.ispringmodulith.notification.internal;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author xumingming
 * @version 1.0
 * @date 2025/8/8 14:58
 */
@Data
public class Notification {

    private String productName;
    private Date date;
    private NotificationType format;

    public Notification(Date date, NotificationType format, String productName) {
        this.productName = productName;
        this.date = date;
        this.format = format;
    }
}
