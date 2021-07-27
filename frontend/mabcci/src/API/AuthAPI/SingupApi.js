import axios from 'axios';
import { useHistory } from 'react-router-dom';
import { SignupUrl } from '../ApiUrl';

const SignupApi = async userInfo => {
  const history = useHistory();

  await axios
    .post(SignupUrl, userInfo)
    .then(res => {
      alert('회원가입성공!');
      history.push('login');

      return {
        status: res.status,
      };
    })
    .catch(err => {
      console.log(err);
      alert('회원가입실패');

      return {
        status: err.status,
      };
    });
};

export default SignupApi;
