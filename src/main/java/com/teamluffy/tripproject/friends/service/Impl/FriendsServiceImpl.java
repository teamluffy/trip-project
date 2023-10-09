package com.teamluffy.tripproject.friends.service.Impl;

import com.teamluffy.tripproject.exception.CustomException;
import com.teamluffy.tripproject.friends.domain.entity.Friends;
import com.teamluffy.tripproject.friends.exception.FriendsErrorCode;
import com.teamluffy.tripproject.friends.model.FriendInput;
import com.teamluffy.tripproject.friends.repository.FriendsRepository;
import com.teamluffy.tripproject.friends.service.FriendsService;
import com.teamluffy.tripproject.friends.domain.FriendType;
import com.teamluffy.tripproject.signup.domain.entity.User;
import com.teamluffy.tripproject.signup.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FriendsServiceImpl implements FriendsService {

  private final FriendsRepository friendsRepository;
  private final UserRepository userRepository;


  /***
   * 친구 닉네임을 가져와서 저장하는 부분입니다.
   * 친구 닉네임을 parameter로 받은 다음, user중에서 있는지 검사합니다.
   * 존재 하지 않으면 false, 존재 한다면 저장을 진행합니다.
   */
  @Override
  public boolean getFriend(FriendInput parameter) {
    System.out.println(parameter);
    System.out.println(parameter.getFriendUserNickname());

    Optional<User> optionalMember = userRepository.findByNickname(parameter.getFriendUserNickname());
    System.out.println(optionalMember);
    if (!optionalMember.isPresent()) {
      System.out.println("해당 닉네임의 유저가 존재하지 않습니다.");
      throw new CustomException(FriendsErrorCode.NOT_FOUND_USER);
    }

    // 나중에는 usedDetail.getUserId로 정보를 가져올 것입니다.현재는 임의의 userId = 6
    int userId = 6;

    Optional<Friends> existNickname = friendsRepository.findByUserIdAndFriendUserNickname(userId, parameter.getFriendUserNickname());

    if (existNickname.isPresent()) {
      System.out.println("해당 닉네임의 유저가 이미 친구추가 되어있습니다.");
      throw new CustomException(FriendsErrorCode.ALREADY_ADD_FRIEND);
    }


    Friends friends = Friends.builder()
        .userId(userId)
        .type(FriendType.FirstMeet)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .friendUserNickname(parameter.getFriendUserNickname())
        .build();
    friendsRepository.save(friends);

    return true;
  }

  // 친구 목록 리스트를 불러오기 위한 부분입니다.
  @Override
  public List<Friends> getAllFriends() {

    // 나중에는 usedDetail.getUserId로 정보를 가져올 것입니다.현재는 임의의 userId
    int userId = 6;

    List<Friends> friendsList = friendsRepository.findByUserId(userId);

    return friendsList;
  }


  //친구 삭제를 위한 부분입니다.
  @Override
  public boolean deleteFriendById(int id) {
    try {
      friendsRepository.deleteById(id);
      System.out.println("삭제완료");
      return true;
    } catch (Exception e) {
      System.err.println("삭제 실패");
      return false;
    }
  }

  //친구 타입 변경을 위한 부분입니다.
  @Override
  @Transactional
  public boolean changeTypeById(int friendId, FriendType friendType) {
    try {
      Friends friend = friendsRepository.findById(friendId).orElse(null);
      if (friend != null) {
        friend.setType(friendType); // ENUM 값 설정
        friendsRepository.save(friend); // 엔티티 업데이트
        System.out.println("타입 변경 완료");
        return true;
      } else {
        System.out.println("친구를 찾을 수 없음");
        throw new CustomException(FriendsErrorCode.NOT_FOUND_ID);
      }
    } catch (DataAccessException e) {
      System.err.println("타입 변경 실패: " + e.getMessage());
      return false;
    }
  }
}
