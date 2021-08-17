import React from 'react';
import './Suggestion.css';
import SuggestionHeader from './SuggestionHeader';
import SuggestionItem from './SuggestionItem';
import SuggestionComment from './SuggestionComment';

const Suggestion = () => {
  return (
    <div className="container suggestion-container">
      <SuggestionHeader />
      <SuggestionItem />
      <SuggestionComment />
    </div>
  );
};

export default Suggestion;
