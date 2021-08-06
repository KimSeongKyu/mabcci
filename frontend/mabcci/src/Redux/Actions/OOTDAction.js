import { OOTD_ALL, OOTD_DETAIL, OOTD_CLEAN } from '../Type/OOTDType';

export const OOTDAll = data => {
  return {
    type: OOTD_ALL,
    payload: data,
  };
};

export const OOTDClean = () => {
  return {
    type: OOTD_CLEAN,
    payload: [],
  };
};

export const OOTDDetail = () => {
  return {
    type: OOTD_DETAIL,
  };
};
