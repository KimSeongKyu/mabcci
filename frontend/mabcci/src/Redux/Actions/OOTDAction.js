import { OOTD_ALL, OOTD_DETAIL } from '../Type/OOTDType';

export const OOTDAll = data => {
  return {
    type: OOTD_ALL,
    payload: data,
  };
};

export const OOTDDetail = () => {
  return {
    type: OOTD_DETAIL,
  };
};
