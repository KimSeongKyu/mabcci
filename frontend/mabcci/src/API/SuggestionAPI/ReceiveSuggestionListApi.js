import { ReceivedSuggestionListUrl } from '../ApiUrl';
import instance from '../index';

const ReceiveSuggestionListApi = async nickname => {
  try {
    const response = await instance.get(
      `${ReceivedSuggestionListUrl}${nickname}`,
    );
    return response.data.proposals;
  } catch (response) {
    return response;
  }
};

export default ReceiveSuggestionListApi;
