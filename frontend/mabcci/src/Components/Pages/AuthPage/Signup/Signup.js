import React from 'react';
import './Signup.css';
// import { useHistory } from 'react-router-dom';
// import axios from 'axios';
import SignupHeader from './SignupHeader';
import SignupForm from './SignupForm';
import SignupBottom from './SignupBottom';

function Signup() {
  // const history = useHistory();

  // // 회원가입 제출
  // function signUp() {
  //   const data = userInfo;
  //   const USER_CREATE_URL = 'api/members';

  //   axios
  //     .post(USER_CREATE_URL, data)
  //     .then(function () {
  //       alert('회원가입성공!');
  //       history.push('home');
  //     })
  //     .catch(function (err) {
  //       console.log(err);
  //       alert('회원가입실패');
  //     });
  // }

  return (
    <div>
      <div className="container">
        <SignupHeader />
        <SignupForm />
        <SignupBottom />
      </div>
    </div>
  );
}

export default Signup;
