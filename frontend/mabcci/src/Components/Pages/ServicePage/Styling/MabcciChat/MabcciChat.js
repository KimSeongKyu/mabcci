import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import NavCategory from '../../../../../Redux/Actions/NavAction';
import ChatRoom from './ChatRoom';
import ChatSide from './ChatSide';
import './MabcciChat.css';
import getUserInfo from '../../../../Common/getUserInfo';

const MabcciChat = () => {
  const [message, setMessage] = useState('');
  const [chatInfo, setChatInfo] = useState({
    roomId: '',
    sender: getUserInfo().nickname,
  });
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(NavCategory('chat'));
  }, []);
  const [chatMenu, setChatMenu] = useState(false);
  return (
    <div className="container chat">
      <ChatSide
        chatMenu={chatMenu}
        chatInfo={chatInfo}
        setChatInfo={setChatInfo}
      />
      <ChatRoom
        setChatMenu={setChatMenu}
        chatMenu={chatMenu}
        chatInfo={chatInfo}
        message={message}
        setMessage={setMessage}
      />
    </div>
  );
};

export default MabcciChat;
