/* eslint-disable */

import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { SignupInput } from '../../../../Redux/Actions/SignupAction';
// import { useDispatch } from 'react-redux';

function SignupBody() {
  const dispatch = useDispatch();

  const [userInfo, setUserInfo] = useState({
    email: '',
    nickname: '',
    firstPhoneNumber: '',
    secondPhoneNumber: '',
    thirdPhoneNumber: '',
    password: '',
    passwordConfirmation: '',
  });
  
  // email 체크
    const comCheck = userInfo.email.slice(
      userInfo.email.length - 4,
      userInfo.email.length,
    );

    const atCheck = userInfo.email.includes('@')

  // input값에 적은 유저정보 저장하기
  function changeUserInfo(e) {
    const { name, value } = e.target;
    setUserInfo({
      ...userInfo,
      [name]: value,
    });
    console.log(userInfo)
    dispatch(SignupInput(userInfo));
  }
  return (
    <div className="input-box">
      <div className="input-list">
        {userInfo.email}
        <input
          type="email"
          placeholder="Email"
          name="email"
          onChange={changeUserInfo}
        />
        {comCheck !== '.com' || atCheck === false ? (
          <p className="warnning">이메일 형식으로 입력해주세요</p>
        ) : null}
        <input
          type="text"
          placeholder="Nickname"
          name="nickname"
          onChange={changeUserInfo}
        />
        <div className="phone-number">
          <div>PhoneNumber</div>
          <input
            name="firstPhoneNumber"
            maxLength="3"
            onChange={changeUserInfo}
          />
          -
          <input
            name="secondPhoneNumber"
            maxLength="4"
            onChange={changeUserInfo}
          />
          -
          <input
            name="thirdPhoneNumber"
            maxLength="4"
            onChange={changeUserInfo}
          />
        </div>
        {isNaN(Number(userInfo.firstPhoneNumber)) === true ||
        isNaN(Number(userInfo.secondPhoneNumber)) === true ||
        isNaN(Number(userInfo.thirdPhoneNumber)) === true ? (
          <p className="warnning">숫자를 입력해주세요</p>
        ) : null}
        <input
          type="password"
          placeholder="Password"
          name="password"
          onChange={changeUserInfo}
        />
        <input
          type="password"
          placeholder="PasswordConfirm"
          name="passwordConfirmation"
          onChange={changeUserInfo}
        />
        {userInfo.password !== userInfo.passwordConfirmation ? (
          <p className="warnning">비밀번호가 다릅니다!</p>
        ) : null}
      </div>
    </div>
  );
}

export default SignupBody;
