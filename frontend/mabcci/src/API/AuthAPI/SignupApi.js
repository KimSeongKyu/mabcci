import { useHistory } from 'react-router-dom';
import { SignupUrl } from '../ApiUrl';
import instance from '../index';

const SignupApi = async userInfo => {
  try {
    console.log('여기는 signupAPI');
    const res = await instance.post(SignupUrl, userInfo);
    console.log(res, '무반응인가');
    // history.push('login');

    return {
      status: res.status,
    };
  } catch (err) {
    console.log('아래가 에러임');
    console.log(err);

    return {
      status: err.status,
    };
  }
};

export default SignupApi;
