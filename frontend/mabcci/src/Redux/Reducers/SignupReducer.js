import {
  SIGNUP_INPUT,
  SIGNUP_SELECT_STYLE,
  SIGNUP_SELECT_SEX,
} from '../Type/SignupType';

const SignupInfo = {
  email: '',
  nickname: '',
  firstPhoneNumber: '',
  secondPhoneNumber: '',
  thirdPhoneNumber: '',
  password: '',
  passwordConfirmation: '',
  selectStyle: [],
  selectSex: '',
};
const SignupReducer = (state = SignupInfo, { type, payload }) => {
  switch (type) {
    case SIGNUP_INPUT: {
      const copy = { ...state };
      copy.email = payload.email;
      copy.nickname = payload.nickname;
      copy.firstPhoneNumber = payload.firstPhoneNumber;
      copy.secondPhoneNumber = payload.secondPhoneNumber;
      copy.thirdPhoneNumber = payload.thirdPhoneNumber;
      copy.password = payload.password;
      copy.passwordConfirmation = payload.passwordConfirmation;
      return copy;
    }
    case SIGNUP_SELECT_STYLE: {
      const copy = { ...state };
      copy.selectStyle = payload;
      return copy;
    }
    case SIGNUP_SELECT_SEX:
      return state;
    default:
      return state;
  }
};

export default SignupReducer;
