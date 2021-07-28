import { LOGIN_SUCCESS, LOGIN_FAIL, LOGOUT } from '../Type/LoginType';

const initialState = {
  isLoggedin: false,
  userinfo: {},
};

const LoginReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case LOGIN_SUCCESS:
      return {
        ...state,
        isLoggedIn: true,
        userinfo: {
          email: payload.email,
          nickname: payload.nickname,
          role: payload.role,
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
    default:
      return state;
  }
};

export default LoginReducer;
