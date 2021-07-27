import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import LoginApi from '../../../../API/AuthAPI/LoginApi';
import { LoginSuccess, LoginFail } from '../../../../Redux/Actions/AuthAction';

const LoginForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const dispatch = useDispatch();
  const history = useHistory();

  const handleSubmit = async e => {
    e.preventDefault();

    const response = await LoginApi({ email, password });

    if (response.status === 200) {
      dispatch(LoginSuccess(response.userInfo));
      history.push('/home');
    } else {
      dispatch(LoginFail);
      history.push('/home');
    }
  };

  return (
    <article id="container-item">
      <section className="loginForm">
        <form className="form" onSubmit={handleSubmit}>
          <div className="form-element">
            <input
              type="text"
              id="email"
              name="email"
              placeholder="Email"
              onChange={e => {
                setEmail(e.target.value);
              }}
            />

            <input
              type="text"
              id="password"
              name="password"
              placeholder="Password"
              onChange={e => {
                setPassword(e.target.value);
              }}
            />
          </div>
          <button className="btn" type="submit">
            Login
          </button>
        </form>
      </section>

      <section className="passwordFind">
        비밀번호를 잊으셨나요?
        <button className="btn-transparent" type="button">
          비밀번호찾기
        </button>
      </section>
    </article>
  );
};

export default LoginForm;
