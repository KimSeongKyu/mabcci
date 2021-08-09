import { useHistory } from 'react-router-dom';
import { SignupUrl } from '../ApiUrl';
import instance from '../index';

const SignupApi = async userInfo => {
  try {
    const res = await instance.post(SignupUrl, userInfo);
    return {
      status: res.status,
    };
  } catch (err) {
    return {
      status: err.status,
    };
  }
};

export default SignupApi;
