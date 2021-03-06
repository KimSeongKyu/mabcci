/* eslint-disable */
import React from 'react';
import '../../MyPageFollow/Follow.css';
import '../MySetting/MySetting.css';
import './MyProposal.css';
import {IoMdArrowBack} from 'react-icons/io';
import { useState } from 'react';
import ReviewWriteApi from '../../../../../../API/ReviewAPI/ReviewWriteApi';
import getUserInfo from '../../../../../Common/getUserInfo'

const MyProposalReview = (props) => {

  const userInfo = getUserInfo();

  const [review, setReview] = useState({
    content: '',
    starRating: '',
    id: 0,
  });

  const writeReivew = (e) => {
    setReview({
      ...review,
      content: e.target.value,
    });
  }

  const closeReviewBox = () => {
    props.setReviewBox(false)
  }

  const clickStar = (e) => {
    setReview({
      ...review,
      starRating: e.target.value,
    });
  }

  const submitReview = async () => {
    const data = {
      id: props.nowProposalId,
      starRating: review.starRating,
      content: review.content
    };
    const res = await ReviewWriteApi(data);
    if (res.status === 204) {
      window.location.replace(`/mypage/${userInfo.nickname}`);
    }
  }

  return (
    <>
      {props.reviewBox === true ? (
        <div className="mypage-review-box">
          <div className="mypage-modal-box-header">
            <button
              type="submit"
              className="mypage-modal-box-btn"
              onClick={closeReviewBox}
            >
              <IoMdArrowBack />
            </button>
            <h5 id="mypage-review-box-head">Review</h5>
          </div>

          <div className="mypage-review-content">
            <div className="mypage-review-star-box">
              <div className="star-rating">
                <input
                  type="radio"
                  id="5-stars"
                  name="rating"
                  value="5"
                  onClick={clickStar}
                />
                <label htmlFor="5-stars" className="star">
                  &#9733;
                </label>
                <input
                  type="radio"
                  id="4-stars"
                  name="rating"
                  value="4"
                  onClick={clickStar}
                />
                <label htmlFor="4-stars" className="star">
                  &#9733;
                </label>
                <input
                  type="radio"
                  id="3-stars"
                  name="rating"
                  value="3"
                  onClick={clickStar}
                />
                <label htmlFor="3-stars" className="star">
                  &#9733;
                </label>
                <input
                  type="radio"
                  id="2-stars"
                  name="rating"
                  value="2"
                  onClick={clickStar}
                />
                <label htmlFor="2-stars" className="star">
                  &#9733;
                </label>
                <input
                  type="radio"
                  id="1-star"
                  name="rating"
                  value="1"
                  onClick={clickStar}
                />
                <label htmlFor="1-star" className="star">
                  &#9733;
                </label>
              </div>
            </div>
            <textarea
              onChange={writeReivew}
              placeholder="????????? ??????????????? (?????? 100???)"
              id=""
              cols="40"
              rows="6"
              maxLength="113"
            ></textarea>
          </div>
          <button type="submit" className="btn-sm" onClick={submitReview}>
            Submit
          </button>
        </div>
      ) : null}
    </>
  );
};

export default MyProposalReview;
