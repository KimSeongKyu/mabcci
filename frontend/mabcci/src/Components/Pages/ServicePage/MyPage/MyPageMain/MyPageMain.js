import React from 'react';
import MabcciReview from './MabcciReview';
import MyPageFeed from './MyPageFeed';
import MyPageProfile from './MyPageProfile';

function MyPageMain() {
  const myInfo = {
    picture: '',
    category: ['스트릿', '캐쥬얼'],
    introduce: '안녕하세요 박서준입니다.',
    height: '185',
    weight: '70',
    foot: '260',
    body: '',
  };

  return (
    <div className="container">
      <MyPageProfile />
      <MyPageFeed />
      <MabcciReview />
    </div>
  );
}

export default MyPageMain;
