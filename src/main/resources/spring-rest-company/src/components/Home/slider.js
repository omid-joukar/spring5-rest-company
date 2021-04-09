import React, { useState , useEffect } from 'react';
import Slider from 'react-slick';
import axios from 'axios';
import {URL_SLIDES} from '../utils/pathes';



const HomeSlider = (props) =>{

    const settings = {
        arrows: false,
        dots: false,
        infinite: true,
        speed:500,
        slidesToShow: 1,
        slidesToScroll: 1
    }


    let [slides,setSlides] = useState([]);
    useEffect( () => {
        const fetchSlides = async ()=> {
           try{
               const response = await axios.get(URL_SLIDES);
               setSlides(response.data.slides)
           } catch(error){
                console.log(error);
           }
        }
        fetchSlides();
    }, [])
    return(
        <>
        
         {
            slides ?
            <Slider {...settings}>
                {
                slides.map( item =>(
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
export default HomeSlider;