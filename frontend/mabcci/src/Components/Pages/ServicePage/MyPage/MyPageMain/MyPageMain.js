import React, { useEffect, useState } from 'react';

import { HiMenu } from 'react-icons/hi';
import MabcciReview from './MabcciReview';
import MyPageFeed from './MyPageFeed';
import MyPageProfile from './MyPageProfile';
import MypageReadApi from '../../../../../API/MypageAPI/MypageReadApi';
import FollowBox from '../MyPageFollow/FollowBox';

function MyPageMain() {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'));

  const [myInfo, setMyInfo] = useState({});

  const [followBox, setFollowBox] = useState('none');

  useEffect(async () => {
    const res = await MypageReadApi(userInfo.nickname);
    // await setMyInfo(...myInfo, ...res.myInfo);
    await setMyInfo(res.myInfo);
  }, []);

  return (
    <div className="mypage-entire">
      <FollowBox followBox={followBox} setFollowBox={setFollowBox} />
      <div className="mypage-container">
        <button className="mypage-mobile-setting" type="submit">
          <HiMenu />
        </button>
        <MyPageProfile
          myInfo={myInfo}
          followBox={followBox}
          setFollowBox={setFollowBox}
        />
        <MyPageFeed />
        <MabcciReview />
      </div>
    </div>
  );
}

export default MyPageMain;
