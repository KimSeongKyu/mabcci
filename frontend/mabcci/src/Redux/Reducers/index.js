import { combineReducers } from 'redux';
import LoginReducer from './LoginReducer';
import OotdReducer from './OOTDReducer';
import NavReducer from './NavReducer';

export default combineReducers({
  LoginReducer,
  OotdReducer,
  NavReducer,
});
