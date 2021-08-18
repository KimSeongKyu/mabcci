import { SUGGESTION_READ } from '../Type/SuggestionType';

const initialState = {
  targetMemberNickname: '',
  mabcciNickname: '',
  top: null,
  bottom: null,
  shoes: null,
  accessory: null,
  description: null,
};

const SuggestionReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case SUGGESTION_READ: {
      return {
        ...state,
        ...payload,
      };
    }
    default:
      return state;
  }
};

export default SuggestionReducer;
