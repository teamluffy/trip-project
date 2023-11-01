// var chatrooms = '';
// var hisMessages = '';
//
// function getChatRoomList(data) {
//   return `<a href="#" class="${data.roomId}" id = "room-id">
//     <div class="row">
//       <div class="col-3"><img class="mr-3 rounded-circle" src="./img/profile.png"
//           alt="Generic placeholder image"></div>
//       <div class="col-9">${data.roomName}
//         <br /><small>Hi, I am using Whats app</small>
//       </div>
//     </div>
//   </a>`
// }
//
// function getChatBox() {
//   return '        <div id="user_chat_data" class="user_chat_data">\n'
//       + '          <div class="profile_name"><a href="#"><i class="fa fa fa-arrow-left" aria-hidden="true"></i></a>\n'
//       + '            &nbsp;&nbsp;&nbsp;&nbsp;<img src="./img/profile.png" class="mr-3 rounded-circle"> &nbsp;&nbsp;\n'
//       + '            Sankar Mahadevan\n'
//       + '          </div>\n'
//       + '\n'
//       + '          <div class="container-fluid chat_section" id="chat-box">\n'
//
//       + '          </div>\n'
//       + '          <div class="type_msg">\n'
//       + '            <div class="input_msg_write">\n'
//       + '              <input id="chat-outgoing-msg" type="text" class="write_msg" placeholder="Type a message" />\n'
//       + '              <button id="chat-outgoing-button" class="msg_send_btn" type="button"><i class="fa fa-paper-plane"\n'
//       + '                  aria-hidden="true"></i></button>\n'
//       + '            </div>\n'
//       + '          </div>\n'
//       + '        </div>'
// }
//
// function addList(datas) {
//   let chatListBox = document.querySelector("#chat-list-box")
//   for (const chatroom of datas) {
//     console.log(chatroom)
//     let user = document.createElement("div")
//     user.className = "user";
//     user.innerHTML = getChatRoomList(chatroom);
//     chatListBox.append(user);
//   }
// }
//
// function findAllRoom() {
//   axios.get('/chat/rooms').then(response => {
//     chatrooms = response.data;
//
//     addList(this.chatrooms)
//   }).catch(error => {
//
//   })
// }
//
// function callMessages(roomId) {
//   axios.get('/chat/messages/' + roomId).then(response => {
//     hisMessages = response.data;
//     console.log(hisMessages)
//     localStorage.setItem("hisMessages", hisMessages)
//     addList(this.chatrooms)
//   }).catch(error => {
//
//   })
//   let delElement = document.getElementById("out-chat-box")
//   if (delElement !== null) {
//     delElement.remove();
//   }
//   let firstBox = document.querySelector('#first-box');
//   let outChatBox = document.createElement("div");
//   outChatBox.id = "out-chat-box"
//   outChatBox.innerHTML = getChatBox();
//   firstBox.append(outChatBox);
// }
//
// findAllRoom();
//
//
//
//
//
