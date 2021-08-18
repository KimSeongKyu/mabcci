import { SUGGESTION_READ } from '../Type/SuggestionType';

const NavCategory = suggestion => {
  return {
    type: SUGGESTION_READ,
    payload: suggestion,
  };
};

export default NavCategory;
