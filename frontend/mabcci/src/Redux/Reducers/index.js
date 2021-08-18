import { combineReducers } from 'redux';
import LoginReducer from './LoginReducer';
import OotdReducer from './OOTDReducer';
import NavReducer from './NavReducer';
import SuggestionReducer from './SuggestionReducer';

export default combineReducers({
  LoginReducer,
  OotdReducer,
  NavReducer,
  SuggestionReducer,
});
