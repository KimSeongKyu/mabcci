import React from 'react';
import { useSelector } from 'react-redux';

const OOTDFeed = () => {
  const feeds = useSelector(state => state.OotdReducer);
  console.log(feeds);
  return (
    <div>
      <h1>Feed</h1>
    </div>
  );
};

export default OOTDFeed;
