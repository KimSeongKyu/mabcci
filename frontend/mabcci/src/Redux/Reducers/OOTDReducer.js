import _ from 'lodash';
import { OOTD_ALL, OOTD_FILTERING, OOTD_FILTER_STATE } from '../Type/OOTDType';
import { data } from '../data';

const initialState = {
  filter: 'all',
  ootd: [],
};

const OotdReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case OOTD_ALL: {
      return { ...state, ootd: _.uniqBy([...state.ootd, ...payload], 'id') };
    }
    case OOTD_FILTERING: {
      console.log(payload);
      return { ...state, ootd: payload };
    }
    case OOTD_FILTER_STATE: {
      console.log(payload);
      return { ...state, filter: payload };
    }
    default:
      return state;
  }
};

export default OotdReducer;
