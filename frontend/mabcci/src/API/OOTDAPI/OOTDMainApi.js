import instance from '../index';
import { OOTDSearchUrl, OOTDLikeUrl } from '../ApiUrl';

const OOTDFeedApi = async (category, page) => {
  console.log(category, page, '요청');
  const OOTDListUrl = `/api/ootd?filter=${category}&size=20&page=${page}`;
  const response = await instance.get(OOTDListUrl);
  return response;
};

export default OOTDFeedApi;
