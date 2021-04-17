import React, { Component } from 'react';
import {CSSTransition} from 'react-transition-group';
import '../../css/App.css';


class  SaleHeader extends Component {
       
    state={
        active:false
    }


    render(){
        console.log()
        window.onscroll = ()=> {
            if (document.body.scrollTop > 700 || document.documentElement.scrollTop > 700) {
                this.setState({
                    active:true
                })
            }
        }
        return (
            <CSSTransition
            in={this.state.active}
            timeout={500}
            classNames="slide-up">
                <div  className={`slide-up ${this.state.active}  container col-sm-12 text-center  display-4`}>
                            Sales
                </div>
            </CSSTransition>
        )
    }
}
export default SaleHeader;
