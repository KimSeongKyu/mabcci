import axios from 'axios';
import { SignupUrl } from '../ApiUrl';

const SignupApi = async userInfo => {
  await axios
    .post(SignupUrl, userInfo)
    .then(() => {})
    .catch(() => {});
};

export default SignupApi;
