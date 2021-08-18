import axios from 'axios';
import instance from '../index';
import { OOTDSearchUrl, OOTDLikeUrl } from '../ApiUrl';

export const OOTDFeedApi = async (category, page, nickname) => {
  const OOTDFeedUrl = `/api/ootds/${nickname}?filter=${category}&size=20&page=${page}`;
  const response = await instance.get(OOTDFeedUrl);
  return response;
};

export const SearchedOOTDFeedApi = async (keyword, page) => {
  const SearchedOOTDFeedUrl = `/api/ootds?search=${keyword}&filter=hashtag&size=20&page=${page}`;
  const response = await instance.get(SearchedOOTDFeedUrl);
  console.log(response);
  return response;
};

export const SearchListApi = async (keyword, category) => {
  const { CancelToken } = axios;
  const source = CancelToken.source();
  try {
    if (category === 'hashtag') {
      const searchListUrl = `/api/hashtags/search?hashtag=${keyword}`;
      const response = await instance.get(searchListUrl, {
        cancelToken: source.token,
      });
      return response;
    }
    if (category === 'member') {
      const searchListUrl = `/api/members/search?nickname=${keyword}`;
      const response = await instance.get(searchListUrl);
      return response;
    }
  } catch (error) {
    console.log(error);
  }
  return () => source.cancel();
};
