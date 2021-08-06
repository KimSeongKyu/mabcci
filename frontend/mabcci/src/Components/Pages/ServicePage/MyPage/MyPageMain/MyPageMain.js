import React from 'react';
import MabcciReview from './MabcciReview';
import MyPageFeed from './MyPageFeed';
import MyPageProfile from './MyPageProfile';

function MyPageMain() {
  const myInfo = {
    picture: '',
    category: ['스트릿', '캐쥬얼'],
    introduce: '인생은 스트릿하게🤘',
    height: '185',
    weight: '70',
    foot: '260',
    body: '',
    nickname: '서준팍',
  };

  return (
    <div className="container">
      <MyPageProfile myInfo={myInfo} />
      <MyPageFeed />
      <MabcciReview />
    </div>
  );
}

export default MyPageMain;
