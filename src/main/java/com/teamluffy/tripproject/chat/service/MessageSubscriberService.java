package com.teamluffy.tripproject.chat.service;


import static com.teamluffy.tripproject.chat.domain.entity.ChatMessage.MessageType.ENTER;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamluffy.tripproject.chat.domain.entity.ChatMessage;
import com.teamluffy.tripproject.chat.domain.entity.ChatMessage.MessageType;
import com.teamluffy.tripproject.chat.domain.entity.ChatRoom;
import com.teamluffy.tripproject.chat.repository.ChatMessageRepository;
import com.teamluffy.tripproject.chat.repository.ChatRoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageSubscriberService implements MessageListener {

  private final ChatMessageRepository chatMessageRepository;
  private final ChatRoomRepository chatRoomRepository;
  private final SimpMessagingTemplate simpMessagingTemplate;
  private final String WS_CHAT_PATTERN = "/sub/chat/room/";


  @Override
  public void onMessage(Message message, byte[] pattern) {
    String channel = new String(message.getChannel());
    String payload = new String(message.getBody());

    if (channel.startsWith("/chat/room")) {
      handleChatMessage(payload);
    } else {
      throw new RuntimeException();
    }
  }

  private void handleChatMessage(String payload) {
    try {
      ChatMessage chatMessage = new ObjectMapper().readValue(payload, ChatMessage.class);
      if (!chatMessage.getMessage().equals(ENTER)) {
        chatMessageRepository.save(chatMessage);
      }
      simpMessagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getRoomId(),
          chatMessage);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public List<ChatMessage> getMessageList(String roomId, String userId) {
    ChatRoom chatRoom = chatRoomRepository.findById(roomId)
        .orElseThrow(() -> new RuntimeException());
    if(chatRoom.getUsers().contains(userId)){
      return chatMessageRepository.findByRoomId(roomId);
    }
    return null;
  }
}
