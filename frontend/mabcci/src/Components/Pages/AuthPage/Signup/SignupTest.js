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


function SignupTest() {
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
  }

  // 스타일 선택 버튼 클릭
  function styleBtnClick(e) {
    e.target.classList.toggle('btn-select-style-active');
    const copy = [...userInfo]
    console.log(userInfo.categories)
    const idx = copy.indexOf(e.target.name);
    if (idx >= 0) {
      copy.splice(idx, 1);
      setUserInfo({
        ['catecories']:copy 
      })
    } else {
      copy.push(e.target.name);
      setUserInfo({
        ['catecories']: copy,
      });
    }
  }

  // // email 체크
  // const comCheck = userInfo.email.slice(
  //   userInfo.email.length - 4,
  //   userInfo.email.length,
  // );

  // const atCheck = userInfo.email.includes('@');

  // signup 버튼
  const handleSubmit = async e => {
    e.preventDefault();
    // const data = signupRedux;
    // const isEmpty = Object.values(data).some(x => x === '' || x.length === 0);

    // if (isEmpty === true) {
    //   alert('회원정보를 모두 입력해주세요');
    // } else {
    //   const response = await SignupApi(signupRedux);

    //   if (response.status === 200) {
    //     history.push('/login');
    //   } else {
    //     alert('회원가입 실패');
    //   }
    // }
  };

  return (
    <div className="input-box">
      <div className="input-list">
        {userInfo.email}
        {userInfo.nickname}
        {userInfo.passwordConfirmation}
        {userInfo.categories}
        {userInfo.gender}
        {userInfo.password}
        <input
          type="email"
          placeholder="Email"
          name="email"
          onChange={changeUserInfo}
        />
        {/* {comCheck !== '.com' || atCheck === false ? (
          <p className="warnning">이메일 형식으로 입력해주세요</p>
        ) : null} */}
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

      <button type="submit" onClick={handleSubmit} className="btn-rounded">
        Sign up
      </button>
    </div>
  );
}

export default SignupTest;
