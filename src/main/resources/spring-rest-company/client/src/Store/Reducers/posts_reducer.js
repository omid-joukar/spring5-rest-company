import {POSTS} from '../types';
export default function(state={},action){

    switch(action.type){
        case POSTS:
            return {...state,posts:action.payload.posts}
        default:
            return state;
    }
}