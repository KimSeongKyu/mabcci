import axios from 'axios';

const createInstance = () => {
  return axios.create({
    baseURL: 'https://77c96212-0588-4a42-9cea-d4e96154fac4.mock.pstmn.io',
  });
};

const instance = createInstance();

export default instance;
