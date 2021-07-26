import axios from 'axios';
import { LoginUrl } from '../ApiUrl';

const LoginApi = async userInfo => {
  await axios
    .post(LoginUrl, userInfo, {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(response => {
      localStorage.setItem('accessToken', response.data.accessToken);
      localStorage.setItem('userinfo', userInfo.id);
      localStorage.setItem('isLoggedIn', 'true');

      return {
        status: response.status,
      };
    })
    .catch(response => {
      localStorage.removeItem('accessToken');
      localStorage.removeItem('userinfo');
      localStorage.setItem('isLoggedIn', 'false');

      return {
        status: response.status,
      };
    });
};

export default LoginApi;
