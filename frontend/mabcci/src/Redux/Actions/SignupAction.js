import {
  SIGNUP_INPUT,
  SIGNUP_SELECT_STYLE,
  SIGNUP_SELECT_GENDER,
} from '../Type/SignupType';

export const SignupInput = data => {
  return {
    type: SIGNUP_INPUT,
    payload: data,
  };
};

export const SingupSelectStyle = style => {
  return {
    type: SIGNUP_SELECT_STYLE,
    payload: style,
  };
};

export const SingupSelectGender = gender => {
  return {
    type: SIGNUP_SELECT_GENDER,
    payload: gender,
  };
};
