/* eslint-disable */

import React, {useEffect} from 'react';
import { IoMdArrowBack } from 'react-icons/io';
import getUserInfo from '../../../../../Common/getUserInfo';
import ReviewDetailApi from '../../../../../../API/ReviewAPI/ReviewDetailApi'
import { BsFillStarFill } from 'react-icons/bs';
import { useState } from 'react';

function MyProposalReviewDetail(props) {

  const [reviewDetail, setReviewDetail] = useState()

  useEffect(async () => {
    const id = props.nowProposalId;
    const res = await ReviewDetailApi(id);
    if(res.status===200) {
      setReviewDetail(res.data);
    }
  }, [props.nowProposalId]);

  const userInfo = getUserInfo();

  const closeReviewDetailBox = () => {
    props.setReviewDetailBox(false);
  };

  return (
    <>
      {props.reviewDetailBox === true && reviewDetail ? (
        <div className="mypage-review-box">
          <div className="mypage-modal-box-header">
            <button
              type="submit"
              className="mypage-modal-box-btn"
              onClick={closeReviewDetailBox}
            >
              <IoMdArrowBack />
            </button>
            <h5 id="mypage-review-box-head">Review</h5>
          </div>
          <div>
            {[...Array(reviewDetail.starRating)].map(n => {
              return (
                <BsFillStarFill
                  className="mypage-review-list-star"
                  key={Math.random()}
                />
              );
            })}
          </div>
          <div className="mypage-review-content">{reviewDetail.content}</div>
        </div>
      ) : null}
    </>
  );
}

export default MyProposalReviewDetail;
