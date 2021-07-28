import { LOGIN_SUCCESS, LOGIN_FAIL, LOGOUT } from '../Type/LoginType';

const initialState = {
  isLoggedin: false,
  userInfo: {},
};

const LoginReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case LOGIN_SUCCESS:
      return {
        ...state,
        isLoggedIn: true,
        userInfo: {
          email: payload.email,
          nickname: payload.nickname,
          role: payload.role,
        },
      };
    case LOGIN_FAIL:
      return {
        ...state,
        isLoggedIn: false,
        userInfo: null,
      };
    case LOGOUT:
      return {
        ...state,
        isLoggedIn: false,
        userInfo: null,
      };
    default:
      return state;
  }
};

export default LoginReducer;
