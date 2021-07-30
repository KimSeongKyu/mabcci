import React from 'react';
import { Link } from 'react-router-dom';

const DescriptionPage3 = () => {
  return (
    <div className="desc-third">
      <div className="desc-mabcci">
        <p>스타일리쉬한</p>
        <p>
          <span>맵시</span>와 소통하며
        </p>
        <p>고민을 해결해보세요</p>
      </div>
      <div className="desc-arrow">
        <p>Click!</p>
        <button className="btn-site" type="button">
          <Link to="home">
            <p>사이트로 가기</p>
          </Link>
        </button>
      </div>
    </div>
  );
};

export default DescriptionPage3;
