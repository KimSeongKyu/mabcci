import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import LoginApi from '../../../../API/AuthAPI/LoginApi';
import { LoginSuccess, LoginFail } from '../../../../Redux/Actions/AuthAction';
import './Login.css';

const LoginForm = () => {
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');

  const dispatch = useDispatch();
  const history = useHistory();

  const handleSubmit = async e => {
    e.preventDefault();

    const response = await LoginApi({ id, password });

    if (response.status === 200) {
      dispatch(LoginSuccess(id));
      history.push('/');
    } else {
      dispatch(LoginFail);
      history.push('/');
    }
  };

  return (
    <article id="container-item">
      <section className="loginForm">
        <form className="form" onSubmit={handleSubmit}>
          <div className="form-element">
            <input
              type="text"
              id="id"
              name="id"
              placeholder="id"
              onChange={e => {
                setId(e.target.value);
              }}
            />
          </div>
          <div className="form-element">
            <input
              type="text"
              id="password"
              name="password"
              placeholder="password"
              onChange={e => {
                setPassword(e.target.value);
              }}
            />
          </div>
          <button type="submit">Login</button>
        </form>
      </section>

      <section className="passwordFind">
        비밀번호를 잊으셨나요?
        <button type="button">비밀번호찾기</button>
      </section>
    </article>
  );
};

export default LoginForm;
