import React, { useState, useRef, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link, useHistory } from 'react-router-dom';
import Masonry from 'react-masonry-css';
import _ from 'lodash';
import {
  OOTDFeedApi,
  SearchListApi,
  SearchedOOTDFeedApi,
} from '../../../../../API/OOTDAPI/OOTDMainApi';
import { baseUrl } from '../../../../../API/ApiUrl';
import {
  OOTDAll,
  OOTDFiltering,
  OOTDFilterState,
} from '../../../../../Redux/Actions/OOTDAction';
import getUserInfo from '../../../../Common/getUserInfo';
import 기본프로필 from '../../../../../Asset/Images/기본프로필.jpg';

const OOTDFeed = ({
  page,
  searching,
  setPage,
  filtering,
  searchResult,
  searchInput,
  setFiltering,
  keyword,
  setKeyword,
  setSearching,
  setSearchResult,
  setSearchInput,
}) => {
  const feeds = useSelector(state => state.OotdReducer.ootd);
  const filterState = useSelector(state => state.OotdReducer.filter);
  const [loading, setLoading] = useState(false);
  const [maxPage, setMaxPage] = useState(0);
  const [searchCategory, setSearchCategory] = useState('hashtag');
  const [recentKeywords, setRecentKeywords] = useState(
    JSON.parse(
      localStorage.getItem('keywords') || '{"hashtag":[], "member":[]}',
    ),
  );
  const dispatch = useDispatch();
  const fetchMoreTrigger = useRef(null);
  const searchEl = useRef();
  const history = useHistory();
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

  const fetchOOTDFeed = async hashtag => {
    if (page > maxPage && !filtering) {
      return;
    }

    setLoading(true);
    if (hashtag || keyword) {
      try {
        dispatch(OOTDFilterState('search'));
        const word = hashtag || keyword;
        console.log(word);
        const response = await SearchedOOTDFeedApi(word, page);
        const { ootdList, totalPages } = response.data;
        setMaxPage(totalPages);
        if (!filtering) {
          dispatch(OOTDAll(ootdList));
        } else {
          dispatch(OOTDFiltering(ootdList));
          setFiltering(false);
        }
        setLoading(false);
        return;
      } catch (error) {
        console.log(error);
      }
    }

    try {
      const { nickname } = getUserInfo();
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
  };

  const handleCloseSearch = e => {
    if (!searchEl.current) return;
    if (
      e.target.className !== 'fas fa-search fa-lg' &&
      searchEl.current.className === 'ootd-search active' &&
      !searchEl.current.contains(e.target)
    ) {
      setSearching(false);
    }
  };

  useEffect(async () => {
    fetchOOTDFeed();
  }, [page, filterState]);

  useEffect(() => {
    if (fetchMoreTrigger.current && !loading) {
      fetchMoreObserver.observe(fetchMoreTrigger.current);
      return () => {
        if (fetchMoreTrigger.current) {
          fetchMoreObserver.unobserve(fetchMoreTrigger.current);
        }
      };
    }
  }, [feeds, loading]);

  useEffect(() => {
    window.addEventListener('click', handleCloseSearch);
    return () => {
      window.removeEventListener('click', handleCloseSearch);
    };
  }, []);

  useEffect(() => {
    localStorage.setItem('keywords', JSON.stringify(recentKeywords));
  }, [recentKeywords]);

  const onCheckSearchCategory = e => {
    const categoryName = e.target.name;
    setSearchCategory(categoryName);
    setSearchResult([]);
    setSearchInput('');
  };

  const onSearchKeywordList = async e => {
    const word = e.target.value;
    setSearchInput(word);
    // setFiltering(true);
    if (word.length < 1) return;
    const response = await SearchListApi(word, searchCategory);
    let searchResultList;
    if (response.data.hashtags) {
      searchResultList = response.data.hashtags;
    }
    if (response.data.members) {
      searchResultList = response.data.members;
    }
    setSearchResult(searchResultList);
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

  const goMemberPage = nickname => {
    history.push(`/mypage/${nickname}`);
  };

  const searchOOTD = result => {
    const newKeyword = {
      id: Date.now(),
      result,
    };
    setRecentKeywords({
      ...recentKeywords,
      hashtag: _.uniqBy([...recentKeywords.hashtag, newKeyword], 'result'),
    });

    setKeyword(result);
    setFiltering(true);
    setPage(0);
    setSearchInput('');
    setSearching(!searching);
    setSearchResult([]);
    fetchOOTDFeed(result);
  };

  const showSearchResult = () => {
    if (searchResult.length === 0) {
      return (
        <div>
          <p>최근 검색 기록</p>
          {recentKeywords.hashtag.map(item => {
            return (
              <div className="result-hashtag" key={item.id}>
                <button
                  type="button"
                  onClick={() => {
                    searchOOTD(item.result);
                  }}
                >
                  <p># {item.result}</p>
                </button>
              </div>
            );
          })}
        </div>
      );
    }
    const dataType = typeof searchResult[0];
    if (dataType === 'string') {
      return searchResult.map(result => {
        return (
          <div className="result-hashtag" key={result}>
            <button
              type="button"
              onClick={() => {
                searchOOTD(result);
              }}
            >
              <p># {result}</p>
            </button>
          </div>
        );
      });
    }
    if (dataType === 'object') {
      return searchResult.map(result => {
        return (
          <div key={result.nickname}>
            <button
              type="button"
              className="result-member"
              onClick={() => {
                searchOOTD(result.nickname);
                goMemberPage(result.nickname);
              }}
            >
              <img
                src={result.picture ? baseUrl + result.picture : 기본프로필}
                alt=""
              />
              <p>{result.nickname}</p>
            </button>
          </div>
        );
      });
    }
  };

  return (
    <div className="ootd-feeds">
      <div
        className={`ootd-search ${searching ? 'active' : ''}`}
        ref={searchEl}
      >
        <input
          type="text"
          placeholder="해시태그 혹은 사용자 이름으로 검색하세요."
          value={searchInput}
          onChange={onSearchKeywordList}
        />
        <div className="ootd-search-category">
          <h5>
            <button
              type="button"
              className={`${searchCategory === 'hashtag' ? 'active' : ''}`}
              name="hashtag"
              onClick={onCheckSearchCategory}
            >
              해시태그
            </button>
          </h5>
          <h5>
            <button
              type="button"
              className={`${searchCategory === 'member' ? 'active' : ''}`}
              name="member"
              onClick={onCheckSearchCategory}
            >
              사용자
            </button>
          </h5>
        </div>
        <div className="result">{showSearchResult()}</div>
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
