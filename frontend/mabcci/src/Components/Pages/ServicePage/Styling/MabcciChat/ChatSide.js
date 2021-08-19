import React, { useEffect, useState } from 'react';
import { chatListApi } from '../../../../../API/ChatAPI/ChatApi';

const ChatSide = ({ chatMenu }) => {
  const [chatList, setChatList] = useState([]);

  useEffect(async () => {
    console.log('api통신');
    const response = await chatListApi();
    setChatList(response.data.chatList);
    console.log(chatList);
  }, []);

  const showChatList = () => {
    return chatList.map(({ nickname, picture }) => {
      return (
        <div key={nickname} className="chat-side-item">
          <img src={picture} alt="" />
          <p>{nickname}</p>
        </div>
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
