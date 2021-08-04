import { LogoutUrl } from '../ApiUrl';
import instance from '../index';

const LogoutApi = async () => {
  try {
    const response = await instance.get(LogoutUrl);
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
