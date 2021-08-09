import React, { useState } from 'react';
import OOTDFeed from './OOTDFeed';
import OOTDHeader from './OOTDHeader';
import './OOTD.css';

function OOTDMain() {
  const [filter, setFilter] = useState('All');
  const [filtering, setFiltering] = useState(false);
  const [searching, setSearching] = useState(false);
  const [page, setPage] = useState(1);

  return (
    <div className="container">
      <OOTDHeader
        filter={filter}
        searching={searching}
        setFilter={setFilter}
        setPage={setPage}
        setFiltering={setFiltering}
        setSearching={setSearching}
      />
      <OOTDFeed
        filter={filter}
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
