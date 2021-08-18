import { PopularMabcciUrl } from '../ApiUrl';
import instance from '../indexMockSeongae';

const PopularMabcciApi = async () => {
  try {
    const response = await instance.get(PopularMabcciUrl);
    return {
      status: response.status,
      mabccies: response.data.mabccies,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default PopularMabcciApi;
