import React from 'react';
import './Suggestion.css';
import SuggestionHeader from './SuggestionHeader';
import SuggestionItem from './SuggestionItem';
import SuggestionComment from './SuggestionComment';
import SuggestionFooter from './SuggestionFooter';

const Suggestion = () => {
  return (
    <div className="container suggestion-container">
      <SuggestionHeader />
      <SuggestionItem />
      <SuggestionComment />
      <SuggestionFooter />
    </div>
  );
};

export default Suggestion;
