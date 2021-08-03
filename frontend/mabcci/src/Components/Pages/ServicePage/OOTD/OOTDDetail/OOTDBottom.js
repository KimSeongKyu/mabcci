import React from 'react';
import userphoto from './Images/userphoto.png';

const OOTDBottom = () => {
  return (
    <footer className="detail-bottom">
      <div className="detail-comments-title">
        <h4>Comments (3)</h4>
      </div>
      <div className="detail-comments">
        <img src={userphoto} alt="UserImage" width="70" />
      </div>
    </footer>
  );
};

export default OOTDBottom;
