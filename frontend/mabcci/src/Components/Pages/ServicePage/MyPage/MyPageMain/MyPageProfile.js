/* eslint-disable */

import React from 'react';
import './MyPage.css';
import 박서준 from './images/박서준.jfif';
import 직사각형_남자 from './images/직사각형_남자.png'
import { useHistory } from 'react-router';
import { useState } from 'react';

const MyPageProfile = props => {
  const history = useHistory

  const [profile, setProfile] = useState(false)

  const clickProfile = () => {setProfile(!profile)}
  
  const goToSetting = () => {
    history.push()
  }

   const clickFollow = (e) => {
     props.setFollowBox(e.target.name)
   }

  return (
    <>
      <div className="mypage-profile-box">
        <div
          className={
            profile === true
              ? 'mypage-profile-inner mypage-profile-inner-active'
              : 'mypage-profile-inner'
          }
        >
          <div className="mypage-profile-bodyinfo" onClick={clickProfile}>
            <img src={직사각형_남자}></img>
            <div className="mypage-profile-bodysize">
              <p>185cm</p>
              <p>75kg</p>
              <p>270mm</p>
            </div>
          </div>
          <img src={박서준} alt="" onClick={clickProfile} />
        </div>

        <div className="mypage-info-box">
          <div id="mypage-web-nickname">
            {/* <h3>{props.myInfo.nickname}</h3> */}
            <h3>박서준</h3>
            <button type="submit" onClick={goToSetting}>
              프로필편집
            </button>
          </div>
          <div id="mypage-mobile-nickname">
            {/* <h5>{props.myInfo.nickname}</h5> */}
            <h5>박서준</h5>
          </div>
          <div>
            <button type="submit" onClick={clickFollow} name='팔로워'>팔로워 {}명</button>
            <button type="submit"onClick={clickFollow} name="팔로잉">팔로잉 {}명</button>
          </div>
          <div id="mypage-mobile-category">
            <h5>#아메카지</h5>
            <h5>#스트릿</h5>
            <h5>#포멀</h5>
          </div>
        </div>
        <div className="mypage-blank" />
      </div>
      <div className="mypage-introduce-box">
        <p>Introduce</p>
        {/* 
        {props.myInfo.introduce === [] ? (
          <div>소개글이 비어있어요!</div>
        ) : (
          <div>안녕하세요 아라찌입니다.</div>
        )} */}
        <div>안녕하세요 서준팍입니다.</div>
      </div>
    </>
  );
};

export default MyPageProfile;
