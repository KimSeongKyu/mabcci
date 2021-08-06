import React, { useState, useRef, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import Masonry from 'react-masonry-css';
import OOTDFeedApi from '../../../../../API/OOTDAPI/OOTDMainApi';
import { OOTDAll, OOTDClean } from '../../../../../Redux/Actions/OOTDAction';

const OOTDFeed = ({ filter, page, setPage }) => {
  const feeds = useSelector(state => state.OotdReducer.ootd);
  const [loading, setLoading] = useState(false);
  const [end, setEnd] = useState(false);
  const dispatch = useDispatch();
  const fetchMoreTrigger = useRef(null);
  const breakpointColumnsObj = {
    default: 3,
    1100: 3,
    700: 3,
    500: 3,
  };

  // 관찰 요소가 보이면 page + 1
  const fetchMoreObserver = new IntersectionObserver(
    ([{ isIntersecting }]) => {
      console.log('관찰됨', fetchMoreTrigger.current);
      if (isIntersecting && !loading) setPage(newPage => newPage + 1);
    },
    { thredhold: 1 },
  );

  // api 요청 데이터 수신
  useEffect(async () => {
    if (end) return;
    setLoading(true);
    const response = await OOTDFeedApi(filter, page);
    if (response.status === 204) {
      return setEnd(true);
    }
    dispatch(OOTDAll(response.data.ootd));
    setLoading(false);
    return () => {
      dispatch(OOTDClean());
    };
  }, [page, filter]);

  useEffect(() => {
    if (fetchMoreTrigger.current && !loading) {
      fetchMoreObserver.observe(fetchMoreTrigger.current);
      console.log('here', fetchMoreTrigger.current);
      return () => {
        if (fetchMoreTrigger.current) {
          fetchMoreObserver.unobserve(fetchMoreTrigger.current);
        }
      };
    }
  }, [feeds, loading]);

  const showFeeds = () => {
    console.log('실행 showFeeds');
    return feeds.map(({ id, nickname, picture, hashTag }, index) => {
      const lastEl = index === feeds.length - 1;
      return (
        <div className="card" key={id} ref={lastEl ? fetchMoreTrigger : null}>
          <h3 className="nickname">
            {nickname}
            {page}
          </h3>
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

  return (
    <div>
      <Masonry
        breakpointCols={breakpointColumnsObj}
        className="my-masonry-grid ootd-feed"
        columnClassName="my-masonry-grid_column"
      >
        {showFeeds()}
      </Masonry>
      <h1>{end ? '컨텐츠가 더이상 없습니다' : ''}</h1>
    </div>
  );
};

export default OOTDFeed;
