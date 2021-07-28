import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import LoginApi from '../../../../API/AuthAPI/LoginApi';
import { LoginSuccess, LoginFail } from '../../../../Redux/Actions/LoginAction';

const LoginForm = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isLoggedInFail, setIsLoggedInFail] = useState(false);

  const dispatch = useDispatch();
  const history = useHistory();

  const handleSubmit = async e => {
    e.preventDefault();

    const userAuthInfo = {
      email,
      password,
    };

    const response = await LoginApi(userAuthInfo);

    console.log(response);
    console.log('develop');
    if (response.status === 200) {
      dispatch(LoginSuccess(response.userInfo));
      history.push('/home');
    } else {
      dispatch(LoginFail());
      setEmail('');
      setPassword('');
      setIsLoggedInFail(true);
    }
  };

  return (
    <article className="container-item">
      <section className="loginForm">
        <form className="form" onSubmit={handleSubmit}>
          <div className="form-element">
            <input
              type="text"
              id="email"
              name="email"
              placeholder="Email"
              value={email}
              onChange={e => {
                setEmail(e.target.value);
              }}
            />

            <input
              type="text"
              id="password"
              name="password"
              placeholder="Password"
              value={password}
              onChange={e => {
                setPassword(e.target.value);
              }}
            />

            {isLoggedInFail ? (
              <p className="id-warnning">
                아이디 또는 비밀번호를 다시 확인해주세요.
              </p>
            ) : null}
          </div>
          <button className="btn" type="submit">
            Login
          </button>
        </form>
      </section>

      <section className="passwordFind">
        <p>비밀번호를 잊으셨나요?</p>
        <button className="btn-transparent" type="button">
          <p>비밀번호찾기</p>
        </button>
      </section>
    </article>
  );
};

export default LoginForm;
