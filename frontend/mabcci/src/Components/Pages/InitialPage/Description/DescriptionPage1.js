import React from 'react';
import './Description.css';

const DescriptionPage1 = () => {
  return (
    <div className="desc-container">
      <div className="desc">
        <div className="desc-mabcci">
          <h1>Mabcci</h1>
          <p>옷맵시</p>
          <p>: 차려입은 옷이 어울리는 모양새.</p>
        </div>
        <div className="desc-arrow">
          <p>밀어서 넘기기</p>
          <i className="fas fa-long-arrow-alt-right" />
        </div>
      </div>
    </div>
  );
};

export default DescriptionPage1;
