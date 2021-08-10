/* eslint-disable */

import React from 'react';
import './MySetting.css';

const MyPageUpdate = (props) => {
  const closeUpdate = () => {
    props.setMyPageUpdate('none')
  }
  return (
    <>
      {props.myPageUpdate !== 'none' ? (
        <div className="mypage-modal-container" />
      ) : null}
      {props.myPageUpdate !== 'none' ? (
        <div className="mypage-update-container">
          <h1>회원정보수정페이지</h1>
          <h2>하이루</h2>
          <h2>하이루</h2>
          <button type="submit" onClick={closeUpdate}>
            X
          </button>
        </div>
      ) : null}
    </>
  );
};

export default MyPageUpdate;
