import React from 'react';
import logo from '../logo.svg';
import dp from '../dummy_dp.svg';
import { FaRegEnvelope } from 'react-icons/fa';
import { Link } from 'react-router-dom';

function FixedNavBar (props) {
    return (
        <nav className="navbar fixed-top navbar-light bg-light shadow-sm">
            <div className="container-fluid justify-content-between">
                <Link className="navbar-brand" to="/">
                    <img src={logo} width={50} height={50} className="d-inline-block" alt="Logo"/> <b>Discussion App</b>
                </Link>
                <div>
                    <div className="dropdown float-right">
                        <div className="dropdown-toggle" data-toggle="dropdown">
                            <img type="button" src={dp} width={40} height={40} className="rounded-circle border img-fluid" alt="Profile" />
                        </div>
                        <div className="dropdown-menu dropdown-menu-right p-0">
                            <div className="dropdown-item" onClick={e => props.handleLogin(null)}>Logout</div>
                        </div>
                    </div>
                    <div className="dropdown mx-2 float-right">
                        <h5 className="" data-toggle="dropdown"><FaRegEnvelope/></h5>
                        
                        <div className="dropdown-menu dropdown-menu-right p-0">
                            <Link className="dropdown-item py-2 small" to="/notification"> 
                                <span>
                                    <strong>Notification Title</strong><br/>
                                    New Notification
                                </span>
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    );
}

export default FixedNavBar;