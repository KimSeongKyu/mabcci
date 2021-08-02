import React from 'react';
import { Redirect } from 'react-router-dom';

const isLoggedin = localStorage.getItem('accessToken');

export const PrivateRoute = ({ component: Component }) => {
  return isLoggedin ? <Component /> : <Redirect to="/intro" />;
};

export const PublicRoute = ({ component: Component }) => {
  return isLoggedin ? <Redirect to="/home" /> : <Component />;
};
