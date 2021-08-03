import jwtDecode from 'jwt-decode';
import { LoginUrl } from '../ApiUrl';
import instance from '../index';

const LoginApi = async userAuthInfo => {
  try {
    const response = await instance.post(LoginUrl, userAuthInfo);

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

    return {
      status: response.status,
      userInfo,
    };
  } catch (response) {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('userinfo');

    return {
      status: response.status,
    };
  }
};

export default LoginApi;
