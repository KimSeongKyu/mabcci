import { OOTDAll } from '../Actions/OOTDAction';
import { data } from '../data';

const initialState = {
  ootd: data,
};

const OotdReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case OOTDAll: {
      return [...state, ...payload];
    }
    default:
      return state;
  }
};

export default OotdReducer;
