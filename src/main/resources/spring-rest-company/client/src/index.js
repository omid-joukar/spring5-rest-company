import React from 'react';
import ReactDOM from 'react-dom';
import Routes from './route';
import {Provider} from 'react-redux';
import {createStore,applyMiddleware} from 'redux';
import promiseMiddleware from 'redux-promise';
import {composeWithDevTools} from 'redux-devtools-extension';
import reducers from './Store/Reducers/reducers';


const createStoreWithMiddleware = createStore(reducers,composeWithDevTools(applyMiddleware(promiseMiddleware)));

ReactDOM.render(<Provider store={createStoreWithMiddleware}>
                 <Routes/>
                </Provider>,document.getElementById('root'));

