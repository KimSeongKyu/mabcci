import { OOTDUpdateUrl } from '../ApiUrl';
import instance from '../indexMock';

const OOTDUpdateApi = async (id, myOOTDInfo) => {
  try {
    const response = await instance.put(`${OOTDUpdateUrl}${id}`, myOOTDInfo);

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
