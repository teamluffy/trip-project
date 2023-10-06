package com.teamluffy.tripproject.chat.domain.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@Document(collection = "chatMessages")
@AllArgsConstructor
public class ChatMessage {

  public enum MessageType {
    ENTER, TALK, LEAVE
  }

  @Id
  private String id;
  private MessageType type;
  //채팅방 ID
  private String message;
  private String sender; //발신
  private String roomId; //수신

  private LocalDateTime createdAt;
}