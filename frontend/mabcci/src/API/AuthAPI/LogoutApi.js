import axios from 'axios';
import { LogoutUrl } from '../ApiUrl';

const LogoutApi = async () => {
  try {
    const response = await axios.get(LogoutUrl);
    return {
      status: response.status,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default LogoutApi;
