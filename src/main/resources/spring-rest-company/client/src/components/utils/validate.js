export const validate = (element)=>{
    let error = [true , ''];
    
    if(element.config.type === 'email'){
        let pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        const valid = pattern.test(element.value.toLowerCase());
        const message = `${!valid ? 'Email is invalid' : ''}`;
        error = [valid,message]
    }
     if(element.validator.required){
        const valid = element.value.trim() !== '';
        const message = `${!valid ? 'This field is required' : ''}`;
        error = [valid,message]
    }  
    if(element.validator.password){
        const valid = element.value.length >=5;
        const message = `${!valid ? 'Must be greater than 5':''}`;
        error = [valid,message]
    }
    
return error;
}