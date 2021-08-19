import React, { useEffect, useRef, useState } from 'react';
import { AiOutlineMenu } from 'react-icons/ai';
import { FaLocationArrow } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import { sendMessageApi } from '../../../../../API/ChatAPI/ChatApi';
import { baseUrl } from '../../../../../API/ApiUrl';

const ChatRoom = ({ setChatMenu, chatMenu, message, setMessage, chatInfo }) => {
  const client = useRef({});
  const [contents, setContents] = useState([]);

  const sendMessage = () => {
    const messageData = {
      type: 'TALK',
      roomId: chatInfo.roomId,
      sender: chatInfo.sender,
      message,
    };
    console.log('send data', messageData);
    client.current.send(
      `${baseUrl}/pub/chat/message`,
      JSON.stringify(messageData),
      {
        Authorization: localStorage.getItem('accessToken'),
      },
    );
    setMessage('');
  };

  const recvMessage = recv => {
    console.log(recv.userCount);
    setContents(prev => [...prev, recv]);
    console.log(contents);
  };

  const connect = () => {
    if (!chatInfo.roomId) return;
    const sock = new SockJS(`${baseUrl}/ws-stomp`);
    client.current = Stomp.over(sock);

    client.current.connect(
      { token: localStorage.getItem('accessToken') },
      frame => {
        client.current.subscribe(
          `${baseUrl}/sub/chat/room/${chatInfo.roomId}`,
          function (messages) {
            console.log('구독');
            const recv = JSON.parse(messages.body);
            recvMessage(recv);
          },
        );
      },
    );
  };

  useEffect(() => {
    connect();
    console.log('contents', contents);
  }, [chatInfo.roomId]);

  const menuToggle = () => {
    setChatMenu(!chatMenu);
  };

  const typeMessage = e => {
    setMessage(e.target.value);
  };

  return (
    <div className="chat-room">
      <header>
        <button type="button" className="chat-menu" onClick={menuToggle}>
          <AiOutlineMenu />
        </button>
        <p>채팅창</p>
      </header>
      <section>
        <div className="chat-room-log">
          <div className="you-box">
            <div>
              <img
                className="profile"
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5e9ZV_LPO-_MZ8OX1FA3F-dzDUqkuKCtv8A&usqp=CAU"
                alt=""
              />
            </div>
            <div className="you">
              <p>
                Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quasi
                perferendis voluptatum inventore ullam aut hic doloribus
                nesciunt deleniti quo nobis ipsam et vitae mollitia atque
                corporis veritatis, a maxime dolores.
              </p>
            </div>
          </div>
          <div className="you-box">
            <img
              className="profile invisible"
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5e9ZV_LPO-_MZ8OX1FA3F-dzDUqkuKCtv8A&usqp=CAU"
              alt=""
            />
            <div className="you">
              <p>Lorem ipsum dolois, a maxime dolores.</p>
            </div>
          </div>
          <div className="me-box">
            <div className="me">
              <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                Molestiae, sapiente quidem. Eum officiis et sequi? Enim, ab rem
                omnis possimus ut tempora at harum eum adipisci consequuntur ad
                sint illo.
              </p>
              <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                Molestiae, sapiente quidem. Eum officiis et sequi? Enim, ab rem
                omnis possimus ut tempora at harum eum adipisci consequuntur ad
                sint illo.
              </p>
              <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit.
                Molestiae, sapiente quidem. Eum officiis et sequi? Enim, ab rem
                omnis possimus ut tempora at harum eum adipisci consequuntur ad
                sint illo.
              </p>
            </div>
          </div>
        </div>
        <div className="chat-input">
          <Link to="/makesuggestion" className="link-suggest">
            <button type="button" className="btn-suggest">
              <p>최종제안서 보내기</p>
            </button>
          </Link>
          <input type="text" value={message} onChange={typeMessage} />
          <div className="btn-chat">
            <button type="button" onClick={sendMessage}>
              <FaLocationArrow />
            </button>
          </div>
        </div>
      </section>
    </div>
  );
};

export default ChatRoom;
