import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

import { SuggestionReadUrl } from '../../../../../API/ApiUrl';
import './Suggestion.css';
import SuggestionHeader from './SuggestionHeader';
import SuggestionItem from './SuggestionItem';
import SuggestionComment from './SuggestionComment';
import SuggestionFooter from './SuggestionFooter';

const Suggestion = () => {
  const suggetsionId = useParams();
  const [suggestion, setSuggestion] = useState();

  useEffect(async suggestionId => {
    const response = await SuggestionReadUrl(suggestionId);
    setSuggestion(response.suggestion);
  });

  return (
    <div className="container suggestion-container">
      <SuggestionHeader suggesion={suggestion} />
      <SuggestionItem suggesion={suggestion} />
      <SuggestionComment suggesion={suggestion} />
      <SuggestionFooter suggesion={suggestion} />
    </div>
  );
};

export default Suggestion;
