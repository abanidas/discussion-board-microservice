import React, { useState } from 'react';
import '../css/App.css';
import { Link } from 'react-router-dom';

function LoginPage (props) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (event) => {
        props.handleLogin(username.trim(), password.trim());
        setUsername('');
        setPassword('');
        event.preventDefault();
    };

    return (
        <div className="App">
            <div className="Centered-item alert-success">
                <form onSubmit={handleSubmit}>
                    <div className="card m-2 shadow">
                        <div className="card-header border-0">
                            <h4>Login to join discussion</h4>
                        </div>
                        <div className="card-body p-4">
                            <div className="form-group text-left h5">
                                <label htmlFor="username">Username</label>
                                <input type="text" className="form-control-lg border bg-light w-100" placeholder="Enter Username" onChange={e => setUsername(e.target.value)}/>
                            </div>
                            <div className="form-group text-left h5">
                                <label htmlFor="password">Password</label>
                                <input type="password" className="form-control-lg border bg-light w-100" placeholder="Enter Password" onChange={e => setPassword(e.target.value)}/>
                            </div>

                            <div className="m-4">
                                <button type="submit" className="btn btn-outline-primary m-1">Login</button>
                                <Link to="/signup" className="btn btn-outline-secondary m-1">New User</Link>
                                <p className="small"><Link to="/forgot" className="text-decoration-none">Forgot Password</Link></p>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default LoginPage;