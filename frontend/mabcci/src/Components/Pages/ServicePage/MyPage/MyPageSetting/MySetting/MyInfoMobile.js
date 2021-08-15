/* eslint-disable */

import React from 'react';
import { IoArrowBackCircle } from 'react-icons/io5';
import './MySetting.css'
import '../../../../AuthPage/Signup/Signup.css'
import MAN_RECTANGLE from '../../../../../../Asset/Images/MAN_RECTANGLE.png';
import MAN_TRAPEZOIDAL from '../../../../../../Asset/Images/MAN_TRAPEZOIDAL.png';
import MAN_INVERTED_TRIANGLE from '../../../../../../Asset/Images/MAN_INVERTED_TRIANGLE.png';
import MAN_OVAL from '../../../../../../Asset/Images/MAN_OVAL.png';
import MAN_TRIANGLE from '../../../../../../Asset/Images/MAN_TRIANGLE.png';
import WOMAN_RECTANGLE from '../../../../../../Asset/Images/WOMAN_RECTANGLE.png';
import WOMAN_TRAPEZOIDAL from '../../../../../../Asset/Images/WOMAN_TRAPEZOIDAL.png';
import WOMAN_INVERTED_TRIANGLE from '../../../../../../Asset/Images/WOMAN_INVERTED_TRIANGLE.png';
import WOMAN_OVAL from '../../../../../../Asset/Images/WOMAN_OVAL.png';
import WOMAN_TRIANGLE from '../../../../../../Asset/Images/WOMAN_TRIANGLE.png';
import MypageUpdateApi from '../../../../../../API/MypageAPI/MyPageUpdateApi';
import MypageReadApi from '../../../../../../API/MypageAPI/MypageReadApi'
import { baseUrl } from '../../../../../../API/ApiUrl';

const MyInfoMobile = props => {

  const goBack = () => {
    props.setMyPageUpdate('none');
    props.setMobileMenu(true);
  };

  function changeUserInfo(e) {
    const { name, value } = e.target;
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      [name]: value,
    });
  }

  const changeGender = (e) => {
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      gender: e.target.name,
    });
  }

  const changeBodyType = e => {
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      bodyType: e.target.name,
    });
  };

  const submit = async () => {
    const res = await MypageUpdateApi(
      props.updateData,
      props.myUpdateInfo.nickname,
    );
    if (res.status === 200) {
      const res = await MypageReadApi(props.myUpdateInfo.nickname);
      if (res.myInfo.picture !== null) {
        res.myInfo.picture = baseUrl + res.myInfo.picture;
      }
      props.setMyInfo(res.myInfo);
      props.setMyPageUpdate('none');
      props.setMobileMenu(true);
        
    } else {
      console.log(res.status)
      alert('닉네임을 확인하세요')
    }
  }

  return (
    <>
      {props.myPageUpdate === 'info' ? (
        <div className="mypage-moblie-container" />
      ) : null}
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
              <input
                type="text"
                value={props.myUpdateInfo.nickname}
                name="nickname"
                onChange={changeUserInfo}
              />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>성별</h3>
              <div className="mypage-mobile-gender-box">
                <button
                  type="submit"
                  className={
                    props.myUpdateInfo.gender === 'MAN'
                      ? 'mypage-mobile-gender-btn-select'
                      : 'mypage-mobile-gender-btn'
                  }
                  onClick={changeGender}
                  name="MAN"
                >
                  MAN
                </button>
                <button
                  type="submit"
                  className={
                    props.myUpdateInfo.gender === 'WOMAN'
                      ? 'mypage-mobile-gender-btn-select'
                      : 'mypage-mobile-gender-btn'
                  }
                  onClick={changeGender}
                  name="WOMAN"
                >
                  WOMAN
                </button>
              </div>
            </div>
            <div className="mypage-mobile-update-content">
              <h3>키(cm)</h3>
              <input
                type="text"
                maxLength="3"
                value={props.myUpdateInfo.height}
                name="height"
                onChange={changeUserInfo}
              />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>몸무게(kg)</h3>
              <input
                type="text"
                maxLength="3"
                value={props.myUpdateInfo.weight}
                name="weight"
                onChange={changeUserInfo}
              />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>발(mm)</h3>
              <input
                type="text"
                maxLength="3"
                value={props.myUpdateInfo.footSize}
                name="footSize"
                onChange={changeUserInfo}
              />
            </div>
            <h3 className="mypage-bodytype-header">Body Type</h3>
            {props.myUpdateInfo.gender === 'MAN' ? (
              <div className="mypage-mobile-update-content">
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'INVERTED_TRIANGLE'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN_INVERTED_TRIANGLE}
                    alt="하이"
                    onClick={changeBodyType}
                    name="INVERTED_TRIANGLE"
                  />
                </button>
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'TRIANGLE'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN_TRIANGLE}
                    alt="하이"
                    onClick={changeBodyType}
                    name="TRIANGLE"
                  />
                </button>
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'RECTANGLE'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN_RECTANGLE}
                    alt="하이"
                    onClick={changeBodyType}
                    name="RECTANGLE"
                  />
                </button>
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'OVAL'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN_OVAL}
                    alt="하이"
                    onClick={changeBodyType}
                    name="OVAL"
                  />
                </button>
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'TRAPEZOIDAL'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN_TRAPEZOIDAL}
                    alt="하이"
                    onClick={changeBodyType}
                    name="TRAPEZOIDAL"
                  />
                </button>
              </div>
            ) : (
              <div className="mypage-mobile-update-content">
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'INVERTED_TRIANGLE'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN_INVERTED_TRIANGLE}
                    alt="하이"
                    onClick={changeBodyType}
                    name="INVERTED_TRIANGLE"
                  />
                </button>
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'TRIANGLE'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN_TRIANGLE}
                    alt="하이"
                    onClick={changeBodyType}
                    name="TRIANGLE"
                  />
                </button>
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'RECTANGLE'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN_RECTANGLE}
                    alt="하이"
                    onClick={changeBodyType}
                    name="RECTANGLE"
                  />
                </button>
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'OVAL'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN_OVAL}
                    alt="하이"
                    onClick={changeBodyType}
                    name="OVAL"
                  />
                </button>
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'TRAPEZOIDAL'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN_TRAPEZOIDAL}
                    alt="하이"
                    onClick={changeBodyType}
                    name="TRAPEZOIDAL"
                  />
                </button>
              </div>
            )}
          </div>
          <button className="mypage-submit-btn" type="submit" onClick={submit}>
            저장
          </button>
        </div>
      ) : null}
    </>
  );
};

export default MyInfoMobile;
