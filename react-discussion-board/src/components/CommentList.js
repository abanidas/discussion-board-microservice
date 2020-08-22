import React from 'react';
import dp from '../dummy_dp.svg';
import { FaRegHeart } from 'react-icons/fa';

function CommentList (props) {

    if(props.comments === null || props.comments.length === 0){
        return (
            <div className="card mt-2">
                <div className="card-header border-0">
                    <h6>No Comments Available</h6>
                </div>
            </div>
        );
    }
    
    return (
        <>
            {props.comments.map(item => (
                <div className="card mt-2" key={item.id}>
                    <div className="card-body">
                        <div className="row">
                            <div className="col-2 col-md-1 col-lg-1">
                                <img src={dp} width={50} height={50} className="rounded-circle img-fluid border m-2" alt="Profile"/>
                            </div>
                            <div className="col-10 col-md-11 col-lg-11">
                                <p className="text-justify">
                                    {item.comment}
                                </p>
                                <label>@{item.username}</label>
                            </div>
                        </div>
                        <div className="text-right mt-2">
                            <div className="text-secondary">
                                <span className="mx-1"><FaRegHeart className="text-danger"/> <span> {item.likeIds != null ? item.likeIds.length : 0} </span> Likes </span>
                            </div>
                        </div>
                    </div>
                </div>
            ))}
        </>
    );
}

export default CommentList;