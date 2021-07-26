import React from 'react';
import './App.css';

// Router
import { Route } from 'react-router-dom';

// Navbar
import Nav from './Components/Common/Navbar';
// Pages - ServicePage
import Home from './Components/Pages/ServicePage/Home/Home';
import CommunityMain from './Components/Pages/ServicePage/Community/CommunityMain/CommunityMain';
import MyPageMain from './Components/Pages/ServicePage/MyPage/MyPageMain/MyPageMain';
import OOTDMain from './Components/Pages/ServicePage/OOTD/OOTDMain/OOTDMain';
import StylingMain from './Components/Pages/ServicePage/Styling/StylingMain/StylingMain';
// Pages - AuthPage
import Login from './Components/Pages/AuthPage/Login/Login';
import Signup from './Components/Pages/AuthPage/Signup/Signup';

function App() {
  return (
    <div className="App">
      <Nav />

      <Route exact path="/home">
        <Home />
      </Route>

      <Route exact path="/community">
        <CommunityMain />
      </Route>

      <Route exact path="/mypage">
        <MyPageMain />
      </Route>

      <Route exact path="/OOTD">
        <OOTDMain />
      </Route>

      <Route exact path="/styling">
        <StylingMain />
      </Route>

      <Route exact path="/login">
        <Login />
      </Route>

      <Route exact path="/signup">
        <Signup />
      </Route>
    </div>
  );
}

export default App;
