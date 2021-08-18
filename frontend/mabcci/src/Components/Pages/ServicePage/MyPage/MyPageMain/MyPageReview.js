import React from 'react';
import { BsFillStarFill } from 'react-icons/bs';

function MyPageReview(props) {
  const 스타스타 = 'gkdlfn';

  return (
    <article className="mypage-review-list-box">
      <header className="mypage-review-list-header">
        <p>Review</p>
        <div style={{ fontSize: 12 }}>더보기</div>
      </header>
      <section className="mypage-review-list">
        <div>
          <BsFillStarFill className="mypage-review-list-star" />
          <BsFillStarFill className="mypage-review-list-star" />
          <BsFillStarFill className="mypage-review-list-star" />
          <BsFillStarFill className="mypage-review-list-star" />
          <BsFillStarFill className="mypage-review-list-star" />
        </div>
        <div className="mypage-review-comment">코멘트</div>
      </section>
    </article>
  );
}

export default MyPageReview;
