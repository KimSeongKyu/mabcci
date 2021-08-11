/* eslint-disable */

import React from 'react';
import { IoArrowBackCircle } from 'react-icons/io5';
import './MySetting.css'
import '../../../../AuthPage/Signup/Signup.css'

const MyInfoMobile = props => {
  const goBack = () => {
    props.setMyPageUpdate('none');
    props.setMobileMenu(true);
  };

  console.log(props.myInfo)

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
              <input type="text" placeholder={props.myInfo.nickname} />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>성별</h3>
              <div className="mypage-mobile-gender-box">
                <button type="submit" className="mypage-mobile-gender-btn">
                  MAN
                </button>
                <button className=" mypage-mobile-gender-btn" type="submit">
                  WOMAN
                </button>
              </div>
            </div>
            <div className="mypage-mobile-update-content">
              <h3>키(cm)</h3>
              <input type="text" placeholder={props.myInfo.height} />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>몸무게(kg)</h3>
              <input type="text" placeholder={props.myInfo.weight} />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>발(mm)</h3>
              <input type="text" placeholder={props.myInfo.footSize} />
            </div>
          </div>
        </div>
      ) : null}
    </>
  );
};

export default MyInfoMobile;
