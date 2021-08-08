/* eslint-disable */

import React from 'react';
import './MyPage.css';
import 아라찌 from './images/다운로드.jfif';
import 직사각형_남자 from './images/직사각형_남자.png'
import { useHistory } from 'react-router';

const MyPageProfile = props => {
  const history = useHistory
  
  const goToSetting = () => {
    history.push()
  }

  return (
    <>
      <div className="mypage-profile-box">
        <div className="mypage-profile-inner">
          <div className="mypage-profile-bodyinfo">
            <img src={직사각형_남자}></img>
            <div className="mypage-profile-bodysize">
              <p>185cm</p>
              <p>75kg</p>
              <p>270mm</p>
            </div>
          </div>
          <img src={아라찌} alt="" />
        </div>

        <div className="mypage-info-box">
          <div id="mypage-web-nickname">
            <h3>{props.myInfo.nickname}</h3>
            <button type="submit" onClick={goToSetting}>프로필편집</button>
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
        {props.myInfo.introduce === [] ? (
          <div>소개글이 비어있어요!</div>
        ) : (
          <div>안녕하세요 아라찌입니다.</div>
        )}
      </div>
    </>
  );
};

export default MyPageProfile;
