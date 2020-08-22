import React, { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import PageWrapper from '../components/PageWrapper';

function StartDiscussionPage (props) {

    const username = props.username;
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const history = useHistory();

    const createPost = (e) => {
        e.preventDefault();

        fetch('http://localhost:8090/posts', {
            method: 'POST',
            headers: {'Content-Type' : 'application/json'},
            body: JSON.stringify({
                'title': title,
                'description': description,
                'username': username
            })
        })
        .then(response => response.json())
        .then(item => {
            history.push(`/discussion/${item.id}`);
        })
        .catch(e => {alert('Couldn\'t create post. Please try again!')});

        setTitle('');
        setDescription('');
    };

    return (
        <PageWrapper>
            <div className="row">
            <div className="col-md-2"></div>
            <div className="col-md-8 col-sm-12">
                <div className="card shadow">
                    <div className="card-body p-4">
                        <form action="" method="POST" onSubmit={e => createPost(e)}>
                            <div className="form-group">
                                <h4>Title</h4>
                                <input type="text" className="form-control-lg border bg-light w-100" placeholder="Enter Title" onChange={e => setTitle(e.target.value)}/>
                            </div>
                            <div className="form-group">
                                <h4>Description</h4>
                                <textarea className="form-control border bg-light" placeholder="Enter Description" rows={10} onChange={e => setDescription(e.target.value)}/>
                            </div>
                            <div className="text-center my-2">
                                <button type="submit" className="btn  btn-primary m-1">Create Discussion</button>
                                <Link to="/" className="btn btn-danger m-1">Cancel</Link>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div className="col-md-2"></div>
            </div>
        </PageWrapper>
    );
}

export default StartDiscussionPage;