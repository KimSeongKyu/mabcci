/* eslint-disable */

import React from 'react';
import { IoArrowBackCircle } from 'react-icons/io5';

const MyProfileMobile = props => {
  const goBack = () => {
    props.setMyPageUpdate('none');
  };

  return (
    <>
      {props.myPageUpdate === 'profile' ? (
        <div className="mypage-mobile-menu mypage-mobile-update">
          <div className="mypage-mobile-menu-header">
            <button
              type="submit"
              className="mypage-mobile-menu-btn"
              onClick={goBack}
            >
              <IoArrowBackCircle />
            </button>
            <h3>프로필 변경</h3>
          </div>
        </div>
      ) : null}
    </>
  );
};

export default MyProfileMobile;
