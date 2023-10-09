package com.teamluffy.tripproject.friends.repository;

import com.teamluffy.tripproject.friends.domain.FriendType;
import com.teamluffy.tripproject.friends.domain.entity.Friends;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {

  List<Friends> findByUserId(int user_id);

  Optional<Friends> findByUserIdAndFriendUserNickname(int user_id, String nickname);

  // 친구 타입 변경
  @Modifying
  @Query("UPDATE Friends f SET f.type = :friendType WHERE f.id = :friendId")
  void changeTypeById(@Param("friendId") int friendId, @Param("friendType") FriendType friendType);
}
