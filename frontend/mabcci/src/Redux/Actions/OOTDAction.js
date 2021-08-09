import {
  OOTD_ALL,
  OOTD_DETAIL,
  OOTD_FILTERING,
  OOTD_FILTER_STATE,
} from '../Type/OOTDType';

export const OOTDAll = data => {
  return {
    type: OOTD_ALL,
    payload: data,
  };
};

export const OOTDFiltering = data => {
  return {
    type: OOTD_FILTERING,
    payload: data,
  };
};

export const OOTDFilterState = data => {
  return {
    type: OOTD_FILTER_STATE,
    payload: data,
  };
};

export const OOTDDetail = () => {
  return {
    type: OOTD_DETAIL,
  };
};
