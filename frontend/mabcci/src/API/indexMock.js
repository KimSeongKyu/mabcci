import axios from 'axios';

const createInstance = () => {
  return axios.create({
    baseURL: 'https://1d4105a0-ceb8-4269-81ca-a1e4c7348a60.mock.pstmn.io',
  });
};

const instance = createInstance();

export default instance;
