import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';
import {URL_POSTS } from '../utils/pathes';



class Posts extends Component{

    state={
        posts:[]
    }

    getPosts = async() =>{

       const response = await axios.get(URL_POSTS);
       const limitPosts = response.data.posts.slice(response.data.posts.length-2,response.data.posts.length)
        this.setState({
            posts:limitPosts
        })
    }

    componentDidMount(){
        this.getPosts();
    }
    render(){
        
        return(
            <>
            <div className="post-title d-flex justify-content-center  ">THIS IS WHY YOU CHOOSE SHOP ANTIGYPT</div>
            {
                this.state.posts ?
                <div className="sale-container  d-flex flex-row justify-content-around flex-wrap align-content-center ">
                                {
                                    this.state.posts.map( item =>(
                                       
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
                                                <p className="mt-3">{item.content}</p>
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
export default Posts;