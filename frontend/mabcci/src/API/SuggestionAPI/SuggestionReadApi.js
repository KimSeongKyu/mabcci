import { SuggestionReadUrl } from '../ApiUrl';
import instance from '../index';

const SuggestionReadApi = async suggestionId => {
  try {
    const response = await instance.get(`${SuggestionReadUrl}${suggestionId}`);
    return {
      status: response.status,
      suggestion: response.data,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export default SuggestionReadApi;
