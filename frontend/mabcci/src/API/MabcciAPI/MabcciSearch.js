import { MabcciSearchUrl } from '../ApiUrl';
import instance from '../index';

const MabcciSearch = async () => {
  try {
    const response = await instance.get(MabcciSearchUrl);
    return {
      status: response.status,
      mabcci: response.data.mabcci,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default MabcciSearch;
