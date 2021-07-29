import React from 'react';

const OOTDHeader = () => {
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
            <span className="active">All</span>
            <span>Following</span>
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
