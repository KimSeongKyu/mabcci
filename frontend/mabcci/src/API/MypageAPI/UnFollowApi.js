import { UnFollowUrl } from '../ApiUrl';
import instance from '../index';

const UnFollowApi = async deleteFollowMembers => {
  try {
    const response = await instance.delete(UnFollowUrl, {
      data: deleteFollowMembers,
    });
    return {
      status: response.status,
    };
  } catch (response) {
    return {
      status: response.status,
      res: response,
    };
  }
};

export default UnFollowApi;
