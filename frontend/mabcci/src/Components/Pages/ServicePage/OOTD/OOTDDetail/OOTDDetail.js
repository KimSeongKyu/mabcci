import React from 'react';
import { useParams } from 'react-router-dom';
import './OOTDDetail.css';
import OOTDHeader from './OOTDHeader';
import OOTDContent from './OOTDContent';
import OOTDBottom from './OOTDBottom';
import getUserInfo from '../../../../Common/getUserInfo';

const OOTDDetail = () => {
  const { id, nickname } = useParams();
  const userInfo = getUserInfo();

  return (
    <div className="container detail-container">
      <OOTDHeader />
      <OOTDContent />
      <OOTDBottom ootdId={id} writerNickname={nickname} userInfo={userInfo} />
    </div>
  );
};

export default OOTDDetail;
