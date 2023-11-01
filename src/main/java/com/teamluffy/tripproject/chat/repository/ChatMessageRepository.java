package com.teamluffy.tripproject.chat.repository;

import com.teamluffy.tripproject.chat.domain.entity.ChatMessage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

  @Query("{'sender':?0,'roomId':?1}")
  List<ChatMessage> mFindBySender(String sender, String roomId);

  @Query("{ 'message.roomId' : ?0 }")
  List<ChatMessage> findByRoomIdInMessage(String roomId);

}