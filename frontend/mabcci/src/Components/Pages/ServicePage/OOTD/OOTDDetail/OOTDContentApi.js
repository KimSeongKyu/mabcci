import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import { AiOutlineHeart, AiFillHeart } from 'react-icons/ai';
import { OOTDDetailApi } from '../../../../../API/OOTDAPI/OOTDDetailApi';

const OOTDContentApi = () => {
  const history = useHistory();
  const myInfo = JSON.parse(localStorage.getItem('userInfo'));
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
    hashtag: [],
    registeredTime: '',
    likeMembers: [],
  });

  const [myLike, setMyLike] = useState(false);

  useEffect(async () => {
    const response = await OOTDDetailApi(id);
    setDetail({ ...detail, ...response.detail });
  }, []);

  const ootdUpdateHandler = () => {
    //  history.push({
    //   pathname: /OOTDUpdate/${detail.id}/${user.nickname},
    //   state: { detail },
    // });
  };

  const likeHandler = () => {
    setMyLike(!myLike);
  };

  return (
    <article className="detail-content">
      <section className="detail-info">
        <div className="detail-info-photo">{user.userphoto}</div>
        <div className="detail-info-content">
          <p>{user.nickname}</p>
          <p>
            {detail.registeredTime} views:{detail.views}
          </p>
          {myInfo.nickname === user.nickname ? (
            <button type="button" onClick={ootdUpdateHandler}>
              수정
            </button>
          ) : null}
          {myInfo.nickname === user.nickname ? (
            <button type="button">삭제</button>
          ) : null}
        </div>
      </section>
      <section className="detail-ootd-photo">{detail.picture}</section>
      <section className="detail-ootd">
        <div className="detail-ootd-like">
          {myLike ? (
            <AiFillHeart
              className="detail-ootd-heart"
              size="20"
              onClick={likeHandler}
            />
          ) : (
            <AiOutlineHeart
              className="detail-ootd-heart"
              size="20"
              onClick={likeHandler}
            />
          )}
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
