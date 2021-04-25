import axios from 'axios';
import {POSTS} from '../types';
import {URL_POSTS} from '../../components/utils/pathes';

export function loadPosts(){
    const response = axios.get(URL_POSTS)
    .then(response => response.data);
    return{
        type:POSTS,
        payload:response
    }
}