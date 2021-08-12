import React, { useEffect } from 'react';
import './StylingMain.css';
import { useDispatch } from 'react-redux';
import NavCategory from '../../../../../Redux/Actions/NavAction';
import MabcciSearch from './MabcciSearch';

function StylingMain() {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(NavCategory('Styling'));
  }, []);
  return (
    <div className="container styling-container">
      <MabcciSearch />
    </div>
  );
}

export default StylingMain;
