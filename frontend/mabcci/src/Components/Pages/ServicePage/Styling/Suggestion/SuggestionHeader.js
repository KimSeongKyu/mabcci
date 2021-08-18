import React from 'react';
import { useSelector } from 'react-redux';

const SuggestionHeader = () => {
  const suggestion = useSelector(state => state.SuggestionReducer);

  return (
    <header className="suggesion-header">
      <div className="suggesion-mabcci">
        <h4>STYLING</h4>
        <h2>{suggestion.mabcciNickname} MABCCI</h2>
      </div>
    </header>
  );
};

export default SuggestionHeader;
