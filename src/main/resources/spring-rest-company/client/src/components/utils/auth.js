import React, { Component } from 'react';
import {userAuth} from '../../Store/Actions/user_actions';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

export default function(ComposedClass){
    class AuthenticationCheck extends Component{
        state={
            loading : true
        }
        componentDidMount(){
                let user  = this.props.state.auth;
                this.setState({loading:false});
                if(!user){
                    this.props.history.push('/login');
                }else{
                    this.props.history.push('/customer');
                }
        }
        render(){
            if(this.state.loading){
                return <div>loading ...</div>
            }else{
            return (
                <ComposedClass {...this.props} user={this.props.state}/>
            )
            }
        }
    }
    function mapStateToProps(state){
        return{
            state:state.user
        }
    }
    const mapDispatchToProps = dispatch =>{
        bindActionCreators({userAuth},dispatch);
    }
    return connect(mapStateToProps,mapDispatchToProps)(AuthenticationCheck);

}