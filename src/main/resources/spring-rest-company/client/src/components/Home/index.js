import React, { Component } from 'react';
import HomeSlider from './slider';
import OnlineShopOffer from './onlineShopOffer';
import Sale from './sale';
import Posts from './posts'

class Home extends Component{

    render(){
        return(
            <> 
        
                <HomeSlider/>
                <OnlineShopOffer/>
                <Sale/>
                <Posts/>
            
            </>
        )
    }
}

export default Home;