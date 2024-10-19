package com.joaocdfarias.notification.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.joaocdfarias.notification.constants.MessagesConstant;
import com.joaocdfarias.notification.domain.Proposal;
import com.joaocdfarias.notification.service.NotificationSnsService;

@Component
public class PendingProposalListener {

  private NotificationSnsService notificationSnsService;

  public PendingProposalListener(NotificationSnsService notificationSnsService) {
    this.notificationSnsService = notificationSnsService;
  }

  @RabbitListener(queues = "${rabbitmq.queue.pending.proposal}")
  public void pendingProposal(Proposal proposal) {
    String message = String.format(MessagesConstant.PROPOSAL_IN_ANALYSIS, proposal.getUser().getName());
    notificationSnsService.notify(message, proposal.getUser().getPhone());
  }
}
