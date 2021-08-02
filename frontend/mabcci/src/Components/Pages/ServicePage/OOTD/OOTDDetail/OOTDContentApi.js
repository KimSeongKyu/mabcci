import React from 'react';
import { useParams, useState, useEffect } from 'react-router-dom';
import { AiOutlineHeart } from 'react-icons/ai';
import { OOTDDetailApi } from '../../../../../API/OOTDAPI/OOTDDetailApi';

const OOTDContent = () => {
  const { id, nickname } = useParams();
  const InitialUser = {
    nickname,
    userphoto: '',
  };
  const IntialDetail = {
    id,
    registeredTime: '',
    views: '',
    picture: '',
    likeMembers: [],
    content: '',
    top: '',
    bottom: '',
    shoes: '',
    accessory: '',
  };

  const { user, setUser } = useState(InitialUser);
  const { detail, setDetail } = useState(IntialDetail);

  useEffect(async () => {
    const reponse = await OOTDDetailApi(id);
    setDetail(detail, ...reponse.data);
  }, [user, detail]);

  return (
    <article className="detail-content">
      <section className="detail-info">
        <div className="detail-info-photo">
          <img src={user.userphoto} alt="UserImage" width="70" />
        </div>
        <div className="detail-info-content">
          <p>{nickname}</p>
          <p>
            {detail.registeredTime} views:{detail.views}
          </p>
        </div>
      </section>
      <section className="detail-ootd-photo">
        <img src={detail.picture} alt="OotdPhoto" />
      </section>
      <section className="detail-ootd">
        <div className="detail-ootd-like">
          <AiOutlineHeart className="detail-ootd-heart" /> 23
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

export default OOTDContent;
