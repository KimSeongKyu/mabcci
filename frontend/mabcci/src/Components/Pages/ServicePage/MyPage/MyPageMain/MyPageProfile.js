/* eslint-disable */

import React from 'react';
import './MyPage.css';
import 아라찌 from './images/다운로드.jfif';

const MyPageProfile = props => {
  return (
    <>
      <div className="mypage-profile-box">
        <div>
          <img src={아라찌} alt="" />
        </div>
        <div className="mypage-info-box">
          <div id="mypage-web-nickname">
            <h3>{props.myInfo.nickname}</h3>
            <button type="submit">프로필편집</button>
          </div>
          <div id="mypage-mobile-nickname">
            <h5>{props.myInfo.nickname}</h5>
          </div>
          <div>
            <button type="submit">팔로워 {}명</button>
            <button type="submit">팔로잉 {}명</button>
          </div>
          <div id="mypage-mobile-category">
            {/* <h5>#{myInfo.category[0]}</h5>
            <h5>#{myInfo.category[0]}</h5>
            <h5>#{myInfo.category[0]}</h5> */}
          </div>
        </div>
        <div className="mypage-blank" />
      </div>
      <div className="mypage-introduce-box">
        <p>Introduce</p>
        <div>{props.myInfo.introduce}</div>
      </div>
    </>
  );
};

export default MyPageProfile;
