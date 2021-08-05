import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import SignupApi from '../../../../API/AuthAPI/SignupApi';
import { LoginAuto } from '../../../../Redux/Actions/LoginAction';
import 미니멀 from '../../../../Asset/Images/미니멀.png';
import 스트릿 from '../../../../Asset/Images/스트릿.png';
import 아메카지 from '../../../../Asset/Images/아메카지.png';
import 오피스 from '../../../../Asset/Images/오피스.png';
import 캐쥬얼 from '../../../../Asset/Images/캐쥬얼.png';
import 포멀 from '../../../../Asset/Images/포멀.png';

function SignupForm() {
  const history = useHistory();
  const dispatch = useDispatch();

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

  function changeUserInfo(e) {
    const { name, value } = e.target;
    setUserInfo({
      ...userInfo,
      [name]: value,
    });
  }

  function mwBtnClick(e) {
    setUserInfo({
      ...userInfo,
      gender: e.target.name,
    });
  }

  function styleBtnClick(e) {
    const copyCategory = [...userInfo.categories];

    const nowCategory = e.target.name;

    let index = -2;
    for (let i = 0; i < copyCategory.length; i += 1) {
      if (copyCategory[i] === nowCategory) {
        index = i;
      }
    }
    if (index > -1) {
      copyCategory.splice(index, 1);
    } else {
      copyCategory.push(nowCategory);
    }
    setUserInfo({
      ...userInfo,
      categories: copyCategory,
    });
  }

  const signupSubmit = async e => {
    e.preventDefault();
    const data = {
      email: userInfo.email,
      nickname: userInfo.nickname,
      password: userInfo.password,
      gender: userInfo.gender,
      phone: `${userInfo.firstPhoneNumber}-${userInfo.secondPhoneNumber}-${userInfo.thirdPhoneNumber}`,
      categories: userInfo.categories,
    };
    const response = await SignupApi(data);

    if (response.status === 200) {
      const authUserInfo = {
        email: userInfo.email,
        password: userInfo.password,
      };
      dispatch(LoginAuto(authUserInfo));
      history.push('/desc');
    } else {
      console.log(response.status);
    }
  };

  const isEmail = () => {
    if (userInfo.email.match(/^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/)) {
      return true;
    }
    return false;
  };

  const isPhoneNumber = () => {
    const phonNumber =
      userInfo.firstPhoneNumber +
      userInfo.secondPhoneNumber +
      userInfo.thirdPhoneNumber;
    const regExp = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
    return regExp.test(phonNumber);
  };

  const isCorrectPassword = () => {
    const regExp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    return regExp.test(userInfo.password);
  };

  const isSamePassword = () => {
    if (userInfo.password !== userInfo.passwordConfirmation) {
      return false;
    }
    return true;
  };

  const isSignupOK = () => {
    if (
      Object.values(userInfo).some(x => x === '' || x.length === 0) === true ||
      isEmail() === false ||
      isPhoneNumber() === false ||
      isSamePassword() === false ||
      isCorrectPassword() === false
    ) {
      return false;
    }
    return true;
  };

  return (
    <div className="signup-input-box">
      <div className="signup-input-list">
        <input
          type="email"
          placeholder="Email"
          name="email"
          onChange={changeUserInfo}
        />
        {isEmail() === false && userInfo.email.length > 0 ? (
          <p className="signup-warnning-email">이메일 형식으로 입력해주세요</p>
        ) : null}
        <input
          type="text"
          placeholder="Nickname"
          name="nickname"
          onChange={changeUserInfo}
        />
        <div className="signup-phone-number">
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
        {isPhoneNumber() === false &&
        (userInfo.firstPhoneNumber.length > 0 ||
          userInfo.secondPhoneNumber.length > 0 ||
          userInfo.thirdPhoneNumber.length > 0) ? (
          <p className="signup-warnning-phone">번호를 알맞게 입력해주세요</p>
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
        {!isSamePassword() ? (
          <p className="signup-warnning-password">비밀번호가 다릅니다!</p>
        ) : null}
        {!isCorrectPassword() ? (
          <p className="signup-warnning-password-length">
            8자 이상의 숫자, 문자 조합으로 작성해주세요
          </p>
        ) : null}
      </div>
      <h5>성별</h5>
      <div className="signup-select-man-woman">
        {userInfo.gender === 'MALE' ? (
          <button
            className="signup-btn-sex-select"
            name="MAN"
            type="submit"
            onClick={mwBtnClick}
          >
            <p className="signup-choice-gender">MALE</p>
          </button>
        ) : (
          <button
            className="btn-rounded-sm"
            name="MALE"
            type="submit"
            onClick={mwBtnClick}
          >
            <p className="signup-choice-gender">MALE</p>
          </button>
        )}

        {userInfo.gender === 'FEMALE' ? (
          <button
            className="signup-btn-sex-select"
            name="FEMALE"
            type="submit"
            onClick={mwBtnClick}
          >
            <p className="signup-choice-gender">FEMALE</p>
          </button>
        ) : (
          <button
            className="btn-rounded-sm"
            name="FEMALE"
            type="submit"
            onClick={mwBtnClick}
          >
            <p className="signup-choice-gender">FEMALE</p>
          </button>
        )}
      </div>

      <h5>선호하는 스타일을 골라주세요!</h5>
      <div className="signup-style-box">
        <div className="signup-select-style">
          <div>
            <button
              className={
                userInfo.categories.includes('미니멀') === false
                  ? 'signup-btn-select-style'
                  : 'signup-btn-select-style-active'
              }
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
              className={
                userInfo.categories.includes('스트릿') === false
                  ? 'signup-btn-select-style'
                  : 'signup-btn-select-style-active'
              }
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
              className={
                userInfo.categories.includes('아메카지') === false
                  ? 'signup-btn-select-style'
                  : 'signup-btn-select-style-active'
              }
              type="submit"
              name="아메카지"
              onClick={styleBtnClick}
            >
              <img src={아메카지} alt="아메카지" />
            </button>
            <p>아메카지</p>
          </div>
        </div>
        <div className="signup-select-style">
          <div>
            <button
              className={
                userInfo.categories.includes('오피스') === false
                  ? 'signup-btn-select-style'
                  : 'signup-btn-select-style-active'
              }
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
              className={
                userInfo.categories.includes('캐쥬얼') === false
                  ? 'signup-btn-select-style'
                  : 'signup-btn-select-style-active'
              }
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
              className={
                userInfo.categories.includes('포멀') === false
                  ? 'signup-btn-select-style'
                  : 'signup-btn-select-style-active'
              }
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
      {isSignupOK() === false ? (
        <button
          type="submit"
          onClick={signupSubmit}
          className="signup-btn-signup-nonactive"
        >
          <p>회원정보를 모두 입력해주세요</p>
        </button>
      ) : (
        <button type="submit" onClick={signupSubmit} className="btn-rounded">
          Sign up
        </button>
      )}
    </div>
  );
}

export default SignupForm;
