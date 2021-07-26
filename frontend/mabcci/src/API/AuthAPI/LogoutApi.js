import axios from 'axios';
import { LogoutUrl } from '../ApiUrl';

const LogoutApi = async () => {
  await axios
    .get(LogoutUrl)
    .then(() => {})
    .catch(() => {});
};

export default LogoutApi;
