package com.xiaofeihai.ispringmodulith.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author xumingming
 * @version 1.0
 * @date 2025/8/8 14:59
 */
@Slf4j
@Service
public class NotificationService {

    public void createNotification(NotificationDto notification) {
        log.info("Received notification by module dependency for product {} in date {} by {}.",
                notification.getProductName(),
                notification.getDate(),
                notification.getFormat());
    }

    @Async
    @EventListener
    public void notificationEvent(NotificationDto notification) {
        log.info("Received notificationEvent by module dependency for product {} in date {} by {}.",
                notification.getProductName(),
                notification.getDate(),
                notification.getFormat());
    }
}
