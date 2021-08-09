import { OOTDWriteUrl } from '../ApiUrl';
import instance from '../index';

const OOTDWriteApi = async myOOTDInfo => {
  try {
    const response = await instance.post(OOTDWriteUrl, myOOTDInfo);

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

export default OOTDWriteApi;
