import React from 'react';

const OOTDHeader = ({ filter, setFilter, setPage }) => {
  const onFilter = e => {
    if (filter === e.target.name) return;
    const keyword = e.target.name;
    setFilter(keyword);
    setPage(1);
  };

  return (
    <div>
      <div className="ootd-write">
        <button className="btn-util" type="button">
          <i className="fas fa-plus" />
        </button>
      </div>
      <div className="ootd-util">
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
        <div>
          <i className="fas fa-search fa-lg" />
        </div>
      </div>
    </div>
  );
};

export default OOTDHeader;
