import React, { useState } from 'react';
// import { useDispatch } from 'react-redux';

function SignupForm() {
  const [userInfo, setUserInfo] = useState([
    {
      email: '',
      nickname: '',
      firstPhoneNumber: '',
      secondPhoneNumber: '',
      thirdPhoneNumber: '',
      password: '',
      passwordConfirmation: '',
    },
  ]);

  // const dispatch = useDispatch();

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
          <div className="phone-number">
            <div>PhoneNumber</div>
            <input
              type="text"
              name="firstPhoneNumber"
              onChange={changeUserInfo}
            />
            -
            <input
              type="text"
              name="secondPhoneNumber"
              onChange={changeUserInfo}
            />
            -
            <input
              type="text"
              name="thirdPhoneNumber"
              onChange={changeUserInfo}
            />
          </div>
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
            <p>비밀번호 맞춰주소</p>
          ) : null}
        </li>
      </ul>
    </div>
  );
}

export default SignupForm;
