import React, {Component} from 'react';
import {validate} from '../utils/validate';
import {userLogin} from '../../Store/Actions/user_actions';
import {connect} from 'react-redux';
import FormFields from '../utils/formFields';

class SignIn extends Component{

    state={
        registerError:'',
        loading:false,
        success:false,
        loginError:false,
        formData:{
            username:{
                element:'input',
                value:'',
                config:{
                    className:'form-br',
                    name:'email_input',
                    type:'email',
                    placeholder:'Enter your email'
                },
                validator: {
                    required:false,
                    email:true
                },
                valid:false,
                touched:false,
                validationMessage:''
            },
            password:{
                element:'input',
                value:'',
                config:{
                    className:'form-br',
                    name:'password_input',
                    type:'password',
                    placeholder:'Enter your password'
                },
                validator:{
                    required:true,
                    password:true
                },
                valid:false,
                touched:false,
                validationMessage:''
            }
        }
    }

    updateForm = (element) => {
        const newFormData = {
            ...this.state.formData
        }
        const newElement = {
            ...newFormData[element.id]
        }
        newElement.value = element.event.target.value;
        
        newElement.touched = element.blur;

        let validateData = validate(newElement);
        newElement.valid = validateData[0];
        newElement.validationMessage = validateData[1];

        newFormData[element.id] = newElement;
        this.setState({
            formData:newFormData
        })

    }

    

    submitForm = (event,type) => {
        event.preventDefault();
        if(type !== null){

            let dataToSubmit = {};
            let formIsValid = true;

            for(let key in this.state.formData){
                dataToSubmit[key] = this.state.formData[key].value
            }
            for(let key in this.state.formData){
                formIsValid = this.state.formData[key].valid && formIsValid;
            }
            if(formIsValid){
                this.setState({
                    loading:true,
                    registerError:''
                })
                if(type){
                    this.props.dispatch(userLogin(dataToSubmit))
                }else{
                    console.log('REGISTER')
                }
            }
        }

    }
    static getDerivedStateFromProps(props,state){
        const auth = props.state.auth;
        if(auth){
            state.loading = false;
            state.success = auth ? true : false;
        }
        if(auth === false){
            state.loading = false;
            state.success = !auth ? false : true;
            state.loginError = true;
        }
    }

    componentDidUpdate(){
        if(this.state.success){
            let roles = Array.from(this.props.state.userData.roles);
            if(roles[0].name === 'ADMIN'){
            this.props.history.push('/admin');
            }else if(roles[0].name === 'CUSTOMER'){
                sessionStorage.setItem("auth",this.props.state.auth);
                sessionStorage.setItem("userData",JSON.stringify(this.props.state.userData));
                sessionStorage.setItem("token",this.props.state.token);
                this.props.history.push('/customer')
            }
        }
    }

    submitButton = () => (
        this.state.loading ?
        'loading...'
        :
        <div>
             <button className="btn-login" onClick={(event)=>this.submitForm(event,true)}> Log in </button>
             <h6>Don't have an account?</h6>
            <button className="btn-signin" onClick={(event)=>this.submitForm(event,false)}> Register now </button>
           
        </div>
    )

    render(){
        return(
           <div className="Container d-flex col-sm-12 bg-img"
           style={{
               background:'url(/images/login/groceries1.jpg) no-repeat'
           }}
           >
            
               <form className="container-form justify-content-center main-form" onSubmit={(event)=>this.submitForm(event,null)}>
               <div className="social_icon">
                    <span><i className="fas fa-user-circle"></i></span>
                </div>
                   <h1>Sign In</h1>
                   <p>Email</p>
                    <FormFields 
                     id={'username'}
                     formData={this.state.formData.username}
                     change={(element) => this.updateForm(element)}
                     />
                     
                    <p>Password</p>
                    <FormFields 
                     id={'password'}
                     formData={this.state.formData.password}
                     change={(element) => this.updateForm(element)}
                     />
                     { this.submitButton() }
                    {this.state.loginError?
                    <p className="loginError">This Account is invalid (:</p>
                    :
                    null
                    }
               </form>
           </div>
        )
    }
}
function mapStateToProps(state){
    return{
    state:state.user
    }
}
export default connect(mapStateToProps)(SignIn);