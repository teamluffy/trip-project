package com.teamluffy.tripproject.friends.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FriendsErrorCode{

  NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 닉네임의 유저가 존재하지 않습니다."),

  NOT_FOUNT_CATEGORY(HttpStatus.NOT_FOUND, "해당 카테고리가 존재하지 않습니다."),

  ALREADY_ADD_FRIEND(HttpStatus.NOT_FOUND, "해당 유저는 이미 친구추가 되어있습니다."),

  NOT_FOUND_ID(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다.");

  private final HttpStatus httpStatus;
  private final String detail;
}
