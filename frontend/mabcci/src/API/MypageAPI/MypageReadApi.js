import { MypageReadUrl } from '../ApiUrl';
import instance from '../index';

const MypageReadApi = async nickname => {
  try {
    const response = await instance.get(`${MypageReadUrl}/${nickname}`);
    return {
      status: response.status,
      myInfo: response.data,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default MypageReadApi;
