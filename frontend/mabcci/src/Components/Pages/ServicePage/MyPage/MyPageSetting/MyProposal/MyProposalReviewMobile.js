/* eslint-disable */

import React, { useState } from 'react';
import '../MyPageSetting.css';
import { IoArrowBackCircle } from 'react-icons/io5';
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import 아라찌 from '../../MyPageMain/images/다운로드.jfif';

const MyPageProposalReviewMobile = () => {
  const history = useHistory();

  const userInfo = JSON.parse(localStorage.getItem('userInfo'));

  const [review, setReview] = useState({
    content: '',
    star: 0,
  });

  const writeReivew = e => {
    setReview({
      ...review,
      content: e.target.value,
    });
  };

  const clickStar = e => {
    setReview({
      ...review,
      star: e.target.value,
    });
  };

  const submitReview = () => {
    console.log(review);
  };

  const goBack = () => {
    history.push(`/mypage/menu/proposal/${userInfo.nickname}`);
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
        <h3>Review</h3>
      </div>
      <div className="mypage-review-content mypage-review-content-mobile">
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
          placeholder="리뷰를 남겨주세요"
          id=""
          cols="40"
          rows="6"
        />
      </div>
      <button type="submit" className="btn-sm mypage-review-btn" onClick={submitReview}>
        Submit
      </button>
    </div>
  );
};

export default MyPageProposalReviewMobile;
