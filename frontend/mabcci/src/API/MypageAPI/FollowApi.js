import { FollowUrl } from '../ApiUrl';
import instance from '../index';

const FollowApi = async followMembers => {
  try {
    const response = await instance.post(FollowUrl, followMembers);
    return {
      status: response.status,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default FollowApi;
