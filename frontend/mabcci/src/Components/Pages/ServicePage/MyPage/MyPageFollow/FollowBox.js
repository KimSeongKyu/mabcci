/* eslint-disable */

import React, { useState } from 'react';
import './Follow.css';
import 박서준 from '../MyPageMain/images/박서준.jfif';

const FollowBox = props => {

  const exitFollowBox = () => {
    props.setFollowBox('none');
  }

  return (
    <div>
      {props.followBox === '팔로워' || props.followBox === '팔로잉' ? (
        <div className="mypage-modal-container" />
      ) : null}

      {props.followBox === '팔로워' ? (
        <div className="mypage-modal-box">
          <div className="mypage-modal-box-header">
            <h5>팔로워</h5>
            <button
              type="submit"
              className="mypage-modal-box-btn"
              onClick={exitFollowBox}
            >
              X
            </button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">삭제</button>
          </div>
        </div>
      ) : null}

      {props.followBox === '팔로잉' ? (
        <div className="mypage-modal-box">
          <div className="mypage-modal-box-header">
            <h5>팔로잉</h5>
            <button
              type="submit"
              className="mypage-modal-box-btn"
              onClick={exitFollowBox}
            >
              X
            </button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={박서준} alt="하이" />
            <p>유저네임</p>
            <button type="submit">팔로잉</button>
          </div>
        </div>
      ) : null}
    </div>
  );
};

export default FollowBox;
