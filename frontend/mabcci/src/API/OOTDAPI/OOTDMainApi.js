import instance from '../index';
import { OOTDSearchUrl, OOTDLikeUrl } from '../ApiUrl';

const OOTDFeedApi = async (category, page, nickname) => {
  console.log(`${page}로 요청`);
  const OOTDListUrl = `/api/ootds/${nickname}?filter=${category}&size=20&page=${page}`;
  const response = await instance.get(OOTDListUrl);
  console.log(response);
  return response;
};

export default OOTDFeedApi;
