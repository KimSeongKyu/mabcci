import axios from 'axios';

const createInstance = () => {
  return axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
      'Content-type': 'application/json',
    },
  });
};

const instance = createInstance();

export default instance;
