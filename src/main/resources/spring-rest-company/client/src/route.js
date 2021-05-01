import React from 'react';
import {Route, Switch, BrowserRouter} from 'react-router-dom';
import Home from './components/Home';
import Footer from './components/Footer/footer';
import Header from './components/Header/header';
import SignIn from './components/Signin/signin';
import Auth from './components/utils/auth';
import Customer from './components/User/customer';
const Routes = () =>(

    <BrowserRouter>
        <Header/>
        <Switch>
        <Route path="/customer" component={Auth(Customer)}/>
        <Route path="/login" exact component={SignIn}/>
        <Route path="/" exact component={Home}/>
        </Switch>
        <Footer/>
    </BrowserRouter>

)
export default Routes; 
