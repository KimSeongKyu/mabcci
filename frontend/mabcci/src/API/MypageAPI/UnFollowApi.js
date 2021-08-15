import { UnFollowUrl } from '../ApiUrl';
import instance from '../index';

const FollowApi = async followMembers => {
  try {
    const response = await instance.delete(UnFollowUrl, followMembers);
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
