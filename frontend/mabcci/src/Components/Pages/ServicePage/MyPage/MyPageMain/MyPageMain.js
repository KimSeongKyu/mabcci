import React from 'react';
import { useHistory } from 'react-router-dom';
import MabcciReview from './MabcciReview';
import MyPageFeed from './MyPageFeed';
import MyPageProfile from './MyPageProfile';

function MyPageMain() {
  const history = useHistory();
  const localLoinToken = localStorage.getItem('accessToken');

  // 로그인이 안되어 있는 경우 intro 화면으로
  if (!localLoinToken) {
    history.push('/intro');
  }
  return (
    <div id="container">
      <MyPageProfile />
      <MabcciReview />
      <MyPageFeed />
    </div>
  );
}

export default MyPageMain;
