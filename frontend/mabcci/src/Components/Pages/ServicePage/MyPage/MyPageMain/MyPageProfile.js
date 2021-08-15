/* eslint-disable */

import React from 'react';
import './MyPage.css';
import { useState } from 'react';
import {AiOutlineSetting} from "react-icons/ai"
import {CgFileDocument} from "react-icons/cg"
import {AiOutlineMessage} from 'react-icons/ai'
import 기본프로필 from '../../../../../Asset/Images/기본프로필.jpg'
import { baseUrl } from '../../../../../API/ApiUrl';

const MyPageProfile = props => {
  const [profile, setProfile] = useState(false)

  const clickProfile = () => {setProfile(!profile)}
  
  const openSetting = () => {
    props.setMyPageUpdate('setting');
    props.setMobileMenu(true);
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

  return (
    <>
      <div className="mypage-profile-box">
        <div
          onClick={clickProfile}
          className={
            profile === true
              ? 'mypage-profile-inner mypage-profile-inner-active'
              : 'mypage-profile-inner'
          }
        >
          <div className="mypage-profile-bodyinfo">
            {props.myInfo.bodyType !== null ? (
              <img src={props.myInfo.gender + '_' + props.myInfo.bodyType} alt="No image"></img>
            ) : (
              <div className="mypage-profile-bodysize-noimage">No Type</div>
            )}
            <div className="mypage-profile-bodysize">
              {props.myInfo.height !== 0 ? (
                <p>{props.myInfo.height}cm</p>
              ) : (
                <p className="mypage-profile-bodysize-secret">secret</p>
              )}
              {props.myInfo.weight !== 0 ? (
                <p>{props.myInfo.height}cm</p>
              ) : (
                <p className="mypage-profile-bodysize-secret">secret</p>
              )}
              {props.myInfo.footSize !== 0 ? (
                <p>{props.myInfo.footSize}mm</p>
              ) : (
                <p className="mypage-profile-bodysize-secret">secret</p>
              )}
            </div>
          </div>
          {props.myInfo.picture !== null ? (
            <img src={props.myInfo.picture} alt="No image"></img>
          ) : (
            <img src={기본프로필} alt="" onClick={clickProfile} />
          )}
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
          <div id="mypage-mobile-category">
            {props.myInfo.categories.map((category, idx) => (
              <h5 key={idx}>#{category}</h5>
            ))}
          </div>
        </div>
        <div className="mypage-blank" />
      </div>
      <div className="mypage-introduce-box">
        <p>Introduce</p>

        {props.myInfo.description == 'null' ||
        props.myInfo.description == '' ? (
          <div className="mypage-introduce-box-null">No information❕</div>
        ) : (
          <div>{props.myInfo.description}</div>
        )}
      </div>
    </>
  );
};

export default MyPageProfile;
