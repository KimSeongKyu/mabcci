import React, { useEffect, useState } from 'react';

import { BsFillStarFill } from 'react-icons/bs';
import ReviewListReadApi from '../../../../../API/ReviewAPI/ReviewListReadApi';
import ReviewEntireListReadApi from '../../../../../API/ReviewAPI/ReviewEntireListReadApi';
import getUserInfo from '../../../../Common/getUserInfo';
import 기본이미지 from '../../../../../Asset/Images/기본프로필.jpg';
import { baseUrl } from '../../../../../API/ApiUrl';

function MyPageReview(props) {
  const userInfo = getUserInfo();

  const [ReviewList, setReviewList] = useState();

  const [entireReviewList, setEntireReviewList] = useState();

  const [entireReviewBox, setEntireReviewBox] = useState(false);

  useEffect(async () => {
    const entireReviewListRes = await ReviewEntireListReadApi(
      userInfo.nickname,
    );
    if (entireReviewListRes.status === 200) {
      setEntireReviewList(entireReviewListRes.data);
    }
    const reviewListRes = await ReviewListReadApi(userInfo.nickname);
    if (reviewListRes.status === 200) {
      setReviewList(reviewListRes.data);
    }
    console.log(entireReviewListRes);
  }, []);

  const openEntireReview = () => {
    setEntireReviewBox(!entireReviewBox);
  };

  return (
    <article className="mypage-review-list-box">
      <header className="mypage-review-list-header">
        <p>Review</p>

        <button
          onClick={openEntireReview}
          style={{ fontSize: 12, color: 'black' }}
          type="submit"
        >
          {entireReviewBox === false ? '더보기' : '접기'}
        </button>
      </header>
      {entireReviewList && ReviewList ? (
        <div>
          <div>
            {ReviewList.map(review => {
              return (
                <section className="mypage-review-list" key={review.content}>
                  <div>
                    {[...Array(review.starRating)].map(n => {
                      return (
                        <BsFillStarFill
                          className="mypage-review-list-star"
                          key={Math.random()}
                        />
                      );
                    })}
                  </div>
                  <div className="mypage-review-comment">
                    {review.content.slice(0, 15)}
                  </div>
                </section>
              );
            })}
          </div>
          <div>
            {entireReviewList.map(review => {
              return (
                <section
                  className="mypage-entire-review-list"
                  key={review.content}
                >
                  <img
                    src={
                      review.memberPicture == null
                        ? 기본이미지
                        : baseUrl + review.memberPicture
                    }
                    alt="하이"
                  />
                  <div style={{ display: 'flex' }}>
                    <h5>{review.nickname}</h5>
                    {review.createdDate}
                  </div>

                  <div>
                    {[...Array(review.starRating)].map(n => {
                      return (
                        <BsFillStarFill
                          className="mypage-review-list-star"
                          key={Math.random()}
                        />
                      );
                    })}
                  </div>
                  <div className="mypage-review-comment">{review.content}</div>
                </section>
              );
            })}
          </div>
        </div>
      ) : null}
      )
    </article>
  );
}

export default MyPageReview;
