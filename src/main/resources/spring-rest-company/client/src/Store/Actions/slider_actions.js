import axios from 'axios';
import {SLIDERS} from '../types';
import {URL_SLIDES} from '../../components/utils/pathes';
export function loadSliders(){

    const response =  axios.get(URL_SLIDES)
    .then(response => response.data);
    return{
        type:SLIDERS,
        payload:response
    }



}
