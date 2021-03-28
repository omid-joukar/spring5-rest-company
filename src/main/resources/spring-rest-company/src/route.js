import React from 'react';
import {Route, Switch, BrowserRouter} from 'react-router-dom';
import Home from './components/Home';
import Footer from './components/footer';
import Header from './components/header';


const Routes = () =>(

    <BrowserRouter>
        <Header/>
        <Switch>
            <Home/>
        </Switch>
        <Footer/>
    </BrowserRouter>

)
export default Routes; 
