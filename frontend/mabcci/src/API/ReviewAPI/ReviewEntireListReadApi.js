import { ReviewEntireListReadUrl } from '../ApiUrl';
import instance from '../index';

const ReviewEntireListReadApi = async nickname => {
  try {
    const response = await instance.get(
      `${ReviewEntireListReadUrl}${nickname}`,
    );
    return {
      status: response.status,
      data: response.data.proposalReviews,
    };
  } catch (response) {
    return response;
  }
};

export default ReviewEntireListReadApi;
