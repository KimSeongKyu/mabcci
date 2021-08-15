/* eslint-disable */

import React, { useState } from 'react';
import { baseUrl } from '../../../../../API/ApiUrl';
import { Link } from 'react-router-dom';

const MyPageFeed = props => {
  return (
    <div className="mypage-feed-container">
      <h5 className="mypage-feed-title">Feed</h5>
      <div className="mypage-feed-box">
        {props.myInfo.ootds.map((content, j) => (
          <div className="mypage-feed" key={j}>
            <Link to={`/OOTD/${props.myInfo.ootds[j].id}/${props.myInfo.nickname}`}>
              <div className="mypage-feed-overlay">
                <p>좋아요 개수 보여주기?</p>
              </div>
            </Link>
            <img src={baseUrl + props.myInfo.ootds[j].image} alt="하이" />
          </div>
        ))}
      </div>
    </div>
  );
};

export default MyPageFeed;
