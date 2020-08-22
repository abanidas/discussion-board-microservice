import React, { useState, useEffect } from 'react';
import { FaRegComments, FaRegHeart } from 'react-icons/fa';
import PageWrapper from '../components/PageWrapper';
import { Link } from 'react-router-dom';
import CommentList from '../components/CommentList';

function DiscussionPage (props) {
    const postId = props.request.match.params.id;
    const username = props.user.username;
    window.scrollTo(0, 0);
    
    const [discussion, setDiscussion] = useState({description:''});
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState('');

    useEffect(() => {
      fetch('http://localhost:8090/posts/'+postId)
      .then(response => response.json())
      .then(setDiscussion)
      .catch(console.error)
    }, [postId]);


    useEffect(() => {
      fetch('http://localhost:8090/comments/posts/'+postId)
      .then(response => response.json())
      .then(setComments)
      .catch(console.error)
    }, [postId]);

    const addComment = (e) => {
        e.preventDefault();
        fetch('http://localhost:8090/comments', {
            method: 'POST',
            headers: {'Content-Type' : 'application/json'},
            body: JSON.stringify({
                'postId': postId,
                'username': username,
                'comment': newComment
            })
        })
        .then(response => response.json())
        .then(item => {
            //TODO: Update UI with the new comment
            /*let oldComments = comments;
            oldComments.shift(item);
            setComments(oldComments);*/
        })
        .catch(e => {alert('Couldn\'t add comment. Please try again!')});

        setNewComment('');
    };
    
    return (
        <PageWrapper>
            <div className="row">
                <div className="col-md-8 col-sm-12">
                    <div className="card">
                        <div className="card-body">
                            <div className="row">
                                <div className="col-2 col-md-1 col-lg-1">
                                    <img src={props.user.imgUrl} width={50} height={50} className="rounded-circle img-fluid border" alt="Profile"/>
                                </div>
                                <div className="col-10 col-md-11 col-lg-11">
                                    <h5 className="m-0">{discussion.title}</h5>
                                    <span className="small text-secondary">@{discussion.username}</span>
                                </div>
                            </div>
                            <div className="mx-2 my-4 text-justify">
                                {discussion.description.split('\n').map((item, idx) => (
                                    <p key={idx}>{item}</p>
                                ))}
                            </div>
                            <div className="text-right mt-2">
                                <div className="text-secondary">
                                    <span className="mx-1"><FaRegHeart className="text-danger"/> <span> {discussion.likeIds != null ? discussion.likeIds.length : 0} </span> Likes </span>
                                    <span className="mx-1"><FaRegComments /> <span> {discussion.commentIds != null ? discussion.commentIds.length : 0} </span> Comments </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="card mt-4 border-0">
                        <div className="card-header border-0">
                            <h5>Responses</h5>
                        </div>
                        <div className="card-body px-0">
                            <div className="card">
                                <div className="card-body">
                                    <form method="POST" onSubmit={e => addComment(e)}>
                                        <div className="row">
                                            <div className="col-2 col-md-1 col-lg-1">
                                                <img src={props.user.imgUrl} width={50} height={50} className="rounded-circle img-fluid border m-2" alt="Profile"/>
                                            </div>
                                            <div className="col-10 col-md-11 col-lg-11">
                                                <textarea className="form-control border-1 bg-light m-2 w-justify" placeholder="Join Conversation..." onChange={e => setNewComment(e.target.value)}/>
                                            </div>
                                        </div>
                                        <div className="text-right">
                                            <button type="submit" className="btn btn-outline-primary">Start Discussion</button>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <CommentList comments={comments}/>
                        </div>
                    </div>
                </div>
                <div className="col-md-4 col-sm-12">
                    <div className="card border-0">
                        <div className="card-header bg-white border">
                            <h5>Similar Threads</h5>
                        </div>
                        <div className="card-body border">
                            <Link to="/discussion">Lorem ipsum dolor sit amet, consectetur adipiscing elit</Link>
                            <hr/>
                            <Link to="/discussion">Lorem ipsum dolor sit amet, consectetur adipiscing elit</Link>
                            <hr/>
                            <Link to="/discussion">Lorem ipsum dolor sit amet, consectetur adipiscing elit</Link>
                            <hr/>
                            <Link to="/discussion">Lorem ipsum dolor sit amet, consectetur adipiscing elit</Link>
                            <hr/>
                            <Link to="/discussion">Lorem ipsum dolor sit amet, consectetur adipiscing elit</Link>
                            <hr/>
                            <Link to="/discussion">Lorem ipsum dolor sit amet, consectetur adipiscing elit</Link>
                            <hr/>
                        </div>
                    </div>
                </div>
            </div>
        </PageWrapper>
    );
}

export default DiscussionPage;