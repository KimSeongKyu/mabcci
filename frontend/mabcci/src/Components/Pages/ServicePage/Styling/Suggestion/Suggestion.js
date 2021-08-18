import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

import SuggestionReadApi from '../../../../../API/SuggestionAPI/SuggestionReadApi';
import './Suggestion.css';
import SuggestionHeader from './SuggestionHeader';
import SuggestionItem from './SuggestionItem';
import SuggestionComment from './SuggestionComment';
import SuggestionFooter from './SuggestionFooter';

const Suggestion = () => {
  const { id } = useParams();
  const [suggestion, setSuggestion] = useState();

  useEffect(async () => {
    const response = await SuggestionReadApi(id);
    setSuggestion(response.suggestion);
  }, []);

  return (
    <div className="container suggestion-container">
      <SuggestionHeader suggestion={suggestion} />
      <SuggestionItem suggestion={suggestion} />
      <SuggestionComment suggestion={suggestion} />
      <SuggestionFooter suggestion={suggestion} />
    </div>
  );
};

export default Suggestion;
