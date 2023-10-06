package com.teamluffy.tripproject.chat.service;


import com.teamluffy.tripproject.chat.domain.entity.ChatMessage;
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
  private final PatternTopic chatMessagesPatternTopic;

  public void sendMessage(ChatMessage chatMessage) {
    redisTemplate.convertAndSend(chatMessagesPatternTopic.getTopic(), chatMessage);
  }

}
