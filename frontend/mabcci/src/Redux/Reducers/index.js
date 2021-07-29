import { combineReducers } from 'redux';
import LoginReducer from './LoginReducer';
import SignupReducer from './SignupReducer';
import OotdReducer from './OOTDReducer';

export default combineReducers({
  LoginReducer,
  SignupReducer,
  OotdReducer,
});
