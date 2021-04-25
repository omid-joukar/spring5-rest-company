import {SALES} from '../types';
export default function(state={},action){
    switch(action.type){
        case SALES:
            return {...state,sales:action.payload.sales}
        default:
            return state;
    }
}