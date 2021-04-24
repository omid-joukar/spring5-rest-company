import React, { Component } from 'react';
import Slider from 'react-slick';
import{loadSliders} from '../../Store/Actions/slider_actions';
import {connect} from 'react-redux';
const settings = {
    arrows: false,
    dots: false,
    infinite: true,
    speed:500,
    slidesToShow: 1,
    slidesToScroll: 1
}

class HomeSlider extends Component{

    componentDidMount(){
        this.props.dispatch(loadSliders())
    }


render(){
    console.log(this.props.state.slides)
    return(
        <>
        
         {
            this.props.state.slides ?
            <Slider {...settings}>
                {
                this.props.state.slides.map( item =>(
                    <div className="slide-container  p-0 m-0 col-sm-12" key={item.slideUrl}>
                        <div className= "item_slider"
                        style={{
                           background: `url(/images/slider/${item.cover}) no-repeat`
                        }}>
                           <div className="caption">
                               <h4>{item.topic}</h4>
                               <p>{item.title}</p>
                           </div>
                        </div>
                    </div>
                ))
                }
            </Slider>
            :
            null
        } 
        </>
    )
}
}
function mapStateToProps(state){
    return{
    state:state.slider
    }
}
export default connect(mapStateToProps)(HomeSlider);