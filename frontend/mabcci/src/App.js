import React, { useState, useEffect } from 'react';
import './App.css';

// Router
import { Switch, Route } from 'react-router-dom';

// Redux
import { useSelector } from 'react-redux';

// Navbar
import Nav from './Components/Common/Navbar';
// Pages - ServicePage
import Home from './Components/Pages/ServicePage/Home/Home';
import CommunityMain from './Components/Pages/ServicePage/Community/CommunityMain/CommunityMain';
import MyPageMain from './Components/Pages/ServicePage/MyPage/MyPageMain/MyPageMain';
import OOTDMain from './Components/Pages/ServicePage/OOTD/OOTDMain/OOTDMain';
import OOTDDetail from './Components/Pages/ServicePage/OOTD/OOTDDetail/OOTDDetail';
import OOTDWrite from './Components/Pages/ServicePage/OOTD/OOTDWrite/OOTDWrite';
import StylingMain from './Components/Pages/ServicePage/Styling/StylingMain/StylingMain';
import OOTDUpdate from './Components/Pages/ServicePage/OOTD/OOTDWrite/OOTDUpdate';
import MakeSuggestion from './Components/Pages/ServicePage/Styling/MakeSuggestion/MakeSuggestion';
import Suggestion from './Components/Pages/ServicePage/Styling/Suggestion/Suggestion';
// Pages - AuthPage
import Login from './Components/Pages/AuthPage/Login/Login';
import Signup from './Components/Pages/AuthPage/Signup/Signup';
// Pages - InitialPage
import Description from './Components/Pages/InitialPage/Description/Description';
import Intro from './Components/Pages/InitialPage/Intro/Intro';
// Pages - MabcciChat
import MabcciChat from './Components/Pages/ServicePage/Styling/MabcciChat/MabcciChat';

import { PrivateRoute, PublicRoute } from './CustomRouter';

function App() {
  // 로그인 여부 redux까지 연결 되어야함
  const [isLoggedin, setisLoggedin] = useState(false);

  useEffect(() => {
    const localLoinToken = localStorage.getItem('accessToken');
    if (localLoinToken) {
      setisLoggedin(true);
    } else {
      setisLoggedin(false);
    }
  });

  const loginRedux = useSelector(state => state.LoginReducer);

  return (
    <div className="App">
      {isLoggedin ? <Nav /> : null}

      <Switch>
        <Route exact path="/intro" component={Intro} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/signup" component={Signup} />
        <Route exact path="/desc" component={Description} />
        <Route exact path="/" component={Home} />
        <Route exact path="/home" component={Home} />
        <Route exact path="/community" component={CommunityMain} />
        <Route exact path="/mypage/:nickname" render={() => <MyPageMain />} />
        <Route exact path="/OOTD" component={OOTDMain} />
        <Route exact path="/OOTDWrite" component={OOTDWrite} />
        <Route exact path="/OOTD/:id/:nickname" component={OOTDDetail} />
        <Route exact path="/styling" component={StylingMain} />
        <Route exact path="/OOTDUpdate/:id/:nickname" component={OOTDUpdate} />
        <Route exact path="/chat" component={MabcciChat} />
        <Route exact path="/makeSuggestion" component={MakeSuggestion} />
        <Route exact path="/suggestion/:id" component={Suggestion} />
      </Switch>
    </div>
  );
}

export default App;
