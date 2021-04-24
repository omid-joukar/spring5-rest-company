const express = require('express');
const bodyParser = require('body-parser');

//define app
const app = express();
app.use(bodyParser.json());


//Routes


app.get("/" ,(req,res)=>{
    res.redirect
})






const port = process.env.port || 3001;
app.listen(port , ()=>{
    console.log(`Started with port : ${port}`);
})