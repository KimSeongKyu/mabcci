import React from 'react';
import blackcircle from './Image/blackcircle.png';

const OOTDHeader = () => {
  return (
    <header className="ootddetail-header">
      <div className="title">
        <h3>OOTD</h3>
      </div>
      <div className="detail-info">
        <img src={blackcircle} alt="tmpUserImage" width="70" />
        <p>username</p>
        <p>날짜</p>
        <p>조회수</p>
      </div>
    </header>
  );
};

export default OOTDHeader;
