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
import OOTDWrite2 from './Components/Pages/ServicePage/OOTD/OOTDWrite/OOTDWrite2';
import StylingMain from './Components/Pages/ServicePage/Styling/StylingMain/StylingMain';
// Pages - AuthPage
import Login from './Components/Pages/AuthPage/Login/Login';
import Signup from './Components/Pages/AuthPage/Signup/Signup';
// Pages - InitialPage
import Description from './Components/Pages/InitialPage/Description/Description';
import Intro from './Components/Pages/InitialPage/Intro/Intro';

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

        <Route exact path="/mypage" component={MyPageMain} />

        <Route exact path="/OOTD" component={OOTDMain} />

        <Route exact path="/OOTDDetail/:id/:nickname" component={OOTDDetail} />

        <Route exact path="/OOTDWrite" componet={OOTDWrite} />

        <Route exact path="/styling" component={StylingMain} />
      </Switch>
    </div>
  );
}

export default App;
