/* eslint-disable */

import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import SignupApi from '../../../../API/AuthAPI/SignupApi';
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
    console.log(e.target);
    console.log(e.target.name);
    setUserInfo({
      ...userInfo,
      ['gender']: e.target.name,
    });
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

  // signup 버튼 실행시
  const handleSubmit = async e => {
    e.preventDefault();
    const data = {
      email: userInfo.email,
      nickname: userInfo.nickname,
      password: userInfo.password,
      gender: userInfo.gender,
      phoneNumber:
        userInfo.firstPhoneNumber +
        '-' +
        userInfo.secondPhoneNumber +
        '-' +
        userInfo.thirdPhoneNumber,
      categories: userInfo.categories,
    };

    const response = await SignupApi(data);

    if (response.status === 200) {
      return history.push('/login');
    }

    alert('회원가입 실패');
  };

  // email 알맞게 입력했는지 체크

  // .com으로 끝나는지
  const comCheck = userInfo.email.slice(
    userInfo.email.length - 4,
    userInfo.email.length,
  );

  // @ 개수가 몇개인지
  var count = 0;
  var pos = userInfo.email.indexOf('@');
  const atCheck = () => {
    while (pos !== -1) {
      count += 1;
      pos = userInfo.email.indexOf('@', pos + 1);
    }
    return count;
  };

  const isEmail = () => {
    if (
      comCheck !== '.com' ||
      userInfo.email.includes('@') === false ||
      userInfo.email.slice(0, 1) === '@' ||
      atCheck() > 1
    ) {
      return false;
    }
    return true;
  };

  // phoneNumber 알맞게 입력했는지 체크
  const isphoneNumber = () => {
    if (
      isNaN(Number(userInfo.firstPhoneNumber)) === true ||
      isNaN(Number(userInfo.secondPhoneNumber)) === true ||
      isNaN(Number(userInfo.thirdPhoneNumber)) === true ||
      userInfo.firstPhoneNumber.length !== 3 ||
      userInfo.secondPhoneNumber.length < 3 ||
      userInfo.thirdPhoneNumber.length !== 4
    ) {
      return false;
    }
    return true;
  };

  // signup 실행해도 되는지 체크 1 (정보 다 적었는지)
  const isEmpty = Object.values(userInfo).some(x => x === '' || x.length === 0);

  // signup 실행해도 되는지 체크 2 (정보 알맞게 적었는지)
  const isCorrect = () => {
    if (
      isEmail() === false ||
      userInfo.password !== userInfo.passwordConfirmation ||
      (userInfo.password.length < 10 && userInfo.password.length) ||
      isphoneNumber() === false
    ) {
      return false;
    }
    return true;
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
        {isEmail() === false && userInfo.email.length > 0 ? (
          <p className="warnning-email">이메일 형식으로 입력해주세요</p>
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
        {isphoneNumber() === false &&
        (userInfo.firstPhoneNumber.length > 0 ||
          userInfo.secondPhoneNumber.length > 0 ||
          userInfo.thirdPhoneNumber.length > 0) ? (
          <p className="warnning-phone">번호를 알맞게 입력해주세요</p>
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
          <p className="warnning-password">비밀번호가 다릅니다!</p>
        ) : null}

        {userInfo.password.length < 10 && userInfo.password.length > 0 ? (
          <p className="warnning-password-length">10자 이상으로 작성해주세요</p>
        ) : null}
      </div>
      <h5>성별</h5>
      <div className="select-man-woman">
        {userInfo.gender === 'MALE' ? (
          <button
            className="btn-sex-select"
            name="MALE"
            type="submit"
            onClick={mwBtnClick}
          >
            <p className="choice-gender">Man</p>
          </button>
        ) : (
          <button
            className="btn-rounded-sm"
            name="MALE"
            type="submit"
            onClick={mwBtnClick}
          >
            <p className="choice-gender">Man</p>
          </button>
        )}

        {userInfo.gender === 'FEMALE' ? (
          <button
            className="btn-sex-select"
            name="FEMALE"
            type="submit"
            onClick={mwBtnClick}
          >
            <p className="choice-gender">Woman</p>
          </button>
        ) : (
          <button
            className="btn-rounded-sm"
            name="FEMALE"
            type="submit"
            onClick={mwBtnClick}
          >
            <p className="choice-gender">Woman</p>
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
      {isEmpty === true || isCorrect() === false ? (
        <button
          type="submit"
          onClick={handleSubmit}
          className="btn-signup-nonactive"
        >
          <h5>회원정보를 모두 입력해주세요</h5>
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
