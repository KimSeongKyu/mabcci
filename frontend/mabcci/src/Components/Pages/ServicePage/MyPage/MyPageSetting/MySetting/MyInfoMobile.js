/* eslint-disable */

import React from 'react';
import { IoArrowBackCircle } from 'react-icons/io5';
import './MySetting.css'

const MyInfoMobile = props => {
  const goBack = () => {
    props.setMyPageUpdate('none');
  };

  return (
    <>
      {props.myPageUpdate === 'info' ? (
        <div className="mypage-mobile-menu mypage-mobile-update">
          <div className="mypage-mobile-menu-header">
            <button
              type="submit"
              className="mypage-mobile-menu-btn"
              onClick={goBack}
            >
              <IoArrowBackCircle />
            </button>
            <h3>내 정보 변경</h3>
          </div>
        </div>
      ) : null}
    </>
  );
};

export default MyInfoMobile;
