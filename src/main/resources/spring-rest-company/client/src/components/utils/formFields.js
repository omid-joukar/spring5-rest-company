import React from 'react'

 const FormFields = ({formData,change,id}) => {
    

    const showError = () => {
        let errorMessage = null;
        if( formData.validator &&
            !formData.valid &&
            formData.validationMessage &&
            formData.config.name !== 'subscribe-email-input'){
                errorMessage = (
                    <div className="label label-danger">
                        {formData.validationMessage}
                    </div>
                )
            }
            return errorMessage;
    }

    const renderTemplate = () =>{
        let formTemplate = null;
        switch(formData.element){
            case('input'):
                formTemplate = (
                    <>
                        <input
                        {...formData.config}
                        value={formData.value}
                        onBlur={(event)=>change({event,id,blur:true})}
                        onChange={(event)=>change({event,id,blur:null})}
                        />
                        {showError()}
                    </>
                )
                break;
            default:
                formTemplate = null;    
        }
        return formTemplate;
    }

    

    return (
        <>
           {renderTemplate()} 
        </>
    )
}
export default FormFields;