import {combineReducers} from 'redux';
import slider from './sliders_reducer';
import post from './posts_reducer';
import sale from './sales_reducer';
import user from './users_reducer';

const reducers = combineReducers({
slider,
post,
sale,
user
});
export default reducers;