import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';

const OOTDFeed = () => {
  const feeds = useSelector(state => state.OotdReducer.ootd);
  const [page, setPage] = useState(0);

  const showFeeds = () => {
    return feeds.map(({ id, nickname, picture, hashTag }) => {
      return (
        <div className="card" key={id}>
          <p>
            {nickname},{id}
          </p>
          <img src={picture} alt="" />
          <p>{hashTag}</p>
        </div>
      );
    });
  };

  return <div className="card-columns ootd-feed">{showFeeds()}</div>;
};

export default OOTDFeed;
