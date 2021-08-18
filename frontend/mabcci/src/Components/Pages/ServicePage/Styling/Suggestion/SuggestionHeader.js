import React from 'react';

const SuggestionHeader = ({ suggestion }) => {
  console.log(suggestion);

  return (
    <header className="suggesion-header">
      <div className="suggesion-mabcci">
        <h4>STYLING</h4>
        <h2> MABCCI</h2>
      </div>
    </header>
  );
};

export default SuggestionHeader;
