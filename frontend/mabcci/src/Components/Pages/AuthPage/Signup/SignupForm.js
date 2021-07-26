import React, { useState } from 'react';

function SignupForm() {
  const [userInfo, setUserInfo] = useState([
    {
      email: '',
      nickname: '',
      phoneNumber: '',
      password: '',
      passwordConfirmation: '',
    },
  ]);

  // input값에 적은 유저정보 저장하기
  function changeUserInfo(e) {
    const { name, value } = e.target;
    setUserInfo({
      ...userInfo,
      [name]: value,
    });
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
          <input
            type="text"
            placeholder="Phone_number"
            name="phoneNumber"
            onChange={changeUserInfo}
          />
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
            placeholder="Password_confirm"
            name="passwordConfirmation"
            onChange={changeUserInfo}
          />
          {userInfo.password !== userInfo.passwordConfirmation ? (
            <p>비밀번호 맞춰주소</p>
          ) : null}
        </li>
      </ul>
      <h4>성별</h4>

      <h4>선호하는 스타일을 골라주세요!</h4>
    </div>
  );
}

export default SignupForm;
