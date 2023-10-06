package com.teamluffy.tripproject.chat.controller;

import com.teamluffy.tripproject.chat.domain.ChatRoomDto;
import com.teamluffy.tripproject.chat.domain.entity.ChatRoom;
import com.teamluffy.tripproject.chat.service.ChatRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  @GetMapping("/room")
  public String callChatPages() {
    return "/chat/chatIndex";
  }

  // 특정 채팅방 정보
  @GetMapping("/room/{roomId}")
  @ResponseBody
  public ChatRoom getRoomInfo(@PathVariable String roomId) {
    return chatRoomService.findRoom(roomId);
  }

  // 내가 포함 된 모든 채팅방 목록
  @GetMapping("/rooms")
  @ResponseBody
  public List<ChatRoom> getAllRoomInfo(@RequestHeader(name = "Authorization") String token) {
    // TokenProvider를 통해 유저 정보 획득
    return chatRoomService.findAllRoom(String.valueOf(1)); // 유저아이디 임시로 '1' 입력
  }

  // 채팅방 생성
  @PostMapping("/room")
  @ResponseBody
  public ChatRoom createRoom(@RequestBody ChatRoomDto chatRoomDto) {

    return chatRoomService.createRoom(chatRoomDto);
  }

  @GetMapping("/room/join")
  @ResponseBody
  public String joinRoom(@RequestParam("userId") String userId,
      @RequestParam("roomId") String roomId) {
    chatRoomService.userJoinRoom(userId, roomId);
    return "가입되었습니다";
  }
}
