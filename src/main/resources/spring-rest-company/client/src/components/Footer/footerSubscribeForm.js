import axios from 'axios';
import React, { Component} from 'react'
import FormFields from '../utils/formFields';
import {validate} from '../utils/validate';
import {URL_SUBSCRIBES} from '../utils/pathes';
 class FooterSubscribeForm extends Component {
    constructor(props){
        super(props);
        this.saveEmail =this.saveEmail.bind(this);
        this.rejectEmail = this.rejectEmail.bind(this);
    }
    state = {
        subscribe:{},
        formData:{
            email:{
                element: 'input',
                value: '',
                config: {
                    placeholder:'youremail@example.com',
                    type:'email',
                    name:'subscribe-email-input',
                    className:'email-input form-controll mt-3'
                },
                validator: {
                    required:false,
                },
                valid: false,
                touched: false,
                validationMessage: ''
            }
        },
        loading:false,
        alreadyIn:false,
        success:false
    }
    showError = () => {
        let errorMessage = null;
        if( this.state.formData.email.validator&&
            !this.state.formData.email.valid &&
            this.state.formData.email.validationMessage){
                errorMessage = (
                    <div className="text-danger">
                        {this.state.formData.email.validationMessage}
                    </div>
                )
            }
            return errorMessage;
    }

    updateForm = (element)=>{
        const newFormData = {
            ...this.state.formData
        }
        const newElement = {
            ...newFormData[element.id]
        }
        newElement.value = element.event.target.value;
         
        let validateData = validate(newElement);
        newElement.valid = validateData[0];
        newElement.validationMessage = validateData[1];
         
        if(element.blur){
            newElement.touched = element.blur
        }
        newFormData[element.id] = newElement;
        
        this.setState({
            formData: newFormData
        });
        
    }
    clearForm = ()=>{
        setTimeout( () => {
            this.setState({
                subscribe:{},
                formData:{
                    email:{
                        element: 'input',
                        value: '',
                        config: {
                            placeholder:'youremail@example.com',
                            type:'email',
                            name:'email-input',
                            className:'email-input form-controll mt-3'
                        },
                        validator: {
                            required:false,
                        },
                        valid: false,
                        touched: false,
                        validationMessage: ''
                     }
                },
                loading:false,
                alreadyIn:false,
                success:false
            })
        },2000);
    }
    saveEmail = ()=>{
        axios(`${URL_SUBSCRIBES}/new`,
        {
            method:'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            data:JSON.stringify(this.state.subscribe)
        }).then(response=>{
            this.setState({
                success:true
            });
            this.clearForm();
        })
        
    }
    rejectEmail = () =>{
        this.setState({
            alreadyIn:true
        })
        this.clearForm();
    }

    onError=(error)=> {
        
        if(error.response.status === 404){
            this.saveEmail();
        }else if(error.response.status === 400){
            this.rejectEmail();
        } 
    }
    
    submitForm = (event) =>{
        
        event.preventDefault();
        this.state.loading = true;
        let dataToSubmit = {};
        let formIsValid = true;
        for(let key in this.state.formData){
            dataToSubmit[key] = this.state.formData[key].value
        }
        for(let key in this.state.formData){
            formIsValid = this.state.formData[key].valid && formIsValid
        }
        if(formIsValid){
                 axios.get(`${URL_SUBSCRIBES}/${this.state.formData.email.value}`)
                .then(response =>{
                    if(response.data){
                        this.rejectEmail();
                    }
                })
                .catch(this.onError.bind(this));
                
                this.state.subscribe = dataToSubmit;
                
               
        }
    }


    render() {
        return (
            <>
                <form className="subscribe-form form-horizontal">
                        <FormFields 
                        formData={this.state.formData.email}
                        change={(element)=>this.updateForm(element)}
                        id="email"/>
                        <button className="submit-btn btn btn-warning"
                        onClick={(event)=>{this.submitForm(event)}}
                        disabled={this.state.loading}>Keep me updated</button>
                    </form>
                    {this.showError()}
                    <div className={this.state.success ? 'success-show text-success' : 'success'}>success</div>
                    <div className={`${this.state.alreadyIn ? 'success-show text-success' : 'success'}`}>already in</div>
            </>
        )
    }
}
export default FooterSubscribeForm;