import { LOGIN_SUCCESS, LOGIN_FAIL, LOGOUT, SIGNUP } from '../Type/AuthType';

const initialState = {
  isLoggedin: false,
  userinfo: {},
};

const AuthReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case LOGIN_SUCCESS:
      return {
        ...state,
        isLoggedIn: true,
        userinfo: {
          payload,
        },
      };
    case LOGIN_FAIL:
      return {
        ...state,
        isLoggedIn: false,
        userinfo: null,
      };
    case LOGOUT:
      return {
        ...state,
        isLoggedIn: false,
        userinfo: null,
      };
    case SIGNUP:
      return {
        ...state,
        isLoggedIn: true,
        userinfo: {
          payload,
        },
      };
    default:
      return state;
  }
};

export default AuthReducer;
