import React from 'react';
import { AiOutlineMenu } from 'react-icons/ai';
import { FaLocationArrow } from 'react-icons/fa';
import { Link } from 'react-router-dom';

const ChatRoom = ({ setChatMenu, chatMenu }) => {
  const menuToggle = () => {
    setChatMenu(!chatMenu);
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
          <input type="text" />
          <div className="btn-chat">
            <button type="button">
              <FaLocationArrow />
            </button>
          </div>
        </div>
      </section>
    </div>
  );
};

export default ChatRoom;
