import React from 'react';
import { useHistory } from 'react-router-dom';
import OOTDFeed from './OOTDFeed';
import OOTDHeader from './OOTDHeader';

function OOTDMain() {
  const history = useHistory();
  const localLoinToken = localStorage.getItem('accessToken');

  // 로그인이 안되어 있는 경우 intro 화면으로
  if (!localLoinToken) {
    history.push('/intro');
  }

  return (
    <div className="container">
      <OOTDHeader />
      <OOTDFeed />
    </div>
  );
}

export default OOTDMain;
