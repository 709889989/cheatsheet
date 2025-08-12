package com.xiaofeihai.ispringmodulith.product;

import com.xiaofeihai.ispringmodulith.notification.NotificationDto;
import com.xiaofeihai.ispringmodulith.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * @author xumingming
 * @version 1.0
 * @date 2025/8/8 14:55
 */
@Slf4j
@Service
public class ProductService {

    private final NotificationService notificationService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public ProductService(NotificationService notificationService, ApplicationEventPublisher applicationEventPublisher) {
        this.notificationService = notificationService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void create(Product product) {
        log.info("productService create.");
        notificationService.createNotification(new NotificationDto(new Date(), product.getName()));
    }

    public void createEvent(Product product) {
        log.info("productService createEvent.");
        applicationEventPublisher.publishEvent(new NotificationDto(new Date(), product.getName()));
        log.info("productService createEvent end.");
    }
}