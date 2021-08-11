/* eslint-disable */

import React from 'react';
import { IoArrowBackCircle } from 'react-icons/io5';
import './MySetting.css'

const MyInfoMobile = props => {
  const goBack = () => {
    props.setMyPageUpdate('none');
    props.setMobileMenu(true);
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
          <div className="mypage-mobile-update-box">
            <div className="mypage-mobile-update-content">
              <h3>닉네임</h3>
              <input type="text" />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>키(cm)</h3>
              <input type="number" placeholder="숫자만 입력해주세요" />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>몸무게(kg)</h3>
              <input type="number" placeholder="숫자만 입력해주세요" />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>발(mm)</h3>
              <input type="number" placeholder="숫자만 입력해주세요" />
            </div>
          </div>
        </div>
      ) : null}
    </>
  );
};

export default MyInfoMobile;
