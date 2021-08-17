import { UnFollowUrl } from '../ApiUrl';
import instance from '../index';

const UnFollowApi = async deleteFollowMembers => {
  try {
    console.log(deleteFollowMembers, '언팔할때 보내는 데이터');
    const response = await instance.delete(UnFollowUrl, deleteFollowMembers);
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
