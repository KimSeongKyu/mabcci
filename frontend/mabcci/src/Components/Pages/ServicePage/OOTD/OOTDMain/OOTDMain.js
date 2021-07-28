import React from 'react';
import { useHistory } from 'react-router-dom';

function OOTDMain() {
  const history = useHistory();
  const localLoinToken = localStorage.getItem('accessToken');

  // 로그인이 안되어 있는 경우 intro 화면으로
  if (!localLoinToken) {
    history.push('/intro');
  }

  return (
    <div>
      <h3>OOTD 페이지입니다.</h3>
    </div>
  );
}

export default OOTDMain;
