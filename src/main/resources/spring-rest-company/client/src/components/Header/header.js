import React, { Component } from 'react';
import {Link, NavLink} from 'react-router-dom';
import {CSSTransition, TransitionGroup} from 'react-transition-group';
import  '../../css/App.css';

class Header extends Component{

    state = {
    webShopShow:false,
    loginShow:false
    }
    showWebShopShow = ()=>{
        const line  = document.querySelector('.line');
        let loginValue = false;
        if (this.state.loginShow === true){
          this.setState({
              webShopShow:!this.state.webShopShow,
              loginShow: false
          })}else{  
        this.setState({
            webShopShow:!this.state.webShopShow,
            loginShow: loginValue
            });
        }
        if(!this.state.loginShow){
            line.textContent="|";
        }else{
            line.textContent=" ";
        }
        
    }
    showloginshow = () =>{
        const line  = document.querySelector('.line');
        let webShopValue = false;
        if (this.state.webShopShow === true){
          this.setState({
              webShopShow:false,
              loginShow: !this.state.loginShow
          })
        }else{  
        this.setState({
            webShopShow:webShopValue,
            loginShow: !this.state.loginShow
            });
        }
        if(this.state.loginShow){
            line.textContent="|";
        }else{
            line.textContent=" ";
        }
    }
render(){

    return(
        <>
            <header>
                <div className="container  col-sm-12 py-4">
                 <div className="row col-sm-12 ">
                    <Link to="/" className="logo col-sm-8 text-white text-decoration-none">
                    antigypt<span className="text-success">market</span>
                    </Link>
        
                <nav>
                     <CSSTransition
                     in={this.state.loginShow}
                     timeout={500}
                     classNames="login-icon">
                        <Link 
                        to="/login" 
                        onClick={this.showloginshow}
                        className={`login-icon ${this.state.loginShow} clo-sm-2 text-decoration-none `}>
                            <i class="fas fa-user"></i>
                        </Link>
                     </CSSTransition> 
                     <CSSTransition
                     in={this.state.loginShow}
                     timeout={500}
                     classNames="login">
                        <Link 
                        to="/login" 
                        onClick={this.showloginshow}
                        className={`login ${this.state.loginShow} clo-sm-2 text-decoration-none `}>
                            <span className="line" style={{padding:'5px'}}>|</span>
                            Login
                        </Link>
                     </CSSTransition> 
                      
                     <CSSTransition
                     in={this.state.webShopShow}
                     timeout={500}
                     classNames="web-shop">
                        <Link  
                        to="/webshop" 
                        className={`web-shop ${this.state.webShopShow}  px-4 col-sm-2  text-decoration-none`}
                        onClick={this.showWebShopShow}><font className="webshop-text">Start your webshop</font>
                        </Link> 
                     </CSSTransition>
                </nav>
            </div>
            </div>
        </header>
    </>
    )
    }
}
export default Header;