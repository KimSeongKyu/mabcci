import { LoginUrl } from '../ApiUrl';
import instance from '../index';
import getUserInfo from '../../Components/Common/getUserInfo';

const LoginApi = async userAuthInfo => {
  try {
    const response = await instance.post(LoginUrl, userAuthInfo);

    const { accessToken } = response.data;
    const { refreshToken } = response.data;

    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('refreshToken', refreshToken);
    const userInfo = getUserInfo();

    return {
      status: response.status,
      userInfo,
    };
  } catch (response) {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');

    return {
      status: response.status,
    };
  }
};

export default LoginApi;
