import axios from 'axios';

const createInstance = () => {
  return axios.create({
    baseURL: '',
    headers: {
      'Content-type': 'application/json',
    },
  });
};

const instance = createInstance();

export default instance;
