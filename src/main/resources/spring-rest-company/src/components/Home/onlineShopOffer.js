import React, { Component } from 'react';
import{Link,NavLink} from 'react-router-dom';
import {Transition} from 'react-transition-group';
import '../../css/App.css';

class OnlineShopOffer extends Component {
    
    state ={
        wine:false,
        haushold:true,
        grocery:false
    }
    updateState(event){
        if(event.target.textContent === 'GROCERIES'){
            this.setState({
                wine:false,
                haushold:false,
                grocery:true
            })

        }else if(event.target.textContent === 'HOUSEHOLD'){
            this.setState({
                wine:false,
                haushold:true,
                grocery:false
            })

        }else if(event.target.textContent === 'WINEWORLD'){
            this.setState({
                wine:true,
                haushold:false,
                grocery:false
            })

        }
    }
    
    render() {

        let boldStyle = {
            fontWeight:'600',
            borderBottom: '4px solid #d42234'
        }
        let normStyle ={
            borderBottom: '4px solid #d3d2d2'
        }


        return (
            <>
            <div className="container p-3 my-3 ">
                <div className="col-sm-12 text-center offer-title">OFFERS FROM OUR ONLINESHOPS</div>
                    <nav>
                        <div className="col-sm-12 d-flex my-4 flex-row justify-content-center align-items-center offer-flex">
                            
                            <NavLink 
                            to="/sadadad"
                            className="text-decoration-none px-5 py-1 text-dark"
                            style={this.state.grocery ? boldStyle : normStyle}
                            activeStyle={{
                                fontWeight:'600',
                                borderBottom: '4px solid #d42234'
                            }}
                            onClick={(event)=>this.updateState(event)}>
                                GROCERIES
                            </NavLink>
                            
                            <NavLink 
                            to="/asdaaaa"
                            exact={true}
                            className="text-decoration-none px-5 py-1 text-dark"
                            style={this.state.haushold ? boldStyle : normStyle}
                            activeStyle={{
                                fontWeight:'600',
                                borderBottom: '4px solid #d42234'
                            }}
                            onClick={(event)=>this.updateState(event)}>
                                HOUSEHOLD
                            </NavLink>
                            
                            <NavLink 
                            to="asdasd"
                            
                            className="text-decoration-none px-5 py-1 text-dark"
                            style={this.state.wine ? boldStyle : normStyle}
                            activeStyle={{
                                fontWeight:'600',
                                borderBottom: '4px solid #d42234'
                            }}
                            onClick={(event)=>this.updateState(event)}>
                                WINEWORLD
                            </NavLink>
                        </div>
                    </nav> 
            </div>
            <div className="col-sm-12 offer-slider-container ">
                    <Transition
                    in={this.state.grocery}
                    timeout={100}
                    >
                        {state =>(
                        
                            <div className={`grocery ${state}`}>
                                <img className={`${state}`} src="/images/slider/grocery.png"/>
                            </div>
                            )
                        }
                    </Transition>
                    <Transition
                    in={this.state.haushold}
                    timeout={100}
                    >
                        {state =>(
                            <div className={`household ${state}`}>
                                <img className={`${state}`} src="/images/slider/household.png"/>
                            </div>
                            )
                        }
                    </Transition>
                    <Transition
                    in={this.state.wine}
                    timeout={100}
                    >
                        {state =>(
                            <div className={`wine ${state}`}>
                                <img className={`${state}`} src="/images/slider/wine.png"/>
                            </div>
                            )
                        }
                    </Transition>
                </div>
            </>
        )
    }
}
export default OnlineShopOffer;