import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import SaleHeader from './saleHeader';
import {connect} from 'react-redux';
import {loadSales} from '../../Store/Actions/sale_actions';


class Sale extends Component {
    
    state = {
        firstSale:{
            object : {},
            active:false,     
        },
        secondSale:{
            object : {},
            active:false,
        },
        thirdSale:{
            object : {},
            active:false,   
        },
        fourthSale:{
            object : {},
            active:false,   
        }
    }

    handleMouseOver = (event,saleUrl)=>{
        
        switch(saleUrl){
            case "/api/v1/sales/1":{
                if(event.target.className === 'item-percent' && !this.state.firstSale.active){
                    this.state.firstSale.active = true;
                    const percent = Number(this.state.firstSale.object.percent);
                    let start =0;
                    const timer = setInterval(()=>{
                        event.target.innerHTML = `<span class="mr-3">SALE</span>  ${start}%`;
                        if(start === percent){
                            clearInterval(timer);
                        }else{
                            start++;
                        }
                    },15);
                }
                if(!this.state.firstSale.active){ 
                this.state.firstSale.active = true;
                const percent = Number(this.state.firstSale.object.percent);
                let start =0;
                if(event.target.className === 'sale-item'){
                        const timer = setInterval(()=>{
                            event.target.querySelector('div').innerHTML = `<span class="mr-3">SALE</span>  ${start}%`;
                            if(start === percent){
                                clearInterval(timer);
                            }else{
                                start++;
                            }
                        },15);
                    }else if(event.target.className === 'item-percent' && this.state.firstSale.active){

                    }
                }else if(this.state.firstSale.active){

                } 
               
                    break;
                }
                case "/api/v1/sales/2":{
                    if(event.target.className === 'item-percent' && !this.state.secondSale.active){
                        this.state.secondSale.active = true;
                        const percent = Number(this.state.secondSale.object.percent);
                        let start =0;
                        const timer = setInterval(()=>{
                            event.target.innerHTML = `<span class="mr-3">SALE</span>  ${start}%`;
                            if(start === percent){
                                clearInterval(timer);
                            }else{
                                start++;
                            }
                        },15);
                    }
                    if(!this.state.secondSale.active){ 
                    this.state.secondSale.active = true;
                    const percent = Number(this.state.secondSale.object.percent);
                    let start =0;
                    if(event.target.className === 'sale-item'){
                            const timer = setInterval(()=>{
                                event.target.querySelector('div').innerHTML = `<span class="mr-3">SALE</span>  ${start}%`;
                                if(start === percent){
                                    clearInterval(timer);
                                }else{
                                    start++;
                                }
                            },15);
                        }else if(event.target.className === 'item-percent' && this.state.secondSale.active){
    
                        }
                    }else if(this.state.secondSale.active){
    
                    } 
                   
                        break;
                    }
                    case "/api/v1/sales/3":{
                        if(event.target.className === 'item-percent' && !this.state.thirdSale.active){
                            this.state.thirdSale.active = true;
                            const percent = Number(this.state.thirdSale.object.percent);
                            let start =0;
                            const timer = setInterval(()=>{
                                event.target.innerHTML = `<span class="mr-3">SALE</span>  ${start}%`;
                                if(start === percent){
                                    clearInterval(timer);
                                }else{
                                    start++;
                                }
                            },15);
                        }
                        if(!this.state.thirdSale.active){ 
                        this.state.thirdSale.active = true;
                        const percent = Number(this.state.thirdSale.object.percent);
                        let start =0;
                        if(event.target.className === 'sale-item'){
                                const timer = setInterval(()=>{
                                    event.target.querySelector('div').innerHTML = `<span class="mr-3">SALE</span>  ${start}%`;
                                    if(start === percent){
                                        clearInterval(timer);
                                    }else{
                                        start++;
                                    }
                                },15);
                            }else if(event.target.className === 'item-percent' && this.state.thirdSale.active){
        
                            }
                        }else if(this.state.thirdSale.active){
        
                        } 
                       
                            break;
                        }
                        case "/api/v1/sales/4":{
                            if(event.target.className === 'item-percent' && !this.state.fourthSale.active){
                                this.state.fourthSale.active = true;
                                const percent = Number(this.state.fourthSale.object.percent);
                                let start =0;
                                const timer = setInterval(()=>{
                                    event.target.innerHTML = `<span class="mr-3">SALE</span>  ${start}%`;
                                    if(start === percent){
                                        clearInterval(timer);
                                    }else{
                                        start++;
                                    }
                                },15);
                            }
                            if(!this.state.fourthSale.active){ 
                            this.state.fourthSale.active = true;
                            const percent = Number(this.state.fourthSale.object.percent);
                            let start =0;
                            if(event.target.className === 'sale-item'){
                                    const timer = setInterval(()=>{
                                        event.target.querySelector('div').innerHTML = `<span class="mr-3">SALE</span>  ${start}%`;
                                        if(start === percent){
                                            clearInterval(timer);
                                        }else{
                                            start++;
                                        }
                                    },15);
                                }else if(event.target.className === 'item-percent' && this.state.fourthSale.active){
            
                                }
                            }else if(this.state.fourthSale.active){
            
                            } 
                           
                                break;
                            }
                            default:{
                                console.log(saleUrl);
                            }

        }
    }

    handleMouseLeave = (event,saleUrl) => {
        switch(saleUrl){
            case "/api/v1/sales/1":{
                if(this.state.firstSale.active){ 
                    this.state.firstSale.active = false;
                    if(event.target.className === 'sale-item'){
                        event.target.querySelector('div').textContent = `${this.state.firstSale.object.topic}`;       
                        }   
                        if(event.target.className === 'item-percent' && !this.state.firstSale.active){
                                event.target.textContent = `${this.state.firstSale.object.topic}`;
                            
                        }else if(event.target.className === 'item-percent' && this.state.firstSale.active){

                        }
                    }else if(!this.state.firstSale.active){

                    }
                    break;
                }
                case "/api/v1/sales/2":{
                    if(this.state.secondSale.active){ 
                        this.state.secondSale.active = false;
                        if(event.target.className === 'sale-item'){
                            event.target.querySelector('div').textContent = `${this.state.secondSale.object.topic}`;       
                            }   
                            if(event.target.className === 'item-percent' && !this.state.secondSale.active){
        
                                    event.target.textContent = `${this.state.secondSale.object.topic}`;
                                
                            }else if(event.target.className === 'item-percent' && this.state.secondSale.active){
    
                            }
                        }else if(!this.state.secondSale.active){
    
                        }
                        break;
                    }
                    case "/api/v1/sales/3":{
                        if(this.state.thirdSale.active){ 
                            this.state.thirdSale.active = false;
                            if(event.target.className === 'sale-item'){
                                event.target.querySelector('div').textContent = `${this.state.thirdSale.object.topic}`;       
                                }   
                                if(event.target.className === 'item-percent' && !this.state.thirdSale.active){
                    
                                        event.target.textContent = `${this.state.thirdSale.object.topic}`;
                                    
                                }else if(event.target.className === 'item-percent' && this.state.thirdSale.active){
        
                                }
                            }else if(!this.state.thirdSale.active){
        
                            }
                            break;
                        }
                        case "/api/v1/sales/4":{
                            if(this.state.fourthSale.active){ 
                                this.state.fourthSale.active = false;
                                if(event.target.className === 'sale-item'){
                                    event.target.querySelector('div').textContent = `${this.state.fourthSale.object.topic}`;       
                                    }   
                                    if(event.target.className === 'item-percent' && !this.state.fourthSale.active){
                                            
                                            event.target.textContent = `${this.state.fourthSale.object.topic}`;
                                        
                                    }else if(event.target.className === 'item-percent' && this.state.fourthSale.active){
            
                                    }
                                }else if(!this.state.fourthSale.active){
                                    
                                }
                                break;
                            }
                            default:{
                                console.log(saleUrl);
                            }

        }
    }
    
    componentDidMount(){
                    this.props.dispatch(loadSales())
                   
            }
    componentDidUpdate(){
        this.state.firstSale.object = this.props.state.sales[0]; 
        this.state.secondSale.object = this.props.state.sales[1];
        this.state.thirdSale.object = this.props.state.sales[2];
        this.state.fourthSale.object = this.props.state.sales[3]; 
    }
    

    
    render() {
        console.log(this.props.state);
        return (
            <>
                <SaleHeader/>  
                <div className="container m-auto text-center col-sm-12 my-3 p-2">
                   { 
                        this.props.state.sales ?
                            <div className="sale-container  d-flex flex-row justify-content-around flex-wrap align-content-center">
                                {
                                    this.props.state.sales.map( item =>(
                                        
                                        <Link 
                                        to="" 
                                        className="sale-item" 
                                        key={item.saleUrl} 
                                        style={{background :`url(/images/sales/${item.cover}) no-repeat` }}
                                        onMouseOver={(event,saleUrl)=>this.handleMouseOver(event,item.saleUrl)}
                                        onMouseLeave={(event,saleUrl)=>this.handleMouseLeave(event,item.saleUrl)}
                                        > 
                                            <div className="item-percent">{item.topic}</div> 
                                        </Link>     
                                   
                                    )
                                )}
                            </div>
                        : 
                        null
                    }

                </div>
            </>
        )
    }
}
function mapStateToProps(state){
    return{
        state:state.sale
    }
}
export default connect(mapStateToProps)(Sale);
