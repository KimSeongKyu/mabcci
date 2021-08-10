import instance from '../index';
import { OOTDSearchUrl, OOTDLikeUrl } from '../ApiUrl';

const OOTDFeedApi = async (category, page) => {
  const mockUrl = `http://localhost/ootd-${page}-${category}`;
  // const OOTDListUrl = `/api/ootd?filter=${category}&size=20&page=${page}`;
  const response = await instance.get(mockUrl);
  console.log(response);
  return response;
};

export default OOTDFeedApi;
