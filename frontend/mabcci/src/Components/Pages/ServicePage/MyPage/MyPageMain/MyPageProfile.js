import React from 'react';
import './MyPage.css';
import 아라찌 from './images/다운로드.jfif';

const MyPageProfile = () => {
  return (
    <>
      <div className="mypage-profile-box">
        <div>
          <img src={아라찌} alt="" />
        </div>
        <div className="mypage-info-box">
          <div id="mypage-web-nickname">
            <h3>닉네임이다</h3>
            <button type="submit">프로필편집</button>
          </div>
          <div id="mypage-mobile-nickname">
            <h5>닉네임이다</h5>
          </div>
          <div>
            <button type="submit">팔로워 {}명</button>
            <button type="submit">팔로잉 {}명</button>
          </div>
          <div id="mypage-mobile-category">
            <h5>#아메카지</h5>
            <h5>#포멀</h5>
            <h5>#스트릿</h5>
          </div>
        </div>
        <div className="mypage-blank" />
      </div>
      <div className="mypage-introduce-box">
        <p>Introduce</p>
        <div>인생은 스트릿하게🤘</div>
      </div>
    </>
  );
};

export default MyPageProfile;
