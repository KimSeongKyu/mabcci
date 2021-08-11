/* eslint-disable */
import React, { useState } from 'react';
import { baseUrl } from '../../../../../API/ApiUrl';

const MyPageFeed = props => {
  console.log(props.myInfo.ootds, typeof props.myInfo.ootds, 'feed');
  console.log(props.myInfo.ootds.length)
  return (
    <div className="mypage-feed-container">
      <h5 className="mypage-feed-title">Feed</h5>
      <div className="mypage-feed-box">
        {props.myInfo.ootds.map((content, i) => (
          <div className="mypage-feed" key={content}>
            <div className="mypage-feed-overlay">
              <p>좋아요 개수 보여주기?</p>
            </div>
            <img src={baseUrl + props.myInfo.ootds[i].image} alt="하이" />
          </div>
        ))}
      </div>
    </div>
  );
};

export default MyPageFeed;
