const express = require('express');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const { response } = require('express');

//define app
const app = express();

app.use(bodyParser.json());
app.use(cookieParser());


app.get('/user/login/auth' , (req,res,err)=>{
    
    console.log();
    if(err) res.sendStatus(400).json({
        auth:false
    })
    if(token === undefined || token === null) res.status(400).json({
        auth:false
    })
    res.status(200).json({
        auth:true
    })
})







const port = process.env.port || 3001;
app.listen(port , ()=>{
    console.log(`Started with port : ${port}`);
})