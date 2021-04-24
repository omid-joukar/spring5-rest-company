import React from 'react';
import {Route, Switch, BrowserRouter} from 'react-router-dom';
import Home from './components/Home';
import Footer from './components/Footer/footer';
import Header from './components/Header/header';


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
