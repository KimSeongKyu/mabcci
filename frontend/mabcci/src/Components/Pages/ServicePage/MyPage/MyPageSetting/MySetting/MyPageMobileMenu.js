/* eslint-disable */

import React from 'react';
import '../MyPageSetting.css';
import { IoArrowBackCircle } from 'react-icons/io5';
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { Logout } from '../../../../../../Redux/Actions/LoginAction';

const MyPageMobileMenu = props => {
  const dispatch = useDispatch();
  const history = useHistory();

  const userInfo = JSON.parse(localStorage.getItem('userInfo'));

  const goBack = () => {
    props.setMobileMenu(false)
  };

  const LogOut = () => {
    localStorage.clear();
    dispatch(Logout());
    history.push('/intro');
  };

  const goToMobileProposal = () => {
    props.setProposalBox(true)
  };

  return (
    <>
      {props.mobileMenu === true ? (
        <div className="mypage-moblie-container" />
      ) : null}
      {props.mobileMenu === true ? (
        <div className="mypage-mobile-menu">
          <div className="mypage-mobile-menu-header">
            <button
              type="submit"
              className="mypage-mobile-menu-btn"
              onClick={goBack}
            >
              <IoArrowBackCircle />
            </button>
            <h1>설정</h1>
          </div>
          <div className="mypage-mobile-menu-content">
            <h3>나의 계정</h3>
            <div>
              <button type="submit">내 정보 변경</button>
            </div>
            <div>
              <button type="submit">카테고리 변경</button>
            </div>
            <div>
              <button type="submit">프로필 변경</button>
            </div>
          </div>
          <div className="mypage-mobile-menu-content">
            <h3>서비스</h3>
            <div>
              <button type="submit">대화목록</button>
            </div>
            <div>
              <button type="submit" onClick={goToMobileProposal}>
                최종제안서
              </button>
            </div>
          </div>
          <button
            type="submit"
            className="mobile-menu-logout-btn"
            onClick={LogOut}
          >
            로그아웃
          </button>
        </div>
      ) : null}
    </>
  );
};

export default MyPageMobileMenu;
