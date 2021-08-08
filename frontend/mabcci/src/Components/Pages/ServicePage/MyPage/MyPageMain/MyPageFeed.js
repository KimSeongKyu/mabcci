import React, { useState } from 'react';

const MyPageFeed = props => {
  const [myImage, setMyImage] = useState(['https://placeimg.com/500/500/arch']);
  const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  // const imageList = numbers.map((N, index) => (<div key={index}><img src=`https://placeimg.com/${N}/${N}/arch`></img></div>))
  numbers.map((N, index) => console.log(N));
  return (
    <div className="mypage-feed-container">
      <h5 className="mypage-feed-title">Feed</h5>
      <div className="mypage-feed-box">
        {numbers.map((N, index) => (
          <div className="mypage-feed">
            <div className="mypage-feed-overlay">
              <p>글제목입니닷</p>
            </div>
            <img src="https://placeimg.com/100/250/arch" alt="하이" />
          </div>
        ))}
      </div>
    </div>
  );
};

export default MyPageFeed;
