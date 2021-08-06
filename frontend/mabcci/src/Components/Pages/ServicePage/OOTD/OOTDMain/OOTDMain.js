import React, { useState } from 'react';
import OOTDFeed from './OOTDFeed';
import OOTDHeader from './OOTDHeader';
import './OOTD.css';

function OOTDMain() {
  const [filter, setFilter] = useState('All');
  const [page, setPage] = useState(1);
  return (
    <div className="container">
      {page}
      <OOTDHeader filter={filter} setFilter={setFilter} setPage={setPage} />
      <OOTDFeed filter={filter} page={page} setPage={setPage} />
    </div>
  );
}

export default OOTDMain;
