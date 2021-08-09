/* eslint-disable */
import '../../MyPageFollow/Follow.css';
import '../../MyPageSetting/MyPageSetting.css';
import React, {useState} from 'react';
import 아라찌 from '../../MyPageMain/images/다운로드.jfif';
import MyProposalReview from './MyProposalReview';

const MyProposalList = props => {
  const closeProposalBox = () => {
    props.setProposalBox(false);
  };

  const [reviewBox, setReviewBox] = useState(false);

  const openReviewBox = () => {
    setReviewBox(true)
  }

  return (
    <>
      <MyProposalReview reviewBox={reviewBox} setReviewBox={setReviewBox} />
      {props.proposalBox === true ? (
        <div className="mypage-modal-container" />
      ) : null}
      {props.proposalBox === true ? (
        <div className="mypage-modal-box">
          <div className="mypage-modal-box-header">
            <h5>Proposal List</h5>
            <button
              type="submit"
              className="mypage-modal-box-btn"
              onClick={closeProposalBox}
            >
              X
            </button>
          </div>
          <div className="mypage-modal-box-content">
            <img src={아라찌} alt="하이" />
            <div className="mypage-proposal-box-info">
              <h5>제안서 제목</h5>
              <p>Mabcci 고은쨩</p>
              <p>2021.08.09</p>
            </div>

            <button type="submit" onClick={openReviewBox}>Review</button>
          </div>
        </div>
      ) : null}
    </>
  );
};

export default MyProposalList;
