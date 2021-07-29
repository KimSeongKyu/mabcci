import axios from 'axios';
import { useDispatch } from 'react-redux';
import jwtDecode from 'jwt-decode';
import { LoginUrl } from '../ApiUrl';
import { LoginSuccess, LoginFail } from '../../Redux/Actions/LoginAction';

const LoginApi = async userAuthInfo => {
  const dispatch = useDispatch();

  try {
    const response = await axios.post(LoginUrl, userAuthInfo, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const { accessToken } = response.data;
    const { refreshToken } = response.data;
    const decoded = jwtDecode(accessToken);
    const userInfo = {
      email: decoded.email,
      nickname: decoded.nickname,
      role: decoded.role,
    };

    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('refreshToken', refreshToken);
    localStorage.setItem('userInfo', userInfo);
    dispatch(LoginSuccess(userInfo));

    return {
      status: response.status,
      userInfo,
    };
  } catch (response) {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('userinfo');
    dispatch(LoginFail());

    return {
      status: response.status,
    };
  }
};

export default LoginApi;
