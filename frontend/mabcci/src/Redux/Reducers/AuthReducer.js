import { LOGIN_SUCCESS, LOGIN_FAIL, LOGOUT, SIGNUP } from '../Type/AuthType';

const initialState = {
  isLoggedin: false,
  userinfo: {
    id: '',
  },
};

const AuthReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case LOGIN_SUCCESS:
      return {
        ...state,
        isLoggedIn: true,
        userinfo: {
          id: payload,
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
          id: payload,
        },
      };
    default:
      return state;
  }
};

export default AuthReducer;
