import React from 'react';
import { useSelector } from 'react-redux';

const SuggestionFooter = () => {
  const suggestion = useSelector(state => state.SuggestionReducer);

  return (
    <footer className="suggesion-footer">
      <div className="suggesion-user">
        <h4>For</h4>
        <h2>{suggestion.targetMemberNickname}</h2>
      </div>
    </footer>
  );
};

export default SuggestionFooter;
