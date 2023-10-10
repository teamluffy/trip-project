package com.teamluffy.tripproject.friends.controller;

import com.teamluffy.tripproject.friends.domain.FriendType;
import com.teamluffy.tripproject.friends.domain.entity.Friends;
import com.teamluffy.tripproject.friends.model.FriendInput;
import com.teamluffy.tripproject.friends.service.FriendsService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@Controller
public class FriendsController {

  private final FriendsService friendsService;


  // 친구창의 메인을 가져오는 GetMapping 입니다.
  @GetMapping("/")
  public String register(Model model) {

    // 친구 목록을 가져오는 서비스 메서드를 호출하여 친구 목록을 가져옵니다.
    List<Friends> friendList = friendsService.getAllFriends();
    System.out.println(friendList);


    model.addAttribute("friends", friendList);
    model.addAttribute("BestFriend", FriendType.BestFriend);
    model.addAttribute("Favorites", FriendType.Favorites);
    model.addAttribute("FirstMeet", FriendType.FirstMeet);

    return "/index";
  }


  // 친구를 추가할 때 사용하는 PostMapping 입니다.
  @PostMapping("/friends")
  public ResponseEntity<String> registerFriends(@RequestBody Map<String, String> requestBody) {
    String friendNickname = requestBody.get("friendNickname");

    if (friendNickname == null || friendNickname.isEmpty()) {
      // 닉네임이 유효하지 않을 경우 400 Bad Request를 반환할 수 있습니다.
      return ResponseEntity.badRequest().build();
    }

    FriendInput parameter = new FriendInput();
    parameter.setFriendUserNickname(friendNickname);

    boolean addFriend = friendsService.getFriend(parameter);

    System.out.println(addFriend);

    if (addFriend) {
      return ResponseEntity.ok().build(); // 204 No Content 응답을 반환합니다.
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error 응답을 반환합니다.
    }
  }


  // 친구를 삭제할 때 사용합니다.
  @DeleteMapping("/friends/delete/{friendId}")
  public ResponseEntity<String> deleteFriend(@PathVariable int friendId) {
    System.out.println(friendId);
    boolean deleted = friendsService.deleteFriendById(friendId);

    System.out.println(deleted);
    if (deleted) {
      return ResponseEntity.ok().build(); // 204 No Content 응답을 반환합니다.
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error 응답을 반환합니다.
    }
  }


  // 친구 타입을 변경할 때 사용하는 PostMapping 입니다.
  @PostMapping("/friends/change-type/{friendId}")
  public ResponseEntity<String> changeType(@PathVariable int friendId, @RequestBody Map<String, String> requestBody) {
    String friendType = requestBody.get("friendType");

    // String 값을 ENUM으로 변환
    FriendType friendTypeValue = FriendType.valueOf(friendType);

    System.out.println(friendType);
    boolean changed = friendsService.changeTypeById(friendId, friendTypeValue);
    if (changed) {
      return ResponseEntity.ok().build(); // 204 No Content 응답을 반환합니다.
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error 응답을 반환합니다.
    }
  }
}
