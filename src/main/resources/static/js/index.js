// JavaScript를 사용하여 버튼 클릭 시 친구 목록을 열고 닫음
const showFriendsButton = document.getElementById('show-friends-button');
const closeFriendsButton = document.getElementById('close-friends-button');
const friendList = document.querySelector('.friend-list');
const addFriendButton = document.getElementById('add-friend-button');
const friendAddForm = document.querySelector('.friend-add-form');
const closeAddFriendButton = document.querySelector('.friend-add-form .down-button');

showFriendsButton.addEventListener('click', () => {
  friendList.classList.add('open');
  showFriendsButton.style.display = 'none'; // 버튼 숨김
});

closeFriendsButton.addEventListener('click', () => {
  friendList.classList.remove('open');
  setTimeout(() => {
    showFriendsButton.style.display = 'block'; // 버튼 표시 (0.5초 후)
  }, 300);
});

// 닫기 버튼 클릭 시 친구 추가 폼 닫기
closeAddFriendButton.addEventListener('click', (e) => {
  e.preventDefault(); // 기본 동작을 막음
  friendAddForm.style.display = 'none';
});

// 친구 추가 버튼 클릭 시 친구 추가 폼 표시
addFriendButton.addEventListener('click', () => {
  friendAddForm.style.display = 'block';
});

// Type버튼에서 data-id의 값을 가져와서 friendId에 담아서 changeType의 함수로 보냅니다.
document.querySelectorAll('.change-type-button').forEach(button => {
  button.addEventListener('click', () => {
    const friendId = button.getAttribute('data-id');
    changeType(friendId);
  });
});



// 친구의 Type을 변경하는 부분입니다.
function changeType(friendId) {
  const modal = document.querySelector('.type-change-modal');
  modal.style.display = 'block';

  // 모달 폼 안에 friendId를 설정합니다.
  const modalForm = modal.querySelector('form');

  modalForm.addEventListener('submit', function (event) {
    event.preventDefault();
    const friendType = modalForm.querySelector('input[name="friendType"]:checked').value;

    // 서버로 타입 변경 요청 보내기
    fetch(`/friends/change-type/${friendId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ friendType }),
    })
    .then((response) => {
      if (response.ok) {
        alert('친구 타입을 변경했습니다.');
        modal.style.display = 'none';
        // 변경 성공 후 홈 페이지로 이동
        window.location.href = '/'; // 또는 적절한 경로로 변경 가능
      } else {
        console.error('친구 타입 변경 실패');
        // 변경 실패 후 홈 페이지로 이동
        window.location.href = '/'; // 또는 적절한 경로로 변경 가능
      }
    })
    .catch((error) => {
      console.error('오류 발생:', error);
    });
  });
}





// 타입버튼 창 닫기 버튼 클릭 시
document.querySelector('.type-change-modal .close-modal-button').addEventListener('click', () => {
  const modal = document.querySelector('.type-change-modal');
  modal.style.display = 'none';
});


// 삭제 시
function deleteFriend(friendId) {
  fetch(`/friends/delete/${friendId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
    },
  })
  .then((response) => {
    if (response.ok) {
      // 성공적으로 삭제되었을 때 할 일
      alert('친구 정보를 삭제했습니다.'); // 삭제 완료 창을 띄움
      console.log('친구 정보를 삭제했습니다.');
      // 친구 삭제 후 홈 페이지로 이동
      window.location.href = '/'; // 또는 적절한 경로로 변경 가능
    } else {
      // 삭제 실패 시 할 일
      console.error('친구 정보 삭제 실패');
      alert('친구 정보를 삭제 실패했습니다. 닉네임을 확인해 주세요.');
    }
  })
  .catch((error) => {
    console.error('오류 발생:', error);
    alert('친구 정보를 삭제 실패했습니다. 닉네임을 확인해 주세요.');
  });
}




// 친구 추가 폼 제출 시
document.getElementById('friend-add-form').addEventListener('submit', function(event) {
  event.preventDefault(); // 기본 제출 동작을 막음
  const friendNickname = document.getElementById('friend-nickname').value;

  // 서버로 친구 추가 요청 보내기
  fetch('/friends', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ friendNickname }),
  })
  .then((response) => {
    if (response.ok) {
      // 성공적으로 추가되었을 때 할 일
      alert('친구를 추가했습니다.'); // 친구 추가 성공 알람을 표시
      window.location.href = '/'; // 다시 목록 페이지로 이동
    } else {
      // 추가 실패 시 할 일
      console.error('친구 추가 실패');
      return response.json();
    }
  })
  .then((errorResponse) => {
    // 오류 메시지를 포함한 응답을 받은 경우
    if (errorResponse) {
      alert('친구를 추가하지 못했습니다: ' + errorResponse.message);
    }
  })
  .catch((error) => {
    console.error('오류 발생:', error);
  });
});




// 카테고리 버튼 선택
const categoryButtons = document.querySelectorAll('.category-button');
// 카테고리 버튼 클릭 이벤트 처리
categoryButtons.forEach(button => {
  button.addEventListener('click', () => {
    const category = button.getAttribute('data-category');
    const friendList = document.querySelector(`.friend-list[data-category="${category}"]`);

    if (friendList) {
      if (friendList.style.display === 'none') {
        friendList.style.display = 'block';
      } else {
        friendList.style.display = 'none';
      }
    }
  });
});