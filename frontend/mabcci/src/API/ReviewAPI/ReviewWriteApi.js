import { ReviewWriteUrl } from '../ApiUrl';
import instance from '../index';

const ReviewWriteApi = async review => {
  console.log(review);
  try {
    const response = await instance.post(ReviewWriteUrl, review);
    return response;
  } catch (response) {
    return response;
  }
};

export default ReviewWriteApi;
