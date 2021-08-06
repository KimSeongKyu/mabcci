import React from 'react';
import { IoMdSend } from 'react-icons/io';
import userphoto from './Images/userphoto.png';

export const SingleComment = () => {
  return (
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
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt Lorem ipsum dolor sit amet, consectetur adipiscing
        elit, sed do eiusmod tempor incididunt
      </div>
    </div>
  );
};

const OOTDBottom = () => {
  return (
    <footer className="detail-bottom">
      <div className="detail-comments-title">
        <h5>Comments (3)</h5>
      </div>
      <div className="detail-comments">
        <SingleComment />
      </div>
      <div className="detail-comment-write">
        <input
          className="detail-comment-input"
          type="text"
          placeholder="댓글 쓰기"
        />
        <IoMdSend className="detail-comment-send" size="30" />
      </div>
    </footer>
  );
};

export default OOTDBottom;
