import React from 'react';

const OOTDHeader = ({
  filter,
  searching,
  setFilter,
  setPage,
  setFiltering,
  setSearching,
}) => {
  const onFilter = e => {
    if (filter === e.target.name) return;
    const keyword = e.target.name;
    setFilter(keyword);
    setFiltering(true);
    console.log('필터링 ㄱㄱ');
    setPage(1);
  };

  const isSearching = () => {
    setSearching(!searching);
  };

  return (
    <div>
      <div className="ootd-write">
        <button className="btn-util" type="button">
          <i className="fas fa-plus" />
        </button>
      </div>
      <div className="ootd-util">
        {filter}
        <div>
          <p>
            <button
              className={`${filter === 'All' ? 'active' : ''}`}
              name="All"
              onClick={onFilter}
              type="button"
            >
              All
            </button>
            <button
              className={`${filter === 'Following' ? 'active' : ''}`}
              onClick={onFilter}
              name="Following"
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
