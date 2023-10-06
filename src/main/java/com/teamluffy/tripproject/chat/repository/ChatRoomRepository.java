package com.teamluffy.tripproject.chat.repository;

import com.teamluffy.tripproject.chat.domain.entity.ChatRoom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

  @Query("{'users': ?0}")
  List<ChatRoom> findByUsersContains(String userId);

  @Override
  Optional<ChatRoom> findById(String roomId);
}

