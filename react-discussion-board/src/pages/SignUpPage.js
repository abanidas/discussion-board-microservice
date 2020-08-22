import React, { useState } from 'react';
import '../css/App.css';
import { Link } from 'react-router-dom';

function SignUpPage (props) {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSignUp = (event) => {
        props.handleSignUp(username.trim());
        setName('');
        setEmail('');
        setUsername('');
        setPassword('');
        console.log(name + " " + email + " " + password);
        event.preventDefault();
    };

    return (
        <div className="App">
            <div className="Centered-item alert-success">
                <form onSubmit={handleSignUp}>
                    <div className="card m-2 shadow">
                        <div className="card-header border-0">
                            <h4>Sign up to start discussion</h4>
                        </div>
                        <div className="card-body p-4">
                            <div className="form-group text-left h5">
                                <label htmlFor="username">Name</label>
                                <input type="text" className="form-control-lg border bg-light w-100" placeholder="Enter Your Name" onChange={e => setName(e.target.value)}/>
                            </div>
                            <div className="form-group text-left h5">
                                <label htmlFor="username">Email</label>
                                <input type="email" className="form-control-lg border bg-light w-100" placeholder="Enter Your Email" onChange={e => setEmail(e.target.value)}/>
                            </div>
                            <div className="form-group text-left h5">
                                <label htmlFor="username">Username</label>
                                <input type="text" className="form-control-lg border bg-light w-100" placeholder="Enter Username" onChange={e => setUsername(e.target.value)}/>
                            </div>
                            <div className="form-group text-left h5">
                                <label htmlFor="password">Password</label>
                                <input type="password" className="form-control-lg border bg-light w-100" placeholder="Enter Password" onChange={e => setPassword(e.target.value)}/>
                            </div>

                            <div className="m-4">
                                <button type="submit" className="btn btn-outline-secondary m-1">Signup</button>
                                <Link to="/" className="btn btn-outline-primary m-1">Login</Link>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default SignUpPage;