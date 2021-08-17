import instance from '../index';
import { OOTDSearchUrl, OOTDLikeUrl } from '../ApiUrl';

export const OOTDFeedApi = async (category, page, nickname) => {
  const OOTDListUrl = `/api/ootds/${nickname}?filter=${category}&size=20&page=${page}`;
  const response = await instance.get(OOTDListUrl);
  console.log(response);
  return response;
};

export const SearchListApi = async (keyword, category) => {
  if (category === 'hashtag') {
    const searchListUrl = `/api/hashtags/search?hashtag=${keyword}`;
    const response = await instance.get(searchListUrl);
    return response;
  }
  if (category === 'member') {
    const searchListUrl = `/api/members/search?nickname=${keyword}`;
    const response = await instance.get(searchListUrl);
    return response;
  }
};
