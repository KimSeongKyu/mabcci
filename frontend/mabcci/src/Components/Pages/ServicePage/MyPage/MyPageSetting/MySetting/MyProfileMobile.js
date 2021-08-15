/* eslint-disable */

import React from 'react';
import { IoArrowBackCircle } from 'react-icons/io5';
import 기본프로필 from '../../../../../../Asset/Images/기본프로필.jpg'
import { BsXCircle } from "react-icons/bs";

const MyProfileMobile = props => {
  const goBack = () => {
    props.setMyPageUpdate('none');
    props.setMobileMenu(true);
  };

  const updateProfile = e => {
    const nowSelectProfile = e.target.files[0]
    const nowSelectProfileURL = URL.createObjectURL(nowSelectProfile);
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      picture: nowSelectProfileURL,
      updatePicture: nowSelectProfile,
    });
  }

  const initializeProfile = () => {
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      picture: null,
      updatePicture: null,
    });
  }

  const updateDescription = (e) => {
    props.setMyUpdateInfo({
      ...props.myUpdateInfo,
      description: e.target.value,
    });
  }

  const submit = () => {
    console.log(props.myUpdateInfo);
  }

  return (
    <>
      {props.myPageUpdate === 'profile' ? (
        <div className="mypage-moblie-container" />
      ) : null}
      {props.myPageUpdate === 'profile' ? (
        <article className="mypage-mobile-menu mypage-mobile-update">
          <header className="mypage-mobile-menu-header">
            <button
              type="submit"
              className="mypage-mobile-menu-btn"
              onClick={goBack}
            >
              <IoArrowBackCircle />
            </button>
            <h3>프로필 변경</h3>
          </header>
          <h5 className="mypage-mobile-profile-h5">사진 변경</h5>
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
              프로필 변경
            </label>
            <input
              type="file"
              id="update-profile"
              style={{ display: 'none' }}
              accept=".jpg,.jpeg,.png"
              onChange={updateProfile}
            />
          </section>
          <h5 className="mypage-mobile-profile-h5">프로필 소개 변경</h5>
          <section>
            <textarea
              name=""
              id=""
              cols="40"
              rows="10"
              value={props.myUpdateInfo.description}
              onChange={updateDescription}
            ></textarea>
          </section>
          <footer>
            <button
              className="mypage-submit-btn mypage-mobile-profie-btn"
              type="submit"
              onClick={submit}
            >
              저장
            </button>
          </footer>
        </article>
      ) : null}
    </>
  );
};

export default MyProfileMobile;
