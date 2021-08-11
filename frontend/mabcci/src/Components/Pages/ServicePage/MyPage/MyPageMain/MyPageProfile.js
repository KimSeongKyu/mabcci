/* eslint-disable */

import React from 'react';
import './MyPage.css';
import 박서준 from './images/박서준.jfif';
import 직사각형_남자 from './images/직사각형_남자.png'
import { useHistory } from 'react-router';
import { useState } from 'react';
import {AiOutlineSetting} from "react-icons/ai"
import {CgFileDocument} from "react-icons/cg"
import {AiOutlineMessage} from 'react-icons/ai'

const MyPageProfile = props => {

  const history = useHistory

  const [profile, setProfile] = useState(false)

  const clickProfile = () => {setProfile(!profile)}
  
  const openSetting = () => {
    props.setMyPageUpdate('setting');
  };

   const clickFollow = (e) => {
     props.setFollowBox(e.target.name)
   }

   const clickChatList = () => {
     props.setChatBox(true)
   }

   const clickProposalList = () => {
     props.setProposalBox(true)
   }

  console.log(props.myInfo.categories, props.myInfo.categories, 'dd?')

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
            <h3>{props.myInfo.nickname}</h3>
            <button type="submit">
              <CgFileDocument onClick={clickProposalList} />
            </button>
            <button type="submit">
              <AiOutlineMessage onClick={clickChatList} />
            </button>
            <button type="submit" onClick={openSetting}>
              <AiOutlineSetting />
            </button>
          </div>
          <div id="mypage-mobile-nickname">
            <h5>{props.myInfo.nickname}</h5>
          </div>
          <div>
            <button type="submit" onClick={clickFollow} name="팔로워">
              팔로워 {props.myInfo.follower}명
            </button>
            <button type="submit" onClick={clickFollow} name="팔로잉">
              팔로잉 {props.myInfo.following}명
            </button>
          </div>
          {/* <div id="mypage-mobile-category">
            {categories.map((category, idx) => (
              <h5 key={idx}>#{category}</h5>
            ))}
          </div> */}
        </div>
        <div className="mypage-blank" />
      </div>
      <div className="mypage-introduce-box">
        <p>Introduce</p>

        {props.myInfo.description === null ? (
          <div>소개글이 비어있어요!</div>
        ) : (
          <div>{props.myInfo.description}</div>
        )}
      </div>
    </>
  );
};

export default MyPageProfile;
