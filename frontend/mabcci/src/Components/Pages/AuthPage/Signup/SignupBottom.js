import React from 'react';
import { useHistory } from 'react-router-dom';
import SignupApi from '../../../../API/AuthAPI/SingupApi';

function SignupBottom() {
  const history = useHistory();

  const handleSubmit = async e => {
    e.preventDefault();

    const response = await SignupApi();

    if (response.status === 200) {
      history.push('/login');
    } else {
      alert('회원가입 실패');
    }
  };

  // 버튼 클릭

  this.state = {};

  function handleClick() {}

  return (
    <div>
      <h5>성별</h5>
      <div className="select-man-woman">
        <button className="btn-rounded-man-woman" type="submit">
          Man
        </button>
        <button className="btn-rounded-man-woman" type="submit">
          Woman
        </button>
      </div>

      <h5>선호하는 스타일을 골라주세요!</h5>

      <div>
        <button type="submit" onClick={handleSubmit}>
          Sign up
        </button>
      </div>
    </div>
  );
}

export default SignupBottom;
