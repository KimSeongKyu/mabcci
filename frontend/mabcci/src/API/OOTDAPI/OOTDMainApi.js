import instance from '../index';
import { OOTDSearchUrl, OOTDLikeUrl } from '../ApiUrl';

const OOTDFeedApi = async (category, page) => {
  console.log(category, page, '요청');
  const mockUrl = `https://9f78dc1d-4e5f-4f16-a89b-7ac640472f7e.mock.pstmn.io/api/ootd?filter=${category}&size=20&page=${page}`;
  // const OOTDListUrl = `/api/ootd?filter=${category}&size=20&page=${page}`;
  const response = await instance.get(mockUrl);
  return response;
};

export default OOTDFeedApi;
