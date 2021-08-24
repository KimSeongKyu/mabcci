import React, { useEffect, useState } from 'react';
import { BsFillStarFill } from 'react-icons/bs';

import { IoArrowForwardCircle, IoArrowBackCircle } from 'react-icons/io5';
import ReviewListReadApi from '../../../../../API/ReviewAPI/ReviewListReadApi';
import ReviewEntireListReadApi from '../../../../../API/ReviewAPI/ReviewEntireListReadApi';
import getUserInfo from '../../../../Common/getUserInfo';
import 기본이미지 from '../../../../../Asset/Images/기본프로필.jpg';
import { baseUrl } from '../../../../../API/ApiUrl';

function MyPageReview(props) {
  const userInfo = getUserInfo();

  const average = arr => arr.reduce((p, c) => p + c, 0) / arr.length;

  const [ReviewList, setReviewList] = useState();

  const [entireReviewList, setEntireReviewList] = useState();

  const [entireReviewBox, setEntireReviewBox] = useState(false);

  const averageStar = () => {
    const arr = [];
    entireReviewList.map(data => {
      arr.push(data.starRating);
      return null;
    });
    const answer = average(arr);
    return answer.toString();
  };

  useEffect(async () => {
    const entireReviewListRes = await ReviewEntireListReadApi(
      props.myInfo.nickname,
    );
    if (entireReviewListRes.status === 200) {
      setEntireReviewList(entireReviewListRes.data);
    }
    const reviewListRes = await ReviewListReadApi(props.myInfo.nickname);
    if (reviewListRes.status === 200) {
      setReviewList(reviewListRes.data);
    }
  }, []);

  const openEntireReview = () => {
    setEntireReviewBox(!entireReviewBox);
  };

  const [nowPage, setNowPage] = useState(0);
  const [reviewArr, setReivewArr] = useState([0, 1, 2, 3, 4]);

  const remainReview = () => {
    return entireReviewList.length % 5;
  };

  const pageDown = () => {
    setNowPage(nowPage - 5);
    const pointNumber = nowPage - 5;
    const tempArr = [];
    const defaultNumber = 5;
    for (let number = 0; number < defaultNumber; number += 1) {
      tempArr.push(pointNumber + number);
    }
    setReivewArr(tempArr);
  };

  const pageUp = () => {
    setNowPage(nowPage + 5);
    let defaultNumber = 0;
    if (nowPage > entireReviewList.length - 10) {
      defaultNumber = remainReview();
    } else {
      defaultNumber = 5;
    }
    const pointNumber = nowPage + 5;
    const tempArr = [];
    for (let number = 0; number < defaultNumber; number += 1) {
      tempArr.push(pointNumber + number);
    }
    setReivewArr(tempArr);
  };

  return (
    <article className="mypage-review-list-box">
      <header className="mypage-review-list-header">
        <p>Review</p>
        {entireReviewList && entireReviewList.length > 0 ? (
          <button
            onClick={openEntireReview}
            style={{ fontSize: 12, color: 'black' }}
            type="submit"
          >
            {entireReviewBox === false ? '자세히보기' : '접기'}
          </button>
        ) : null}
      </header>
      {entireReviewList && ReviewList ? (
        <div>
          <div className="mypage-star-average">
            {entireReviewList.length > 0 ? (
              <header style={{ fontSize: 'x-large' }}>
                평균별점
                <h1>
                  <BsFillStarFill className="mypage-review-title-star" />
                  {averageStar().slice(0, 4)}
                </h1>
              </header>
            ) : (
              <p style={{ color: 'gray' }}>등록된 리뷰가 없습니다.</p>
            )}
          </div>
          {entireReviewBox === false ? (
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
                      {review.content.slice(0, 30)}
                      {review.content.length > 30 ? <>....</> : null}
                    </div>
                  </section>
                );
              })}
            </div>
          ) : (
            <div>
              {reviewArr.map(i => {
                return (
                  <section
                    className="mypage-entire-review-list"
                    id="mypage-entire-review-list-mobile"
                    key={entireReviewList[i].content}
                  >
                    <div className="mypage-review-userInfo">
                      <img
                        src={
                          entireReviewList[i].memberPicture == null
                            ? 기본이미지
                            : baseUrl + entireReviewList[i].memberPicture
                        }
                        alt="하이"
                      />
                      <div style={{ display: 'flex' }}>
                        <div>
                          <h5 className="mypage-review-nickname">
                            {entireReviewList[i].nickname}
                          </h5>
                          <div
                            style={{ fontSize: 'x-small', textAlign: 'start' }}
                          >
                            {entireReviewList[i].createdDate.slice(0, 10)}
                          </div>
                        </div>
                      </div>

                      <div>
                        {[...Array(entireReviewList[i].starRating)].map(n => {
                          return (
                            <BsFillStarFill
                              className="mypage-review-list-star"
                              key={Math.random()}
                            />
                          );
                        })}
                      </div>
                    </div>
                    <div className="mypage-entire-review-comment mypage-review-comment">
                      {entireReviewList[i].content}
                    </div>
                  </section>
                );
              })}
              <footer style={{ display: 'flex', justifyContent: 'center' }}>
                <IoArrowBackCircle
                  style={{ marginRight: '10px' }}
                  onClick={pageDown}
                  className={
                    nowPage >= 5
                      ? 'mypage-review-page-btn'
                      : 'mypage-review-page-disabled-btn'
                  }
                />
                <IoArrowForwardCircle
                  onClick={pageUp}
                  className={
                    nowPage < entireReviewList.length - 5
                      ? 'mypage-review-page-btn'
                      : 'mypage-review-page-disabled-btn'
                  }
                />
              </footer>
            </div>
          )}
        </div>
      ) : null}
    </article>
  );
}

export default MyPageReview;
