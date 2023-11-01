package com.teamluffy.tripproject.chat.service;


import com.teamluffy.tripproject.chat.domain.model.chat.SendChatMessageForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessagePublisherService {

  private final RedisTemplate redisTemplate;
  private final PatternTopic chatMessagesPatterTopic;

  public void sendMessage(SendChatMessageForm sendChatMessageForm) {
    try {
      log.info(chatMessagesPatterTopic.getTopic());
      redisTemplate.convertAndSend(chatMessagesPatterTopic.getTopic(), sendChatMessageForm);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
