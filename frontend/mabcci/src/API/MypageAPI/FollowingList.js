import { FollowListUrl } from '../ApiUrl';
import instance from '../index';

const FollowingList = async (nickname, followingList) => {
  try {
    const response = await instance.get(
      `${FollowListUrl}${nickname}/following`,
      followingList,
    );

    return {
      status: response.status,
      data: response.data,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default FollowingList;
