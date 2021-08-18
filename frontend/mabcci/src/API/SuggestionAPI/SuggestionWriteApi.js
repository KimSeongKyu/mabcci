import { SuggestionWriteUrl } from '../ApiUrl';
import instance from '../index';

const SuggestionWriteApi = async suggestion => {
  try {
    const response = await instance.post(SuggestionWriteUrl, suggestion);
    return response;
  } catch (response) {
    return response;
  }
};

export default SuggestionWriteApi;
