import React from 'react';

const ChatRoom = () => {
  return (
    <div className="chat-room">
      <header>
        <p>채팅창</p>
      </header>
      <section>
        <div className="chat-room-log">
          <div className="you-box">
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
            </div>
          </div>
        </div>
        <div className="chat-input">
          <input type="text" />
        </div>
      </section>
    </div>
  );
};

export default ChatRoom;
