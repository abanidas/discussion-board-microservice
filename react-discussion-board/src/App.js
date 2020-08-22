import React, { useState } from 'react';
import './css/App.css';
import FixedNavBar from './components/FixedNavBar';
import HomePage from './pages/HomePage';
import StartDiscussionPage from './pages/StartDiscussionPage';
import DiscussionPage from './pages/DiscussionPage';
import { BrowserRouter as Router, Switch, Route, Redirect, useHistory } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';

function App() {
  const [authenticated, setAuthenticated] = useState(false);
  //const [username, setUsername] = useState(null);
  const [user, setUser] = useState({});
  const history = useHistory();

  const handleLogin = (username, password) => {
    
    //TODO: username, password authentication
    
    if(username == null){
      setAuthenticated(false);
    }
    else{
      fetch('http://localhost:8090/users/'+username)
      .then(response => response.json())
      .then(item => {
        setUser(item);
        setAuthenticated(item.username != null && item.username !== '');
      })
      .catch(() => {
        alert('Invalid User!')
      });
    }
  };

  const handleSignUp = (username) => {
    history.push("/");
  };
  

  return (

    <Router>
      {authenticated ? <FixedNavBar handleLogin={handleLogin}/> : ''}
      <Switch>
        <Route path="/" render={() => (!authenticated ? <LoginPage handleLogin={handleLogin}/> : <Redirect to="/home" />)} exact />
        <Route path="/signup" render={() => (!authenticated ? <SignUpPage handleSignUp={handleSignUp}/> : <Redirect to="/home" />)} />
        <Route path="/home" render={() => (authenticated ? <HomePage /> : <Redirect to="/" />)} />
        <Route path="/new" render={() => (authenticated ? <StartDiscussionPage username={user.username}/> : <Redirect to="/" />)} />
        <Route path="/discussion/:id" render={(request) => (authenticated ? <DiscussionPage request={request} user={user}/> : <Redirect to="/" />)} />
      </Switch>
    </Router>
  );
}

export default App;
