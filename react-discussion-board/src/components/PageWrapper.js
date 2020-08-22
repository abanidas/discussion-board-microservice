import React from 'react';

function PageWrapper (props) {
    return (
        <div className="container-fluid" style={{paddingTop: "16vh"}}>
            {props.children}
        </div>
    );
}

export default PageWrapper;