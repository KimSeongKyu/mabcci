import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from 'react-router-dom';
import {
  LoginSuccess,
  LoginFail,
  LoginAuto,
} from '../../../../Redux/Actions/LoginAction';
import LoginApi from '../../../../API/AuthAPI/LoginApi';

const DescriptionPage3 = () => {
  const dispatch = useDispatch();
  const history = useHistory();

  const loginRedux = useSelector(state => state.LoginReducer);

  const LoginToHome = async () => {
    const res = await LoginApi(loginRedux.userInfo);
    if (res.status === 200) {
      dispatch(LoginSuccess(res.userInfo));
    } else {
      dispatch(LoginFail());
    }
    history.push('/home');
  };

  return (
    <div className="desc-third">
      <div className="desc-mabcci">
        <p>스타일리쉬한</p>
        <p>
          <span>맵시</span>와 소통하며
        </p>
        <p>고민을 해결해보세요</p>
      </div>
      <div className="desc-arrow">
        <p>Click!</p>
        <button className="btn-site" type="button" onClick={LoginToHome}>
          <p>사이트로 가기</p>
        </button>
      </div>
    </div>
  );
};

export default DescriptionPage3;
