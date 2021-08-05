import { OOTDUpdateUrl } from '../ApiUrl';
import instance from '../indexMock';

const OOTDUpdateApi = async myOOTDInfo => {
  try {
    const response = await instance.put(OOTDUpdateUrl, myOOTDInfo);

    return {
      status: response.status,
      info: response.data,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default OOTDUpdateApi;
