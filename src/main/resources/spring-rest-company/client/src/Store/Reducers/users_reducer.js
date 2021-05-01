import {USER_LOGIN,USER_AUTH} from '../types';

export default function(state={},action){
    switch(action.type){
        case USER_LOGIN:{
            return {
                ...state,
                auth: action.payload.auth,
                userData: action.payload.userData,
                token: action.payload.token
            }
        }
        case USER_AUTH:{
            return{
                ...state,
                auth: action.payload.auth,
                userData: action.payload.userData,
                token: action.payload.token
            }
        }
        default:
            return state
    }

}