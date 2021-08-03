import React from 'react';
import './OOTDDetail.css';
import OOTDHeader from './OOTDHeader';
import OOTDContentApi from './OOTDContentApi';
import OOTDBottom from './OOTDBottom';

const OOTDDetail = () => {
  return (
    <div className="container">
      <OOTDHeader />
      <OOTDContentApi />
      <OOTDBottom />
    </div>
  );
};

export default OOTDDetail;
