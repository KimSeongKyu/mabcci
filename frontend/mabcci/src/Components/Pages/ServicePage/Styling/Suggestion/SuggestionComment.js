import React from 'react';
import { useSelector } from 'react-redux';

const SuggestionComment = () => {
  const suggestion = useSelector(state => state.SuggestionReducer);

  return (
    <article className="suggestion-comment">
      <p>{suggestion.description}</p>
    </article>
  );
};

export default SuggestionComment;
