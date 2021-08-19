import { SuggestSuggestionListUrl } from '../ApiUrl';
import instance from '../index';

const SuggestSuggestionListApi = async nickname => {
  try {
    const response = await instance.get(
      `${SuggestSuggestionListUrl}${nickname}`,
    );
    return response.data.proposals;
  } catch (response) {
    return response;
  }
};

export default SuggestSuggestionListApi;
