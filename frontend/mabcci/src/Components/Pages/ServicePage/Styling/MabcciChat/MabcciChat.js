import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import NavCategory from '../../../../../Redux/Actions/NavAction';
import ChatRoom from './ChatRoom';
import ChatSide from './ChatSide';
import './MabcciChat.css';

const MabcciChat = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(NavCategory('chat'));
  }, []);
  const [chatMenu, setChatMenu] = useState(false);
  return (
    <div className="container chat">
      <ChatSide chatMenu={chatMenu} />
      <ChatRoom setChatMenu={setChatMenu} chatMenu={chatMenu} />
    </div>
  );
};

export default MabcciChat;
