import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { OOTDFilterState } from '../../../../../Redux/Actions/OOTDAction';

const OOTDHeader = ({
  searching,
  setPage,
  setFiltering,
  setSearching,
  setKeyword,
  setSearchResult,
  setSearchInput,
}) => {
  const dispatch = useDispatch();
  const filterState = useSelector(state => state.OotdReducer.filter);
  const onFilter = e => {
    if (filterState === e.target.name) return;
    const keyword = e.target.name;
    dispatch(OOTDFilterState(keyword));
    setFiltering(true);
    setKeyword(null);
    setSearchResult([]);
    setSearchInput('');
    setPage(0);
  };

  const isSearching = () => {
    setSearching(!searching);
  };

  return (
    <div>
      <div className="ootd-write">
        <Link to="/OOTDWrite">
          <button className="btn-util" type="button">
            <i className="fas fa-plus" />
          </button>
        </Link>
      </div>
      <div className="ootd-util">
        <div>
          <p>
            <button
              className={`${filterState === 'all' ? 'active' : ''}`}
              name="all"
              onClick={onFilter}
              type="button"
            >
              All
            </button>
            <button
              className={`${filterState === 'following' ? 'active' : ''}`}
              onClick={onFilter}
              name="following"
              type="button"
            >
              Following
            </button>
          </p>
        </div>
        <button type="button" onClick={isSearching}>
          <i className="fas fa-search fa-lg" />
        </button>
      </div>
    </div>
  );
};

export default OOTDHeader;
