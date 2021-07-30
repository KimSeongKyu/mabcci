import React from 'react';
import userphoto from './Images/userphoto.png';

const OOTDHeader = () => {
  return (
    <header className="detail-header">
      <div className="detail-title">
        <h3>OOTD</h3>
      </div>
      <div className="detail-info">
        <div className="detail-info-photo">
          <img src={userphoto} alt="UserImage" width="70" />
        </div>
        <div className="detail-info-content">
          <p>김고은</p>
          <p>2021.07.29 views:50</p>
        </div>
      </div>
      <hr />
    </header>
  );
};

export default OOTDHeader;
