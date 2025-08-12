package com.xiaofeihai.ispringmodulith.notification;

import com.xiaofeihai.ispringmodulith.notification.internal.NotificationType;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author xumingming
 * @version 1.0
 * @date 2025/8/8 15:57
 */
@Data
public class NotificationDto {
    private String productName;
    private Date date;
    private NotificationType format;

    public NotificationDto(Date date, String productName) {
        this.productName = productName;
        this.date = date;
        this.format = NotificationType.SMS;
    }
}
