import _ from 'lodash';
import { OOTD_ALL, OOTD_CLEAN } from '../Type/OOTDType';
import { data } from '../data';

const initialState = {
  ootd: [],
};

const OotdReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case OOTD_ALL: {
      return { ...state, ootd: _.uniqBy([...state.ootd, ...payload], 'id') };
    }
    case OOTD_CLEAN: {
      return { ...state, ootd: payload };
    }
    default:
      return state;
  }
};

export default OotdReducer;
