import React, { useState, useEFfect, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import NavCategory from '../../../../../Redux/Actions/NavAction';
import OOTDFeed from './OOTDFeed';
import OOTDHeader from './OOTDHeader';
import './OOTD.css';

function OOTDMain() {
  const [filtering, setFiltering] = useState(false);
  const [searching, setSearching] = useState(false);
  const [keyword, setKeyword] = useState('');
  const [searchResult, setSearchResult] = useState([]);
  const [searchInput, setSearchInput] = useState('');
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
        setKeyword={setKeyword}
        setSearchResult={setSearchResult}
        setSearchInput={setSearchInput}
      />
      <OOTDFeed
        filtering={filtering}
        searching={searching}
        page={page}
        keyword={keyword}
        searchResult={searchResult}
        searchInput={searchInput}
        setPage={setPage}
        setFiltering={setFiltering}
        setKeyword={setKeyword}
        setSearching={setSearching}
        setSearchResult={setSearchResult}
        setSearchInput={setSearchInput}
      />
    </div>
  );
}

export default OOTDMain;
