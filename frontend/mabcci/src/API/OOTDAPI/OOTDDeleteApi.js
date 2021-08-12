import { OOTDDeleteUrl } from '../ApiUrl';
import instance from '../indexMock';

const OOTDDeleteApi = async id => {
  try {
    const response = await instance.delete(`${OOTDDeleteUrl}/${id}`);

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

export default OOTDDeleteApi;
