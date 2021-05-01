import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {loadPosts} from '../../Store/Actions/post_actions';
import {connect} from 'react-redux';



class Posts extends Component{



    componentDidMount(){
        this.props.dispatch(loadPosts())
    }
    render(){
        
        return(
            <>
            <div className="post-title d-flex justify-content-center  ">THIS IS WHY YOU CHOOSE SHOP ANTIGYPT</div>
            {
                this.props.state.posts ?
                <div className="sale-container  d-flex flex-row justify-content-around flex-wrap align-content-center ">
                                {
                                    this.props.state.posts.slice(this.props.state.posts.length-2,this.props.state.posts.length).map( item =>(
                                       
                                        <Link 
                                        to="" 
                                        className="post-item text-decoration-none text-dark " 
                                        key={item.postUrl} 
                                        > 
                                            <div className="icon">
                                                <div className="icon-image" dangerouslySetInnerHTML={{
                                                    __html:item.cover
                                                }}></div>
                                            </div>    
                                                

                                                <h4 className="mt-3">{item.topic}</h4> 
                                                <p className="mt-3 text-dark">{item.content}</p>
                                        </Link> 
                                       
                                    
                                    )
                                )}
                            </div>
                        : 
                        null
                    }
            </>
        )
    }
}
function mapStateToProps(state){
    return{
        state:state.post
    }
}
export default connect(mapStateToProps)(Posts);