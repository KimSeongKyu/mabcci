import React from 'react';
import userphoto from './Images/userphoto.png';

const OOTDBottom = () => {
  return (
    <footer className="detail-bottom">
      <div className="detail-comments-title">
        <h5>Comments (3)</h5>
      </div>
      <div className="detail-comments">
        <div className="detail-comment">
          <div className="detail-comment-info">
            <div className="detail-comment-info-photo">
              <img src={userphoto} alt="UserImage" width="70" />
            </div>
            <div className="detail-comment-info-content">
              <p>Username</p>
              <p>5일</p>
              <button type="button">답글 달기</button>
              <button type="button">수정</button>
              <button type="button">삭제</button>
            </div>
          </div>
          <div className="detail-comment-content">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt Lorem ipsum dolor sit amet, consectetur
            adipiscing elit, sed do eiusmod tempor incididunt
          </div>
        </div>
      </div>
    </footer>
  );
};

export default OOTDBottom;
