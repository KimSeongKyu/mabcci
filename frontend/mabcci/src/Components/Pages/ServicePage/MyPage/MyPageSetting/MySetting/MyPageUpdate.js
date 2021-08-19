/* eslint-disable */

import React from 'react';
import './MySetting.css';
import '../../MyPageFollow/Follow.css'
import MypageReadApi from '../../../../../../API/MypageAPI/MypageReadApi';
import MypageUpdateApi from '../../../../../../API/MypageAPI/MyPageUpdateApi';
import getUserInfo from '../../../../../Common/getUserInfo'
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
import 미니멀 from '../../../../../../Asset/Images/미니멀.png';
import 아메카지 from '../../../../../../Asset/Images/아메카지.png';
import 스트릿 from '../../../../../../Asset/Images/스트릿.png';
import 오피스 from '../../../../../../Asset/Images/오피스.png';
import 캐쥬얼 from '../../../../../../Asset/Images/캐쥬얼.png';
import 포멀 from '../../../../../../Asset/Images/포멀.png';
import { IoIosCloseCircle } from "react-icons/io"
import { BsXCircle } from 'react-icons/bs';
import { baseUrl } from '../../../../../../API/ApiUrl';
import 기본프로필 from '../../../../../../Asset/Images/기본프로필.jpg'


const MyPageUpdate = (props) => {

  const userInfo = getUserInfo();

  const closeUpdate = () => {
    props.setMyPageUpdate('none')
    props.setMobileMenu(false)
  }

  const submit = async () => {
    console.log(props.myUpdateInfo);
    console.log(props.updateData);
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
      alert('프로필사진을 변경해주세요');
    }
  };

  const updateProfile = e => {
    const nowSelectProfile = e.target.files[0];
    const nowSelectProfileURL = URL.createObjectURL(nowSelectProfile);
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      picture: nowSelectProfileURL,
      updatePicture: nowSelectProfile,
    });
  };

  const initializeProfile = () => {
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      picture: null,
      updatePicture: null,
    });
  };

  function changeUserInfo(e) {
    const { name, value } = e.target;
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      [name]: value,
    });
  }

  const changeGender = e => {
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      gender: e.target.name,
    });
  };

  const changeBodyType = e => {
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      bodyType: e.target.name,
    });
    console.log(props.myUpdateInfo)
  };

    const styleBtnClick = e => {
      const copyCategory = [...props.myUpdateInfo.categories];

      const nowCategory = e.target.name;

      const findResult = copyCategory.indexOf(nowCategory);

      if (findResult === -1) {
        copyCategory.push(nowCategory);
      } else {
        copyCategory.splice(findResult, 1);
      }
      props.setMyUpdateInfo({
        ...props.myUpdateInfo,
        categories: copyCategory,
      });
    };

  return (
    <>
      {props.myPageUpdate !== 'none' ? (
        <div className="mypage-modal-container" />
      ) : null}
      {props.myPageUpdate !== 'none' ? (
        <div className="mypage-update-container">
          <header className="mypage-update-header">
            <h3>회원정보 수정</h3>
            <button
              type="submit"
              className="mypage-mobile-menu-btn"
              onClick={closeUpdate}
            >
              <IoIosCloseCircle />
            </button>
          </header>

          <h5 className="mypage-mobile-profile-h5">프로필 사진</h5>
          <section className="mypage-mobile-profile">
            {props.myUpdateInfo.picture !== null ? (
              <div className="mypage-mobile-profile-picture">
                <img src={props.myUpdateInfo.picture} alt="No image"></img>
                <button type="submit" onClick={initializeProfile}>
                  <BsXCircle />
                </button>
              </div>
            ) : (
              <div className="mypage-mobile-profile-picture">
                <img src={기본프로필} alt="" />
                <button type="submit" onClick={initializeProfile}>
                  <BsXCircle />
                </button>
              </div>
            )}
          </section>
          <section>
            <label
              htmlFor="update-profile"
              className="mypage-mobile-profile-input"
            >
              사진 변경
            </label>
            <input
              type="file"
              id="update-profile"
              style={{ display: 'none' }}
              accept=".jpg,.jpeg,.png"
              onChange={updateProfile}
            />
          </section>
          <h5 className="mypage-mobile-profile-h5">프로필 소개</h5>
          <section>
            <textarea
              name="description"
              id=""
              cols="40"
              rows="10"
              value={props.myUpdateInfo.description}
              onChange={changeUserInfo}
            ></textarea>
          </section>

          <h5 className="mypage-mobile-profile-h5">프로필 정보</h5>
          <section className="mypage-mobile-update-box">
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
          </section>

          <h5 className="mypage-mobile-profile-h5">Body Type</h5>
          <section className="mypage-mobile-update-box">
            {props.myUpdateInfo.gender === 'MAN' ? (
              <div className="mypage-mobile-update-bodyType">
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'NONE'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                  name="NONE"
                  onClick={changeBodyType}
                >
                  NO TYPE
                </button>
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
              <div className="mypage-mobile-update-bodyType">
                <button
                  className={
                    props.myUpdateInfo.bodyType === 'NONE'
                      ? 'mypage-bodytype-btn mypage-bodytype-btn-select'
                      : 'mypage-bodytype-btn'
                  }
                  name="NONE"
                  onClick={changeBodyType}
                >
                  NO TYPE
                </button>
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
          </section>

          <h5 className="mypage-mobile-profile-h5">Category</h5>
          <section className="signup-style-box ">
            <div className="signup-select-style mypage-mobile-category">
              <div>
                <button
                  className={
                    props.myUpdateInfo.categories.includes('미니멀') === false
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
                    props.myUpdateInfo.categories.includes('스트릿') === false
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
            </div>
            <div className="signup-select-style mypage-mobile-category">
              <div>
                <button
                  className={
                    props.myUpdateInfo.categories.includes('아메카지') === false
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
              <div>
                <button
                  className={
                    props.myUpdateInfo.categories.includes('오피스') === false
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
            </div>
            <div className="signup-select-style mypage-mobile-category">
              <div>
                <button
                  className={
                    props.myUpdateInfo.categories.includes('캐쥬얼') === false
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
                    props.myUpdateInfo.categories.includes('포멀') === false
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
          </section>

          <footer>
            <button className="btn-sm" type="submit" onClick={submit}>
              저장하기
            </button>
          </footer>
        </div>
      ) : null}
    </>
  );
};

export default MyPageUpdate;
