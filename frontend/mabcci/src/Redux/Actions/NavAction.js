import NAV_CATEGORY from '../Type/NavType';

const NavCategory = category => {
  return {
    type: NAV_CATEGORY,
    payload: category,
  };
};

export default NavCategory;
