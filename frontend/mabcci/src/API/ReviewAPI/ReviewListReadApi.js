import { ReviewListReadUrl } from '../ApiUrl';
import instance from '../index';

const ReviewListReadApi = async nickname => {
  try {
    const response = await instance.get(`${ReviewListReadUrl}${nickname}`);
    return {
      status: response.status,
      data: response.data.proposalReviews,
    };
  } catch (response) {
    return response;
  }
};

export default ReviewListReadApi;
