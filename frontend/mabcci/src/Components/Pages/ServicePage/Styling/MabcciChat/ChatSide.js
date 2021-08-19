import React, { useEffect, useState } from 'react';
import { chatListApi } from '../../../../../API/ChatAPI/ChatApi';
import getUserInfo from '../../../../Common/getUserInfo';
import { baseUrl } from '../../../../../API/ApiUrl';
import 기본프로필 from '../../../../../Asset/Images/기본프로필.jpg';

const ChatSide = ({ chatMenu, chatInfo, setChatInfo }) => {
  const [chatList, setChatList] = useState([]);

  useEffect(async () => {
    const { nickname } = getUserInfo();
    const response = await chatListApi(nickname);
    setChatList(response.data);
  }, []);

  const settingRoom = roomId => {
    setChatInfo({ ...chatInfo, roomId });
  };

  const showChatList = () => {
    return chatList.map(({ roomId, nickname, picture }) => {
      return (
        <button
          type="button"
          className="chat-side-btn"
          key={roomId}
          onClick={() => settingRoom(roomId)}
        >
          <div className="chat-side-item">
            <img src={picture ? baseUrl + picture : 기본프로필} alt="" />
            <p>{nickname}</p>
          </div>
        </button>
      );
    });
  };

  return (
    <div className={`chat-side ${chatMenu ? 'active' : ''}`}>
      <header>
        <p>채팅 목록</p>
      </header>
      <section>{showChatList()}</section>
    </div>
  );
};

export default ChatSide;
