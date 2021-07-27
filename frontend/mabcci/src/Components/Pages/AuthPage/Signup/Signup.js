import React from 'react';
import './Signup.css';
import SignupHeader from './SignupHeader';
import SignupForm from './SignupForm';
import SignupBottom from './SignupBottom';

function Signup() {
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
