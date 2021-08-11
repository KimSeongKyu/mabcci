import { AllMabcciUrl } from '../ApiUrl';
import instance from '../index';

const AllMabcciApi = async () => {
  try {
    const response = await instance.get(AllMabcciUrl);
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

export default AllMabcciApi;
