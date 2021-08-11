import React, { useState, useRef, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import Masonry from 'react-masonry-css';
import OOTDFeedApi from '../../../../../API/OOTDAPI/OOTDMainApi';
import { baseUrl } from '../../../../../API/ApiUrl';
import {
  OOTDAll,
  OOTDFiltering,
} from '../../../../../Redux/Actions/OOTDAction';

const OOTDFeed = ({ page, searching, setPage, filtering, setFiltering }) => {
  const feeds = useSelector(state => state.OotdReducer.ootd);
  const filterState = useSelector(state => state.OotdReducer.filter);
  const [loading, setLoading] = useState(false);
  const [maxPage, setMaxPage] = useState(0);
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
    if (page > maxPage && !filtering) {
      return;
    }

    setLoading(true);

    try {
      const { nickname } = JSON.parse(localStorage.getItem('userInfo'));
      const response = await OOTDFeedApi(filterState, page, nickname);
      const { totalPages, ootdList } = response.data;
      setMaxPage(totalPages);
      if (!filtering) {
        dispatch(OOTDAll(ootdList));
      } else {
        dispatch(OOTDFiltering(ootdList));
        setFiltering(false);
      }
    } catch (error) {
      console.log('error');
    }
    setLoading(false);
  }, [page, filterState]);

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

  const onCheckSearchCategory = e => {
    const categoryName = e.target.name;
    setSearchCategory(categoryName);
  };

  const showFeeds = () => {
    return feeds.map(
      ({ id, nickname, picture, hashtags, likeCount }, index) => {
        const lastEl = index === feeds.length - 1;
        return (
          <div className="card" key={id} ref={lastEl ? fetchMoreTrigger : null}>
            <h3 className="nickname">{nickname}</h3>
            <Link to={`/OOTD/${id}/${nickname}`}>
              <img src={baseUrl + picture} alt="" />
              <i className="fas fa-heartbeat" />
              <span>{likeCount}</span>
              <p>
                {hashtags.map(tag => {
                  return `#${tag}`;
                })}
              </p>
            </Link>
          </div>
        );
      },
    );
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
      <h1>{loading ? <div className="loader">Loading...</div> : ''}</h1>
    </div>
  );
};

export default OOTDFeed;
