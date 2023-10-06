package com.teamluffy.tripproject.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamluffy.tripproject.chat.domain.entity.ChatMessage;
import com.teamluffy.tripproject.chat.service.MessagePublisherService;
import com.teamluffy.tripproject.chat.service.MessageSubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


  @MessageMapping("/chat/message")
  public void sendMessage(ChatMessage message) {
    switch (message.getType()) {
      case ENTER: {
        message.setMessage(message.getSender() + "님이 입장하였습니다.");
        break;
      }
      case LEAVE: {

        message.setMessage(message.getSender() + "님이 퇴장하였습니다.");
        break;
      }

    }
    log.info("Message Controller check");
    publisherService.sendMessage(message);
  }

  @GetMapping("/messages/{roomId}")
  public String getChatRoomMessages(@RequestHeader(name = "Authorization") String token,
      @PathVariable String roomId) {
    // TokenProvider를 통해 유저 정보 획득

    try {
      String jsonMessages = new ObjectMapper().writeValueAsString(
          subscriberService.getMessageList(roomId, "1")); // 유저아이디 임시로 '1' 입력
      return jsonMessages;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
