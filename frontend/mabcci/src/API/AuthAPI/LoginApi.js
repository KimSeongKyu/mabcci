import axios from 'axios';
import jwtDecode from 'jwt-decode';
import { LoginUrl } from '../ApiUrl';

const LoginApi = async userAuthInfo => {
  await axios
    .post(LoginUrl, userAuthInfo, {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      const { accessToken } = response;
      const { refreshToken } = response;
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
    })
    .catch(response => {
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
      localStorage.removeItem('userinfo');

      return {
        status: response.status,
      };
    });
};

export default LoginApi;
