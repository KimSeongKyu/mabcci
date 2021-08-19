import axios from 'axios';

const createInstance = () => {
  return axios.create({
    baseURL: '13.124.127.111:8080',
    headers: {
      'Content-type': 'application/json',
    },
  });
};

const instance = createInstance();

export default instance;
