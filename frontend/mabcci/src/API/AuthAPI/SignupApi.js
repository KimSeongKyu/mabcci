import axios from 'axios';
import { useHistory } from 'react-router-dom';
import { SignupUrl } from '../ApiUrl';
import instance from '../index';

const SignupApi = async userInfo => {
  const history = useHistory();

  try {
    const res = await instance.post(SignupUrl, userInfo, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    history.push('login');

    return {
      status: res.status,
    };
  } catch (err) {
    console.log(err);

    return {
      status: err.status,
    };
  }
};

export default SignupApi;
