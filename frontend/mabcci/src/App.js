import React, { useState, useEffect } from 'react';
import './App.css';

// Router
import { Switch } from 'react-router-dom';

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
        <PublicRoute exact path="/intro" component={Intro} />

        <PublicRoute exact path="/login" component={Login} />

        <PublicRoute exact path="/signup" component={Signup} />

        <PublicRoute exact path="/desc" component={Description} />

        <PrivateRoute exact path="/" component={Home} />

        <PrivateRoute exact path="/home" component={Home} />

        <PrivateRoute exact path="/community" component={CommunityMain} />

        <PrivateRoute exact path="/mypage" component={MyPageMain} />

        <PrivateRoute exact path="/OOTD" component={OOTDMain} />

        <PrivateRoute exact path="/OOTDDetail" component={OOTDDetail} />

        <PrivateRoute exact path="/OOTDWrite" componet={OOTDWrite} />

        <PrivateRoute exact path="/styling" component={StylingMain} />
      </Switch>
    </div>
  );
}

export default App;
