import NAV_CATEGORY from '../Type/NavType';

const initialState = '';

const NavReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case NAV_CATEGORY: {
      return payload;
    }
    default:
      return state;
  }
};

export default NavReducer;
