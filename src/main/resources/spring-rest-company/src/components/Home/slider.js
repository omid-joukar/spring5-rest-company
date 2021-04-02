import React, { useState , useEffect } from 'react';
import Slider from 'react-slick';
import axios from 'axios';
import {URL_SLIDES} from '../utils/pathes';
const settings = {
    arrows: true,
    dots: false,
    infinite: false,
    speed:500,
    slidesToShow: 1,
    slidesToScroll: 1
}


const HomeSlider = () =>{
    let [slides,setSlides] = useState([]);
    useEffect( () => {
        const fetchSlides = async ()=> {
           try{
               const response = await axios.get(URL_SLIDES);
               setSlides(response.data)
           } catch(error){
                console.log(error);
           }
        }
        fetchSlides();
    }, [])
console.log(slides);
    return(
        <>
        
         {
            slides ?
            <Slider {...settings}>
                {
                slides.map( item =>(
                    <div className="slide-container pl-0 col-sm-12" key={item.id}>
                        <div className= "item_slider"
                        style={{
                           background: `url(/images/slider/${item.cover}) no-repeat` 
                        }}>
                            
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
export default HomeSlider;