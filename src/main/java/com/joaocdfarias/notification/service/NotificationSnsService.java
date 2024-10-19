package com.joaocdfarias.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;

@Service
public class NotificationSnsService {

  @Autowired
  private AmazonSNS amazonSNS;

  public void notify(String message, String phone) {
    PublishRequest publishRequest = new PublishRequest().withMessage(message).withPhoneNumber(phone);
    amazonSNS.publish(publishRequest);
  }
}
