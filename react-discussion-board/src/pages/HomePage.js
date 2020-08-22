import React, { useEffect, useState } from 'react';
import DiscussionList from '../components/DiscussionList';
import { Link } from 'react-router-dom';
import PageWrapper from '../components/PageWrapper';

function HomePage () {
  
    const [discussions, setDiscussions] = useState([]);

    useEffect(() => {
      fetch('http://localhost:8090/posts')
      .then(response => response.json())
      .then(setDiscussions)
      .catch(console.error)
    }, []);

    return (
        <PageWrapper>
          <div className="row">
            <div className="col-sm-1"></div>
            <div className="col-md-10 col-sm-12">
              <Link to="/new" className="btn btn-primary shadow my-2">Start New Discussion</Link>
              <div className="row my-4">
                <div className="col-sm-6">
                <form className="form-inline" action="">
                    <input type="text" className="form-control" placeholder="Find Discussion" id="searchInput" autoComplete="off"/>
                </form>
                </div>
                <div className="col-sm-6 text-right">
                  <select className="btn btn-light m-1">
                    <option>Most Recent</option>
                    <option>Relevant</option>
                  </select>
                </div>
              </div>
              <DiscussionList discussions={discussions}/>
            </div>
            <div className="col-sm-1"></div>
          </div>
        </PageWrapper>
    );
}

export default HomePage;