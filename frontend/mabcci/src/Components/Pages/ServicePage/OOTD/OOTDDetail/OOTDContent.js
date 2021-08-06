import React from 'react';
import { AiOutlineHeart } from 'react-icons/ai';
import userphoto from './Images/userphoto.png';
import ootdphoto from './Images/ootdphoto.jpg';

const OOTDContent = () => {
  return (
    <article className="detail-content">
      <section className="detail-info">
        <div className="detail-info-photo">
          <img src={userphoto} alt="UserImage" width="70" />
        </div>
        <div className="detail-info-content">
          <p>김고은</p>
          <p>2021.07.29 views:50</p>
          <button type="button">수정</button>
          <button type="button">삭제</button>
        </div>
      </section>
      <section className="detail-ootd-photo">
        <img src={ootdphoto} alt="OotdPhoto" />
      </section>
      <section className="detail-ootd">
        <div className="detail-ootd-like">
          <AiOutlineHeart className="detail-ootd-heart" /> 23
        </div>
        <div className="detail-ootd-content">
          <p>오늘의 OOTD입니다.</p>
        </div>
        <div className="detail-ootd-clothes">
          <div className="detail-ootd-clothes1">
            <p>Top</p>
            <p>Bottom</p>
            <p>Shoes</p>
            <p>Acc</p>
          </div>
          <div className="detail-ootd-clothes2">
            <p>셔링 스퀘어 패드티</p>
            <p>머메라인 밴딩 맥시 롱스커트</p>
            <p>새틴 슬림 스틸레토힐</p>
            <p>인터로킹 G 진주 귀걸이</p>
          </div>
        </div>
      </section>
    </article>
  );
};

export default OOTDContent;
