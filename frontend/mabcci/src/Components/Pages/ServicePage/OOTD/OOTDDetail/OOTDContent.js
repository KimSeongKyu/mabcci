import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Pagination } from 'swiper/core';
import { AiOutlineHeart, AiFillHeart } from 'react-icons/ai';
import { baseUrl } from '../../../../../API/ApiUrl';
import { OOTDDetailApi } from '../../../../../API/OOTDAPI/OOTDDetailApi';
import 'swiper/swiper.min.css';
import 'swiper/components/pagination/pagination.min.css';
import getUserInfo from '../../../../Common/getUserInfo';
import OOTDDeleteApi from '../../../../../API/OOTDAPI/OOTDDeleteApi';

const OOTDContent = props => {
  const history = useHistory();
  const { ootdId, writerNickname, userInfo } = props;
  const [writer, setWriter] = useState({
    nickname: writerNickname,
    memberPicture: '',
  });
  const [detail, setDetail] = useState({
    id: ootdId,
    content: '',
    top: '',
    bottom: '',
    shoes: '',
    accessory: '',
    ootdPictures: [],
    views: '',
    hashtag: [],
    registeredTime: '',
    likeCount: '',
    likeStatus: '',
  });
  const [myLike, setMyLike] = useState();

  useEffect(async () => {
    const response = await OOTDDetailApi(detail.id);
    if (response.status === 200) {
      setDetail({ ...detail, ...response.detail });
      setWriter({ ...writer, memberPicture: response.memberPicture });
      setMyLike(response.likeStatus);
    }
  }, []);

  const ootdUpdateHandler = () => {
    const info = {
      id: detail.id,
      top: detail.top,
      bottom: detail.bottom,
      shoes: detail.shoes,
      accessory: detail.accessory,
      content: detail.content,
      picture: detail.ootdPictures,
      hashTag: detail.hashtag,
    };

    history.push({
      pathname: `/OOTDUpdate/${detail.id}/${writer.nickname}`,
      state: { info },
    });
  };

  const ootdDeleteHandler = () => {
    const response = OOTDDeleteApi(detail.id);
    window.location.replace('/OOTD');
  };

  const likeHandler = () => {
    setMyLike(!myLike);
  };

  return (
    <article className="detail-content">
      <section className="detail-info">
        <div className="detail-info-photo">
          <img src={writer.memberPicture} alt="UserPicture" width="70" />
        </div>
        <div className="detail-info-content">
          <p>{writer.nickname}</p>
          <p>
            {detail.registeredTime} views:{detail.views}
          </p>
          {userInfo.nickname === writer.nickname ? (
            <button type="button" onClick={ootdUpdateHandler}>
              수정
            </button>
          ) : null}
          {userInfo.nickname === writer.nickname ? (
            <button type="button" onClick={ootdDeleteHandler}>
              삭제
            </button>
          ) : null}
        </div>
      </section>
      <section className="detail-ootd-photo">
        <Swiper pagination className="detail-swiper-container">
          {detail.ootdPictures.map(picture => {
            return (
              <SwiperSlide className="detail-swiper-slide" key={picture}>
                <img src={baseUrl + picture} alt="OotdPhoto" />
              </SwiperSlide>
            );
          })}
        </Swiper>
      </section>
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
          {detail.likeCount}
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
        <div className="detail-ootd-hashtag">
          <p>
            {detail.hashtag === 0 &&
              detail.hashtag.map(hashtag => `#${hashtag} `)}
          </p>
        </div>
      </section>
    </article>
  );
};

export default OOTDContent;
