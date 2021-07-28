import React from 'react';
import './intro.css';
import { Link, useHistory } from 'react-router-dom';

const Intro = () => {
  const history = useHistory();
  const localLoinToken = localStorage.getItem('accessToken');

  // 로그인이 되어 있는 경우 홈 화면으로
  if (localLoinToken) {
    history.push('/home');
  }

  return (
    <div className="intro-background">
      <div className="intro-section">
        <h1>
          Welcome to <span>Mabcci</span>
        </h1>
        <button className="btn" type="button">
          <Link to="/login">
            <p>로그인</p>
          </Link>
        </button>
        <button className="btn" type="button">
          <Link to="/signup">
            <p>회원가입</p>
          </Link>
        </button>
      </div>
    </div>
  );
};

export default Intro;
