import React from 'react';
import './Signup.css';
import SignupForm from './SignupForm';
import SignupHeader from './SignupHeader';

function Signup() {
  return (
    <div>
      <div className="signup-container">
        <SignupHeader />
        <SignupForm />
      </div>
    </div>
  );
}

export default Signup;
