import axios from 'axios';
import { OOTDUpdateUrl } from '../ApiUrl';
import instance from '../indexMock';

const OOTDUpdateApi = async (id, myUpdateInfo) => {
  try {
    const response = await axios.put(
      `http://localhost:8080/api/ootds/${id}`,
      myUpdateInfo,
    );
    return {
      status: response.status,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default OOTDUpdateApi;
