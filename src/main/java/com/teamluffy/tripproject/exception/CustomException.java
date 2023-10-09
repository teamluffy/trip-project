package com.teamluffy.tripproject.exception;

import com.teamluffy.tripproject.friends.exception.FriendsErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    private final FriendsErrorCode friendsErrorCode;

    public CustomException(ErrorCode errorCode){
        super(errorCode.getDetail());
        this.errorCode = errorCode;
        this.friendsErrorCode = null;
    }

    public CustomException(FriendsErrorCode friendsErrorCode) {
        super(friendsErrorCode.getDetail());
        this.friendsErrorCode = friendsErrorCode;
        this.errorCode = null;
    }

}
