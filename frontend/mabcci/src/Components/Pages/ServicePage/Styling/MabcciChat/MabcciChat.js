import React, { useState } from 'react';
import ChatRoom from './ChatRoom';
import ChatSide from './ChatSide';
import './MabcciChat.css';

const MabcciChat = () => {
  const [chatMenu, setChatMenu] = useState(false);
  return (
    <div className="container chat">
      <ChatSide chatMenu={chatMenu} />
      <ChatRoom setChatMenu={setChatMenu} chatMenu={chatMenu} />
    </div>
  );
};

export default MabcciChat;
