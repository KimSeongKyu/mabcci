/* eslint-disable */

import React from 'react';
import { IoArrowBackCircle } from 'react-icons/io5';
import './MySetting.css'
import '../../../../AuthPage/Signup/Signup.css'
import MAN직사각형 from '../../../../../../Asset/Images/MAN직사각형.png';
import MAN사다리꼴형 from '../../../../../../Asset/Images/MAN사다리꼴형.png';
import MAN역삼각형 from '../../../../../../Asset/Images/MAN역삼각형.png';
import MAN타원형 from '../../../../../../Asset/Images/MAN타원형.png';
import MAN삼각형 from '../../../../../../Asset/Images/MAN삼각형.png';
import WOMAN직사각형 from '../../../../../../Asset/Images/WOMAN직사각형.png';
import WOMAN사다리꼴형 from '../../../../../../Asset/Images/WOMAN사다리꼴형.png';
import WOMAN역삼각형 from '../../../../../../Asset/Images/WOMAN역삼각형.png';
import WOMAN타원형 from '../../../../../../Asset/Images/WOMAN타원형.png';
import WOMAN삼각형 from '../../../../../../Asset/Images/WOMAN삼각형.png';
import MypageUpdateApi from '../../../../../../API/MypageAPI/MyPageUpdateApi'

const MyInfoMobile = props => {

  console.log(props.myInfo)

  const goBack = () => {
    props.setMyPageUpdate('none');
    props.setMobileMenu(true);
  };

  function changeUserInfo(e) {
    const { name, value } = e.target;
    props.setMyInfo({
      ...props.myInfo,
      [name]: value,
    });
  }

  const changeGender = (e) => {
    props.setMyInfo({
      ...props.myInfo,
      gender: e.target.name,
    })
  }

  const changeBodyType = e => {
    props.setMyInfo({
      ...props.myInfo,
      bodyType: e.target.name,
    });
  };

  const submit = async () => {
    const updateData = new FormData();
    updateData.append('nickname', props.myInfo.nickname)
    updateData.append('gender', props.myInfo.gender);
    updateData.append('height', props.myInfo.height);
    updateData.append('weight', props.myInfo.weight);
    updateData.append('footSize', props.myInfo.footSize);
    updateData.append('categories', props.myInfo.categories);
    updateData.append('picture', props.myInfo.picture);
    updateData.append('description', props.myInfo.description);
    const res = await MypageUpdateApi(updateData, props.myInfo.nickname);
    if (res.status === 200) {
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
                value={props.myInfo.nickname}
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
                    props.myInfo.gender === 'MAN'
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
                    props.myInfo.gender === 'WOMAN'
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
                type="number"
                value={props.myInfo.height}
                name="height"
                onChange={changeUserInfo}
              />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>몸무게(kg)</h3>
              <input
                type="number"
                value={props.myInfo.weight}
                name="weight"
                onChange={changeUserInfo}
              />
            </div>
            <div className="mypage-mobile-update-content">
              <h3>발(mm)</h3>
              <input
                type="number"
                value={props.myInfo.footSize}
                name="footSize"
                onChange={changeUserInfo}
              />
            </div>
            <h3 className="mypage-bodytype-header">Body Type</h3>
            {props.myInfo.gender === 'MAN' ? (
              <div className="mypage-mobile-update-content">
                <button
                  className={
                    props.myInfo.bodyType === '역삼각형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN역삼각형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="역삼각형"
                  />
                </button>
                <button
                  className={
                    props.myInfo.bodyType === '삼각형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN삼각형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="삼각형"
                  />
                </button>
                <button
                  className={
                    props.myInfo.bodyType === '직사각형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN직사각형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="직사각형"
                  />
                </button>
                <button
                  className={
                    props.myInfo.bodyType === '타원형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN타원형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="타원형"
                  />
                </button>
                <button
                  className={
                    props.myInfo.bodyType === '사다리꼴형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={MAN사다리꼴형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="사다리꼴형"
                  />
                </button>
              </div>
            ) : (
              <div className="mypage-mobile-update-content">
                <button
                  className={
                    props.myInfo.bodyType === '역삼각형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN역삼각형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="역삼각형"
                  />
                </button>
                <button
                  className={
                    props.myInfo.bodyType === '삼각형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN삼각형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="삼각형"
                  />
                </button>
                <button
                  className={
                    props.myInfo.bodyType === '직사각형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN직사각형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="직사각형"
                  />
                </button>
                <button
                  className={
                    props.myInfo.bodyType === '타원형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN타원형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="타원형"
                  />
                </button>
                <button
                  className={
                    props.myInfo.bodyType === '사다리꼴형'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                >
                  <img
                    src={WOMAN사다리꼴형}
                    alt="하이"
                    onClick={changeBodyType}
                    name="사다리꼴형"
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
