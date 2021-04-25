import axios from 'axios';
import {SALES} from '../types';
import {URL_SALES} from '../../components/utils/pathes';
export function loadSales(){
    
    const response = axios.get(URL_SALES)
            .then(response => response.data);
    
    return{
        type:SALES,
        payload:response
    }
}