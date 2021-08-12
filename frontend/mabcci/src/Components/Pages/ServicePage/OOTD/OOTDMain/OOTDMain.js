import React, { useState, useEFfect, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import NavCategory from '../../../../../Redux/Actions/NavAction';
import OOTDFeed from './OOTDFeed';
import OOTDHeader from './OOTDHeader';
import './OOTD.css';

function OOTDMain() {
  const [filtering, setFiltering] = useState(false);
  const [searching, setSearching] = useState(false);
  const [page, setPage] = useState(0);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(NavCategory('OOTD'));
  }, []);
  return (
    <div className="container">
      <OOTDHeader
        searching={searching}
        setPage={setPage}
        setFiltering={setFiltering}
        setSearching={setSearching}
      />
      <OOTDFeed
        filtering={filtering}
        searching={searching}
        page={page}
        setPage={setPage}
        setFiltering={setFiltering}
      />
    </div>
  );
}

export default OOTDMain;
