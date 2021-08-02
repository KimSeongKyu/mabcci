import React from 'react';
import OOTDFeed from './OOTDFeed';
import OOTDHeader from './OOTDHeader';
import './OOTD.css';

function OOTDMain() {
  return (
    <div className="container">
      <OOTDHeader />
      <OOTDFeed />
    </div>
  );
}

export default OOTDMain;
