import React from 'react';
import './Signup.css';
import { useDispatch } from 'react-redux';
import SignupForm from './SignupForm';
import SignupHeader from './SignupHeader';
import { LoginSuccess, Logout } from '../../../../Redux/Actions/LoginAction';

function Signup() {
  const dispatch = useDispatch();

  function Login() {
    localStorage.setItem('accessToken', 'hi');
    dispatch(LoginSuccess('로그인OK'));
  }

  function LogOut() {
    localStorage.removeItem('accessToken');
    dispatch(Logout('로그아웃OK'));
  }

  return (
    <div>
      <div className="signup-container">
        <button type="submit" onClick={Login}>
          로그인
        </button>
        <button type="submit" onClick={LogOut}>
          로그아웃
        </button>
        <SignupHeader />
        <SignupForm />
      </div>
    </div>
  );
}

export default Signup;
