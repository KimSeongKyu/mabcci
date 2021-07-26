import { LOGIN_SUCCESS, LOGIN_FAIL, LOGOUT, SIGNUP } from '../Type/AuthType';

export const LoginSuccess = id => {
  return {
    type: LOGIN_SUCCESS,
    payload: id,
  };
};

export const LoginFail = () => {
  return {
    type: LOGIN_FAIL,
  };
};

export const Logout = () => {
  return {
    type: LOGOUT,
  };
};

export const Signup = () => {
  return {
    type: SIGNUP,
  };
};
