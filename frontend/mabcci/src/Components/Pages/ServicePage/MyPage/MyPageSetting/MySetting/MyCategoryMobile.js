/* eslint-disable */

import React from 'react';
import { IoArrowBackCircle } from 'react-icons/io5';
import '../../../../AuthPage/Signup/Signup.css'
import 미니멀 from '../../../../../../Asset/Images/미니멀.png'
import 아메카지 from '../../../../../../Asset/Images/아메카지.png';
import 스트릿 from '../../../../../../Asset/Images/스트릿.png';
import 오피스 from '../../../../../../Asset/Images/오피스.png';
import 캐쥬얼 from '../../../../../../Asset/Images/캐쥬얼.png';
import 포멀 from '../../../../../../Asset/Images/포멀.png';

const MyCategoryMobile = props => {
  const goBack = () => {
    props.setMyPageUpdate('none');
    props.setMobileMenu(true);
  };

  const styleBtnClick = (e) => {
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
  }
  

  const submit = () => {
    console.log(props.myUpdateInfo);
  }

  return (
    <>
      {props.myPageUpdate === 'category' ? (
        <div className="mypage-moblie-container" />
      ) : null}
      {props.myPageUpdate === 'category' ? (
        <div className="mypage-mobile-menu mypage-mobile-update">
          <div className="mypage-mobile-menu-header">
            <button
              type="submit"
              className="mypage-mobile-menu-btn"
              onClick={goBack}
            >
              <IoArrowBackCircle />
            </button>
            <h3>카테고리 변경</h3>
          </div>
          <div className="signup-style-box ">
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
          </div>
          <button className="mypage-submit-btn mypage-category-submit-btn" type="submit" onClick={submit}>
            저장
          </button>
        </div>
      ) : null}
    </>
  );
};

export default MyCategoryMobile;
