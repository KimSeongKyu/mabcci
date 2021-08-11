import { MypageUpdateUrl } from '../ApiUrl';
import instance from '../index';

const MypageUpdateApi = async (myInfo, nickname) => {
  try {
    console.log(nickname);
    const response = await instance.post(
      `${MypageUpdateUrl}/${nickname}`,
      myInfo,
    );
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

export default MypageUpdateApi;
