import React from 'react';
import './OOTDDetail.css';
import OOTDHeader from './OOTDHeader';
import OOTDContent from './OOTDContent';
import OOTDBottom from './OOTDBottom';

const OOTDDetail = () => {
  return (
    <div className="container detail-container">
      <OOTDHeader />
      <OOTDContent />
      <OOTDBottom />
    </div>
  );
};

export default OOTDDetail;
