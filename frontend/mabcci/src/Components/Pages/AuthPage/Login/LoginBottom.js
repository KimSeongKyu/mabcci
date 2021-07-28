import React from 'react';
import { Link } from 'react-router-dom';

const LoginBottom = () => {
  return (
    <footer className="container-item">
      <hr />
      <p>계정이 없으신가요?</p>
      <button className="btn-transparent" type="button">
        <Link to="signup">
          <p>회원가입하기</p>
        </Link>
      </button>
    </footer>
  );
};

export default LoginBottom;
