import React from 'react';
import dp from '../dummy_dp.svg';
import { FaHeart } from 'react-icons/fa';
import { FaRegComments, FaEllipsisH } from 'react-icons/fa';
import { Link } from 'react-router-dom';

function DiscussionList (props) {

    //const history = useHistory();
    //const openDicussion = (id) => {history.push("/discussion/"+id)};

    return (
        <>
            {props.discussions.map(item => (

                <Link className="card border-0 shadow my-4 text-decoration-none text-dark" to={`/discussion/${item.id}`} key={item.id}>
                    <div className="card-body">
                        <div className="row">
                            <div className="col-2 col-md-1 col-lg-1">
                                <img src={dp} width={80} height={80} className="rounded-circle img-fluid border" alt="Profile"/>
                            </div>
                            <div className="col-10 col-md-8 col-lg-8">
                                <h3>{item.title}</h3>
                                <p>{item.description.substring(0, 150)} ...</p>
                            </div>
                            <div className="col-md-3 col-sm-12 text-right">
                                <img src={dp} width={30} height={30} className="rounded-circle border" alt="Profile"/>
                                <img src={dp} width={30} height={30} className="rounded-circle border" alt="Profile"/>
                                <img src={dp} width={30} height={30} className="rounded-circle border" alt="Profile"/>
                                <FaEllipsisH className="mx-1 text-secondary"/>
                                <div className="mt-3 small text-secondary">
                                    <span className="mx-1"><FaHeart className="text-danger"/> <span> {item.likeIds != null ? item.likeIds.length : 0} </span> Likes </span>
                                    <span className="mx-1"><FaRegComments /> <span> {item.commentIds != null ? item.commentIds.length : 0} </span> Comments </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </Link>

            ))}
            
        </>
    );
}

export default DiscussionList;