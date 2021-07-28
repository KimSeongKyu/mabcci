/* eslint-disable */

import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { SignupInput } from '../../../../Redux/Actions/SignupAction';
// import { useDispatch } from 'react-redux';

function SignupForm() {
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

  // const dispatch = useDispatch();

  // input값에 적은 유저정보 저장하기
  function changeUserInfo(e) {
    const { name, value } = e.target;
    setUserInfo({
      ...userInfo,
      [name]: value,
    });
    console.log(e.target.value)
    dispatch(SignupInput(userInfo));
  }
  return (
    <div>
      <ul className="input-list">
        <li>
          <input
            type="email"
            placeholder="Email"
            name="email"
            onChange={changeUserInfo}
          />
        </li>
        <li>
          <input
            type="text"
            placeholder="Nickname"
            name="nickname"
            onChange={changeUserInfo}
          />
        </li>
        <li>
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
        </li>
        <li>
          <input
            type="password"
            placeholder="Password"
            name="password"
            onChange={changeUserInfo}
          />
        </li>
        <li>
          <input
            type="password"
            placeholder="PasswordConfirm"
            name="passwordConfirmation"
            onChange={changeUserInfo}
            
          />
          {userInfo.password !== userInfo.passwordConfirmation ? (
            <p className="warnning">비밀번호가 다릅니다!</p>
          ) : null}
        </li>
      </ul>
    </div>
  );
}

export default SignupForm;
