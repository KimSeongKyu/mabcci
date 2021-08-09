import React from 'react';
import '../MyPageSetting.css';
import { IoArrowBackCircle } from 'react-icons/io5';
import { useHistory } from 'react-router-dom';
import 아라찌 from '../../MyPageMain/images/다운로드.jfif';

const MyPageProposalListMobile = () => {
  const history = useHistory();

  const userInfo = JSON.parse(localStorage.getItem('userInfo'));

  const goBack = () => {
    history.push(`/mypage/menu/${userInfo.nickname}`);
  };

  const goReview = () => {
    history.push(`/mypage/menu/review/${userInfo.nickname}`);
  };

  return (
    <div className="mypage-mobile-menu">
      <div className="mypage-mobile-menu-header">
        <button
          type="submit"
          className="mypage-mobile-menu-btn"
          onClick={goBack}
        >
          <IoArrowBackCircle />
        </button>
        <h3>Proposal List</h3>
      </div>
      <div className="mypage-modal-box-content mypage-mobile-proposal-box">
        <img src={아라찌} alt="하이" />
        <div className="mypage-proposal-box-info">
          <h5>제안서 제목</h5>
          <p>Mabcci 고은쨩</p>
          <p>2021.08.09</p>
        </div>

        <button type="submit" onClick={goReview}>
          Review
        </button>
      </div>
    </div>
  );
};

export default MyPageProposalListMobile;
