/* eslint-disable */

import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import SignupApi from '../../../../API/AuthAPI/SingupApi';
import 미니멀 from '../../../../Asset/Images/미니멀.png';
import 스트릿 from '../../../../Asset/Images/스트릿.png';
import 아메카지 from '../../../../Asset/Images/아메카지.png';
import 오피스 from '../../../../Asset/Images/오피스.png';
import 캐쥬얼 from '../../../../Asset/Images/캐쥬얼.png';
import 포멀 from '../../../../Asset/Images/포멀.png';


function SignupForm() {
  const history = useHistory();

  const [userInfo, setUserInfo] = useState({
    email: '',
    nickname: '',
    firstPhoneNumber: '',
    secondPhoneNumber: '',
    thirdPhoneNumber: '',
    password: '',
    passwordConfirmation: '',
    gender: '',
    categories: [],
  });


  // input값에 적은 유저정보 저장하기
  function changeUserInfo(e) {
    const { name, value } = e.target;
    setUserInfo({
      ...userInfo,
      [name]: value,
    });
  }

  // 성별 선택 버튼 클릭
  function mwBtnClick(e) {
    setUserInfo({
      ...userInfo,
      ['gender']: e.target.name
    })
  }

  // 스타일 선택 버튼 클릭
  function styleBtnClick(e) {
    e.target.classList.toggle('btn-select-style-active');
    const copy = [...userInfo.categories];
    const idx = copy.indexOf(e.target.name);
    if (idx >= 0) {
      copy.splice(idx, 1);
    } else {
      copy.push(e.target.name);
    }

    setUserInfo({
      ...userInfo,
      ['categories']: copy,
    });
  }

  // email 체크
  const comCheck = userInfo.email.slice(
    userInfo.email.length - 4,
    userInfo.email.length,
  );

  const atCheck = userInfo.email.includes('@');

  // signup 가능 체크
  const isEmpty = Object.values(userInfo).some(
    x => x === '' || x.length === 0,
  );

  // signup 버튼
  const handleSubmit = async e => {
    e.preventDefault();
    const data = {
      email : userInfo.email,
      nickname : userInfo.nickname,
      password : userInfo.password,
      gender : userInfo.gender,
      phonenumber : userInfo.firstPhoneNumber + '-' + userInfo.secondPhoneNumber + '-' + userInfo.thirdPhoneNumber,
      categories : userInfo.categories
    }

    console.log(data)

    const response = await SignupApi(data);

    if (response.status === 200) {
      history.push('/login');
    } else {
      alert('회원가입 실패');
    }
    
  };

  return (
    <div className="input-box">
      <div className="input-list">
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
      <h5>성별</h5>
      <div className="select-man-woman">
        {userInfo.gender === 'MALE' ? (
          <button
            className="btn-sex-select"
            type="submit"
            name="MALE"
            onClick={mwBtnClick}
          >
            Man
          </button>
        ) : (
          <button
            className="btn-rounded-sm"
            type="submit"
            name="MALE"
            onClick={mwBtnClick}
          >
            Man
          </button>
        )}

        {userInfo.gender === 'FEMALE' ? (
          <button
            className="btn-sex-select"
            type="submit"
            name="FEMALE"
            onClick={mwBtnClick}
          >
            Woman
          </button>
        ) : (
          <button
            className="btn-rounded-sm"
            type="submit"
            name="FEMALE"
            onClick={mwBtnClick}
          >
            Woman
          </button>
        )}
      </div>

      <h5>선호하는 스타일을 골라주세요!</h5>
      <div className="style-box">
        <div className="select-style">
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="미니멀"
              onClick={styleBtnClick}
            >
              <img src={미니멀} alt="미니멀" />
            </button>
            <p>미니멀</p>
          </div>
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="스트릿"
              onClick={styleBtnClick}
            >
              <img src={스트릿} alt="스트릿" />
            </button>
            <p>스트릿</p>
          </div>
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="아메카지"
              onClick={styleBtnClick}
            >
              <img src={아메카지} alt="아메카지" />
            </button>
            <p>아메카지</p>
          </div>
        </div>
        <div className="select-style">
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="오피스"
              onClick={styleBtnClick}
            >
              <img src={오피스} alt="오피스" />
            </button>
            <p>오피스</p>
          </div>
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="캐쥬얼"
              onClick={styleBtnClick}
            >
              <img src={캐쥬얼} alt="캐쥬얼" />
            </button>
            <p>캐쥬얼</p>
          </div>
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="포멀"
              onClick={styleBtnClick}
            >
              <img src={포멀} alt="포멀" />
            </button>
            <p>포멀</p>
          </div>
        </div>
      </div>
      {isEmpty === true ? (
        <button
          type="submit"
          onClick={handleSubmit}
          className="btn-signup-nonactive"
        >
          회원정보를 모두 입력해주세요
        </button>
      ) : (
        <button type="submit" onClick={handleSubmit} className="btn-rounded">
          Sign up
        </button>
      )}
    </div>
  );
}

export default SignupForm;
