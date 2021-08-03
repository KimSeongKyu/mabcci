import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { AiOutlineHeart } from 'react-icons/ai';
import { OOTDDetailApi } from '../../../../../API/OOTDAPI/OOTDDetailApi';

const OOTDContentApi = () => {
  const { id, nickname } = useParams();

  const [user, setUser] = useState({
    nickname,
    userphoto: '사진',
  });

  const [detail, setDetail] = useState({
    id,
    content: '',
    top: '',
    bottom: '',
    shoes: '',
    accessory: '',
    picture: '',
    views: '',
    registeredTime: '',
    likeMembers: [],
  });

  useEffect(async () => {
    const response = await OOTDDetailApi(id);
    setDetail({ ...detail, ...response.detail });
  }, []);

  return (
    <article className="detail-content">
      <section className="detail-info">
        <div className="detail-info-photo">{user.userphoto}</div>
        <div className="detail-info-content">
          <p>{user.nickname}</p>
          <p>
            {detail.registeredTime} views:{detail.views}
          </p>
        </div>
      </section>
      <section className="detail-ootd-photo">{detail.picture}</section>
      <section className="detail-ootd">
        <div className="detail-ootd-like">
          <AiOutlineHeart className="detail-ootd-heart" />
          {detail.likeMembers.length}
        </div>
        <div className="detail-ootd-content">
          <p>{detail.content}</p>
        </div>
        <div className="detail-ootd-clothes">
          <div className="detail-ootd-clothes1">
            <p>Top</p>
            <p>Bottom</p>
            <p>Shoes</p>
            <p>Acc</p>
          </div>
          <div className="detail-ootd-clothes2">
            <p>{detail.top}</p>
            <p>{detail.bottom}</p>
            <p>{detail.shoes}</p>
            <p>{detail.accessory}</p>
          </div>
        </div>
      </section>
    </article>
  );
};

export default OOTDContentApi;
