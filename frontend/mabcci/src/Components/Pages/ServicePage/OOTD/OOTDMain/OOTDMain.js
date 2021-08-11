import React, { useState } from 'react';
import OOTDFeed from './OOTDFeed';
import OOTDHeader from './OOTDHeader';
import './OOTD.css';

function OOTDMain() {
  const [filtering, setFiltering] = useState(false);
  const [searching, setSearching] = useState(false);
  const [page, setPage] = useState(0);

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
