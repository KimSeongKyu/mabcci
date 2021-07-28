import React from 'react';
import MabcciReview from './MabcciReview';
import MyPageFeed from './MyPageFeed';
import MyPageProfile from './MyPageProfile';

function MyPageMain() {
  return (
    <div id="container">
      <MyPageProfile />
      <MabcciReview />
      <MyPageFeed />
    </div>
  );
}

export default MyPageMain;
