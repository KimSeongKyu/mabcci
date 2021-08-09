import React, { useState, useRef, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import Masonry from 'react-masonry-css';
import OOTDFeedApi from '../../../../../API/OOTDAPI/OOTDMainApi';
import {
  OOTDAll,
  OOTDFiltering,
  OOTDClean,
} from '../../../../../Redux/Actions/OOTDAction';

const OOTDFeed = ({
  filter,
  page,
  searching,
  setPage,
  filtering,
  setFiltering,
}) => {
  const feeds = useSelector(state => state.OotdReducer.ootd);
  const [loading, setLoading] = useState(false);
  const [end, setEnd] = useState(false);
  const [searchCategory, setSearchCategory] = useState('tag');
  const dispatch = useDispatch();
  const fetchMoreTrigger = useRef(null);
  const breakpointColumnsObj = {
    default: 3,
    576: 2,
  };

  // 관찰 요소가 보이면 page + 1
  const fetchMoreObserver = new IntersectionObserver(
    ([{ isIntersecting }]) => {
      if (isIntersecting && !loading) setPage(newPage => newPage + 1);
    },
    { thredhold: 1 },
  );

  // api 요청 데이터 수신
  useEffect(async () => {
    if (end && !filtering) return;
    if (end && filtering) setEnd(false);

    setLoading(true);
    const response = await OOTDFeedApi(filter, page);
    if (response.data.message === 'no more data') {
      return setEnd(true);
    }
    if (!filtering) {
      dispatch(OOTDAll(response.data.ootd));
    } else {
      dispatch(OOTDFiltering(response.data.ootd));
      setFiltering(false);
    }
    setLoading(false);
  }, [page, filter]);

  useEffect(() => {
    if (fetchMoreTrigger.current && !loading) {
      fetchMoreObserver.observe(fetchMoreTrigger.current);
      console.log('here', fetchMoreTrigger.current);
      return () => {
        if (fetchMoreTrigger.current) {
          fetchMoreObserver.unobserve(fetchMoreTrigger.current);
        }
        dispatch(OOTDClean());
      };
    }
  }, [feeds, loading]);

  const onCheckSearchCategory = e => {
    const categoryName = e.target.name;
    setSearchCategory(categoryName);
  };

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

  return (
    <div className="ootd-feeds">
      <div className={`ootd-search ${searching ? 'active' : ''}`}>
        <input
          type="text"
          placeholder="해시태그 혹은 사용자 이름으로 검색하세요."
        />
        <div className="ootd-search-category">
          <h5>
            <button
              type="button"
              className={`${searchCategory === 'tag' ? 'active' : ''}`}
              name="tag"
              onClick={onCheckSearchCategory}
            >
              해시태그
            </button>
          </h5>
          <h5>
            <button
              type="button"
              className={`${searchCategory === 'user' ? 'active' : ''}`}
              name="user"
              onClick={onCheckSearchCategory}
            >
              사용자
            </button>
          </h5>
        </div>
        <h1>검색창</h1>
      </div>
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
