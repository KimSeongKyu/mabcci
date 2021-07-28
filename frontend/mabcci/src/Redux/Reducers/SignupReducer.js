import {
  SIGNUP_INPUT,
  SIGNUP_SELECT_STYLE,
  SIGNUP_SELECT_GENDER,
} from '../Type/SignupType';

const SignupInfo = {
  email: '',
  nickname: '',
  PhoneNumber: '',
  firstPhoneNumber: '',
  secondPhoneNumber: '',
  thirdPhoneNumber: '',
  password: '',
  passwordConfirmation: '',
  categories: [],
  gender: '',
};
const SignupReducer = (state = SignupInfo, { type, payload }) => {
  switch (type) {
    case SIGNUP_INPUT: {
      const copy = { ...state };
      console.log(payload, '확인점');
      copy.email = payload.email;
      copy.nickname = payload.nickname;
      copy.PhoneNumber = `${payload.firstPhoneNumber}-${payload.secondPhoneNumber}-${payload.thirdPhoneNumber}`;
      copy.firstPhoneNumber = payload.firstPhoneNumber;
      copy.secondPhoneNumber = payload.secondPhoneNumber;
      copy.thirdPhoneNumber = payload.thirdPhoneNumber;
      copy.password = payload.password;
      copy.passwordConfirmation = payload.passwordConfirmation;
      return copy;
    }
    case SIGNUP_SELECT_STYLE: {
      const copy = { ...state };
      copy.categories = payload;
      return copy;
    }
    case SIGNUP_SELECT_GENDER: {
      const copy = { ...state };
      copy.gender = payload;
      return copy;
    }

    default:
      return state;
  }
};

export default SignupReducer;
