/* eslint-disable */

import React from 'react';
import './MySetting.css';
import '../../MyPageFollow/Follow.css'
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

const MyPageUpdate = (props) => {

  const userInfo = getUserInfo();

  const closeUpdate = () => {
    props.setMyPageUpdate('none')
  }

  const updateMyInfo = async () => {
    const res = await MypageUpdateApi(myInfo, userInfo.nickname);
    console.log(res)
  }

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
    };

  return (
    <>
      {props.myPageUpdate !== 'none' ? (
        <div className="mypage-modal-container" />
      ) : null}
      {props.myPageUpdate !== 'none' ? (
        <div className="mypage-update-container">
          <header>
            <button type="submit" onClick={closeUpdate}>
              X
            </button>
          </header>

          <section>
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
          </section>

          <footer>
            <button className="btn-sm" type="submit" onClick={updateMyInfo}>
              회원정보수정
            </button>
          </footer>
        </div>
      ) : null}
    </>
  );
};

export default MyPageUpdate;
