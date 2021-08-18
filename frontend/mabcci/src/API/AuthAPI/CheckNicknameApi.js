import { CheckNicknameUrl } from '../ApiUrl';
import instance from '../index';

const CheckNicknameApi = async nickname => {
  try {
    const response = await instance.get(`${CheckNicknameUrl}${nickname}`);
    return {
      data: response.data,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default CheckNicknameApi;
