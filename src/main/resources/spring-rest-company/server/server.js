const express = require('express');
const bodyParser = require('body-parser');

//define app
const app = express();
app.use(bodyParser.json());










const port = process.env.port || 3001;
app.listen(port , ()=>{
    console.log(`Started with port : ${port}`);
})