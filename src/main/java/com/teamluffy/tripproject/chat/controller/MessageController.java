package com.teamluffy.tripproject.chat.controller;

import com.teamluffy.tripproject.chat.domain.model.chat.SendChatMessageForm;
import com.teamluffy.tripproject.chat.service.MessagePublisherService;
import com.teamluffy.tripproject.chat.service.MessageSubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class MessageController {

  private final MessagePublisherService publisherService;
  private final MessageSubscriberService subscriberService;
  private final TokenProvider provider;


  @MessageMapping("/chat/message")
  public void sendMessage(SendChatMessageForm sendChatMessageForm) {
    switch (sendChatMessageForm.getType()) {
      case ENTER: {
        sendChatMessageForm.setContent(sendChatMessageForm.getSender() + "님이 입장하였습니다.");
        break;
      }
      case LEAVE: {

        sendChatMessageForm.setContent(sendChatMessageForm.getSender() + "님이 퇴장하였습니다.");
        break;
      }

    }
    log.info("Message Controller check");
    publisherService.sendMessage(sendChatMessageForm);
  }

  @GetMapping("/messages/{roomId}")
  @PreAuthorize("hasRole('CUSTOMER')")
  public ResponseEntity<?> getChatRoomMessages(@RequestHeader("Cookie") String cookies,
      @PathVariable String roomId) {
    String token = provider.extractAccessTokenFromCookies(cookies);
    UserVo userVo = provider.getUserVo(token);

    return ResponseEntity.ok(
        subscriberService.getMessageList(roomId, String.valueOf(userVo.getId())));
  }

  private String extractTokenFromBearerString(String bearerToken) {
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }


}