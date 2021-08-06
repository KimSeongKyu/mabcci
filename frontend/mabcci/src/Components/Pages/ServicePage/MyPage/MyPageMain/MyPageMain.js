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
    const data = await MypageReadApi(userInfo.nickname);
    await setMyInfo(data);
    console.log(myInfo);
  }, []);

  return (
    <div className="container">
      <MyPageProfile myData={myInfo} />
      <MyPageFeed />
      <MabcciReview />
    </div>
  );
}

export default MyPageMain;
