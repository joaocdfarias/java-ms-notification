package com.joaocdfarias.notification.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.joaocdfarias.notification.constants.MessagesConstant;
import com.joaocdfarias.notification.domain.Proposal;
import com.joaocdfarias.notification.service.NotificationSnsService;

@Component
public class FinishedProposalListener {

  private NotificationSnsService notificationSnsService;

  public FinishedProposalListener(NotificationSnsService notificationSnsService) {
    this.notificationSnsService = notificationSnsService;
  }

  @RabbitListener(queues = "${rabbitmq.queue.finished.proposal}")
  public void finishedProposal(Proposal proposal) {
    if (Boolean.TRUE.equals(proposal.getApproved())) {
      String message = String.format(MessagesConstant.PROPOSAL_FINISHED,
      proposal.getUser().getName());
      notificationSnsService.notify(message, proposal.getUser().getPhone());
    } else {
      String message = String.format(MessagesConstant.PROPOSAL_REPROVED, proposal.getUser().getName(),
          proposal.getObservation());
      notificationSnsService.notify(message, proposal.getUser().getPhone());
    }

  }
}
