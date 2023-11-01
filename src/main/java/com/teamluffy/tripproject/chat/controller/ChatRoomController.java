package com.teamluffy.tripproject.chat.controller;

import com.teamluffy.tripproject.chat.domain.model.room.ChatRoomDto;
import com.teamluffy.tripproject.chat.domain.entity.ChatRoom;
import com.teamluffy.tripproject.chat.service.ChatRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

  private final ChatRoomService chatRoomService;
  private final TokenProvider provider;

  // 채팅 리스트 화면
  @GetMapping("/room")
  @PreAuthorize("hasRole('CUSTOMER')")
  public String callChatPages() {
    return "/chat/chatIndex";
  }

  @GetMapping("/login")
  public String callLoginPages() {
    return "/login/login";
  }

  //
  @GetMapping("/room/{roomId}")
  @ResponseBody
  @PreAuthorize("hasRole('CUSTOMER')")
  public ChatRoom getRoomInfo(@PathVariable String roomId) {
    log.info(roomId);
    log.info(String.valueOf(chatRoomService.findRoom(roomId)));
    return chatRoomService.findRoom(roomId);
  }

  // 모든 채팅방 목록 반환
  @GetMapping("/rooms")
  @ResponseBody
  @PreAuthorize("hasRole('CUSTOMER')")
  public List<ChatRoom> getAllRoomInfo(@RequestHeader("Cookie") String cookies) {
    String token = provider.extractAccessTokenFromCookies(cookies);
    UserVo userVo = provider.getUserVo(token);
    return chatRoomService.findAllRoom(String.valueOf(userVo.getId()));
  }

  // 채팅방 생성
  @PostMapping("/room")
  @ResponseBody
  @PreAuthorize("hasRole('CUSTOMER')")
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