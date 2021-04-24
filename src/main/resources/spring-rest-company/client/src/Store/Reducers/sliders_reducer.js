import {SLIDERS} from '../types';
export default function(state={},action){
    switch(action.type){         
        case SLIDERS:
            return{...state,slides:action.payload.slides}
        default:
            return state;
    }
}