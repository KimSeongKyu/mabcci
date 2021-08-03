import React, { useState, useRef, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import OOTDFeedApi from '../../../../../API/OOTDAPI/OOTDMainApi';
import { OOTDAll } from '../../../../../Redux/Actions/OOTDAction';

const OOTDFeed = ({ filter, page, setPage }) => {
  const feeds = useSelector(state => state.OotdReducer.ootd);
  const dispatch = useDispatch();
  const fetchMoreTrigger = useRef(null);

  // 관찰 요소가 보이면 page + 1
  const fetchMoreObserver = new IntersectionObserver(([{ isIntersecting }]) => {
    if (isIntersecting) setPage(newPage => newPage + 1);
  });

  // api 요청 데이터 수신
  useEffect(async () => {
    const response = await OOTDFeedApi(filter, page);
    dispatch(OOTDAll(response.data));
  }, [page, filter]);

  useEffect(() => {
    setPage(1);
  }, [filter]);

  useEffect(() => {
    fetchMoreObserver.observe(fetchMoreTrigger.current);
    return () => {
      fetchMoreObserver.unobserve(fetchMoreTrigger.current);
    };
  }, []);

  const showFeeds = () => {
    return feeds.map(({ id, nickname, picture, hashTag }, index) => {
      const lastEl = index === feeds.length - 1;
      return (
        <div className="card" key={id} ref={lastEl ? fetchMoreTrigger : null}>
          <h3 className="nickname">{nickname}</h3>
          <Link to={`/OOTD/${id}/${nickname}`}>
            <img src={picture} alt="" />
            <i className="fas fa-heartbeat" />
            <span>likenum</span>
            <p>{hashTag}</p>
          </Link>
        </div>
      );
    });
  };

  return <div className="card-columns ootd-feed">{showFeeds()}</div>;
};

export default OOTDFeed;
