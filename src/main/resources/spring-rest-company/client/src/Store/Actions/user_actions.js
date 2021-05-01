import axios from 'axios';
import {USER_LOGIN,USER_AUTH} from '../types';
import {URL_USER_LOGIN,URL_USER_AUTH} from '../../components/utils/pathes';

export function userLogin({username,password}){
    const response = axios.post(URL_USER_LOGIN,JSON.stringify({'username':username,'password':password}))
                           .then( response => response.data);
                           
    return{
        type:USER_LOGIN,
        payload:response
    }
}
export function userAuth(){
    const auth = sessionStorage.getItem("auth");
    const userData = sessionStorage.getItem("userData");
    const token = sessionStorage.getItem("token");
    return{
        type:USER_AUTH,
        payload:{
            auth,userData,token
        }
    }
}