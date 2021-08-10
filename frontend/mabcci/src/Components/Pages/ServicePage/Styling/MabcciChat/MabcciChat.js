import React from 'react';
import ChatRoom from './ChatRoom';
import ChatSide from './ChatSide';
import './MabcciChat.css';

const MabcciChat = () => {
  return (
    <div className="container chat">
      <ChatSide />
      <ChatRoom />
    </div>
  );
};

export default MabcciChat;
