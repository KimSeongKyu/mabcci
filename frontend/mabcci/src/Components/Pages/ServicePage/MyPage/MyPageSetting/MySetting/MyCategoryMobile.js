/* eslint-disable */

import React from 'react';
import { IoArrowBackCircle } from 'react-icons/io5';

const MyCategoryMobile = props => {
  const goBack = () => {
    props.setMyPageUpdate('none');
    props.setMobileMenu(true);
  };

  return (
    <>
      {props.myPageUpdate === 'category' ? (
        <div className="mypage-mobile-menu mypage-mobile-update">
          <div className="mypage-mobile-menu-header">
            <button
              type="submit"
              className="mypage-mobile-menu-btn"
              onClick={goBack}
            >
              <IoArrowBackCircle />
            </button>
            <h3>카테고리 변경</h3>
          </div>
        </div>
      ) : null}
    </>
  );
};

export default MyCategoryMobile;
