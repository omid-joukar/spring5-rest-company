import {combineReducers} from 'redux';
import slider from './sliders_reducer';
import post from './posts_reducer';
import sale from './sales_reducer';
const reducers = combineReducers({
slider,
post,
sale
});
export default reducers;