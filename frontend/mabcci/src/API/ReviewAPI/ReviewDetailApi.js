import { ReviewDetailUrl } from '../ApiUrl';
import instance from '../index';

const ReviewDetailApi = async id => {
  try {
    const response = await instance.get(
      `${ReviewDetailUrl}${id}/reviews/details`,
    );
    return {
      status: response.status,
      data: response.data,
    };
  } catch (response) {
    return response;
  }
};

export default ReviewDetailApi;
