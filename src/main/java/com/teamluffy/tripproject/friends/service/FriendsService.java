package com.teamluffy.tripproject.friends.service;


import com.teamluffy.tripproject.friends.domain.FriendType;
import com.teamluffy.tripproject.friends.domain.entity.Friends;
import com.teamluffy.tripproject.friends.model.FriendInput;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface FriendsService {

  boolean getFriend(FriendInput parameter);

  List<Friends> getAllFriends();

  boolean deleteFriendById(int id);

  boolean changeTypeById(int friendId, FriendType friendType);
}
