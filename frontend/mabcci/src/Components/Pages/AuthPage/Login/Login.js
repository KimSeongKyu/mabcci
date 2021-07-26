import React from 'react';
import './Login.css';
import LoginHeader from './LoginHeader';
import LoginForm from './LoginForm';
import LoginBottom from './LoginBottom';

const Login = () => {
  return (
    <div id="container">
      <div id="container-login">
        <LoginHeader />
        <LoginForm />
        <LoginBottom />
      </div>
    </div>
  );
};

export default Login;
