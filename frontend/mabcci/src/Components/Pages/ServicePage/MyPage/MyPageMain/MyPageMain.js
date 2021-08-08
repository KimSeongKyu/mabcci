import React, { useEffect, useState } from 'react';
import { useStore } from 'react-redux';
import MabcciReview from './MabcciReview';
import MyPageFeed from './MyPageFeed';
import MyPageProfile from './MyPageProfile';
import MypageReadApi from '../../../../../API/MypageAPI/MypageReadApi';

function MyPageMain() {
  const userInfo = JSON.parse(localStorage.getItem('userInfo'));

  const [myInfo, setMyInfo] = useState({});

  useEffect(async () => {
    const res = await MypageReadApi(userInfo.nickname);
    // await setMyInfo(...myInfo, ...res.myInfo);
    await setMyInfo(res.myInfo);
  }, []);

  return (
    <div className="container">
      <MyPageProfile myInfo={myInfo} />
      <MyPageFeed />
      <MabcciReview />
    </div>
  );
}

export default MyPageMain;
