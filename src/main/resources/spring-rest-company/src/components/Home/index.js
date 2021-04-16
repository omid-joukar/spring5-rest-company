import React, { Component } from 'react';
import HomeSlider from './slider';
import OnlineShopOffer from './onlineShopOffer';
import Sale from './sale';
class Home extends Component{

    render(){
        return(
            <> 
        
                <HomeSlider/>
                <OnlineShopOffer/>
                <Sale/>
            
            </>
        )
    }
}

export default Home;