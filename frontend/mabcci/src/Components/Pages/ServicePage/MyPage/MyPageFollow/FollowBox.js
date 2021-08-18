/* eslint-disable */

import React, { useState, useEffect } from 'react';
import { Link, useParams, useHistory } from 'react-router-dom';
import './Follow.css';
import { IoMdClose } from 'react-icons/io';
import FollowingListApi from '../../../../../API/MypageAPI/FollowingListApi'
import FollowerListApi from '../../../../../API/MypageAPI/FollowerListApi';
import 기본이미지 from '../../../../../Asset/Images/기본프로필.jpg'

const FollowBox = props => {
  const { nickname } = useParams();
  const baseUrl = 'http://localhost:8080';

  const follower = {
    follower: props.myInfo.nickname
  };

  const following = {
    following: props.myInfo.nickname
  }

  const [followerList, setFollowerList] = useState()
  const [followingList, setFollowingList] = useState()

  useEffect(async () => {
    const followingRes = await FollowingListApi(
      props.myInfo.nickname,
      following,
    );
    const followerRes = await FollowerListApi(props.myInfo.nickname, follower);
    setFollowerList(followerRes.data);
    setFollowingList(followingRes.data);
  }, [props.myInfo.nickname, props.myInfo.follower, props.myInfo.following]);

  const exitFollowBox = () => {
    props.setFollowBox('none');
  }

  const goToUserPage = (e) => {
    props.setFollowBox('none');
  }

  return (
    <div>
      {props.followBox === '팔로워' || props.followBox === '팔로잉' ? (
        <div className="mypage-follow-modal-container" />
      ) : null}

      {props.followBox === '팔로워' ? (
        <div className="mypage-follow-box">
          <div className="mypage-modal-box-header">
            <h5>팔로워</h5>
            <button
              type="submit"
              className="mypage-modal-box-btn"
              onClick={exitFollowBox}
            >
              <IoMdClose />
            </button>
          </div>
          {followerList.map(follower => {
            return (
              <div className="mypage-modal-box-content" key={follower}>
                <div className="mypage-modal-box-information">
                  <img src={follower.picture == null ? 기본이미지
                  :baseUrl + follower.picture} alt="하이" />
                  <Link to={`/mypage/${follower.name}`} onClick={goToUserPage}>
                    <p>{follower.name}</p>
                  </Link>
                </div>
                <button type="submit">삭제</button>
              </div>
            );
          })}
        </div>
      ) : null}

      {props.followBox === '팔로잉' ? (
        <div className="mypage-follow-box">
          <div className="mypage-modal-box-header">
            <h5>팔로잉</h5>
            <button
              type="submit"
              className="mypage-modal-box-btn"
              onClick={exitFollowBox}
            >
              <IoMdClose />
            </button>
          </div>
          {followingList.map(following => {
            return (
              <div className="mypage-modal-box-content">
                <div className="mypage-modal-box-information">
                  <img
                    src={
                      follower.picture === null
                        ? 기본이미지
                        : baseUrl + following.picture
                    }
                    alt="하이"
                  />
                  <Link to={`/mypage/${following.name}`} onClick={goToUserPage}>
                    <p>{following.name}</p>
                  </Link>
                </div>
                <button type="submit">팔로잉</button>
              </div>
            );
          })}
        </div>
      ) : null}
    </div>
  );
};

export default FollowBox;
