/* eslint-disable */

import React, { useState } from 'react';
import { baseUrl } from '../../../../../API/ApiUrl';
import { Link } from 'react-router-dom';

const MyPageFeed = props => {
  return (
    <div className="mypage-feed-container">
      <h5 className="mypage-feed-title">Feed</h5>
      {props.myInfo.ootds.length > 0 ? (
        <div className="mypage-feed-box">
          {props.myInfo.ootds.map((content, j) => (
            <div className="mypage-feed" key={j}>
              <Link
                to={`/OOTD/${props.myInfo.ootds[j].id}/${props.myInfo.nickname}`}
              >
                <div className="mypage-feed-overlay">
                </div>
              </Link>
              <img src={baseUrl + props.myInfo.ootds[j].image} alt="하이" />
            </div>
          ))}
        </div>
      ) : (
        <div style={{ height: '130px' }}></div>
      )}
    </div>
  );
};

export default MyPageFeed;
