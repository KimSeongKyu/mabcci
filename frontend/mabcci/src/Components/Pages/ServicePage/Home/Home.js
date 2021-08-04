import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div>
      <h3>홈페이지입니다.</h3>
      <Link to="/OOTDWrite">임시링크</Link>
    </div>
  );
}

export default Home;
