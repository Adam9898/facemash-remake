import {Modal} from './Modal';
import $ from 'jquery';
import {User} from '../models/User';
import {validate, Validator, ValidationError} from 'class-validator';


/**
 *  This class uses an implicit singleton pattern. Just initialize it as you would do it any other way.
 */
export class SignUpModal extends Modal {

    private static instance: SignUpModal;
    private user = new User();
    private validator = new Validator();

    constructor(context: JQuery<HTMLElement>) {
        super(context);
        if (SignUpModal.instance) {
            return SignUpModal.instance;
        } else {
            SignUpModal.instance = this;
            this.activateModalListeners();
            console.log("registering modal listeners");
        }
    }

    private activateModalListeners() {
        $('#register-button')
            .on('click', (event) => {
                //event.preventDefault();
                this.validate();
            });
        $('#register-username')
            .on('keyup', () => {
                this.user.username = '' + $('#register-username').val();
                this.validateUsername();
            });
        $('#register-password')
            .on('keyup', () => {
                this.user.password = '' + $('#register-password').val();
                this.validatePassword();
            });
        $('#register-re-password')
            .on('keyup', () => {
                this.user.rePassword = '' + $('#register-re-password').val();
                this.validateRePassword();
            });
    }

    protected focus() {
        $(`#${this.modalID} .modal input`).first().get(0).focus();
    }

    private async validate() {
        // todo BUG when user clicks sign up button: empty input field gets validated as if it were valid.
        const validArr = [];
        validArr.push(await this.validateUsername(), await this.validatePassword(), await this.validateRePassword());
        const valid =  validArr[0] && validArr[1] && validArr[2];
        if (valid) {
            this.registerUser();
        }
    }

    private registerUser() {
        this.user = new User(); // flushing sensitive data from memory
        console.log('registering the user');
        //$('#register-button').trigger('submit');
    }

    private async validateUsername() {
        let returnBool = false;
        try {
            const validationErrors = await validate(this.user);
            const validationError = validationErrors
                .find(element => element.property === '_username');
            if (validationError) {
                this.displayUsernameValidationError(validationError);
            } else {
                this.displayUsernameValidationSuccess();
                returnBool = true;
            }
        } catch (e) {
            console.log('Error occurred during username validation: ' + e);
        }
        return returnBool;
    }

    private displayUsernameValidationSuccess() {
        $('#register-username')
            .removeClass('validation-error')
            .addClass('validation-success');
        $('#username-error').hide().text('');
    }

    private displayUsernameValidationError(validationError: ValidationError) {
        $('#register-username')
            .removeClass('validation-success')
            .addClass('validation-error');
        $('#username-error').text(validationError.constraints[Object.keys(validationError.constraints)[0]]).show();
    }

    private async validatePassword() {
        let returnBool = false;
        try {
            const validationErrors = await validate(this.user);
            const validationError = validationErrors
                .find((element) => element.property === '_password');
            if (validationError) {
                this.displayPasswordValidationError(validationError);
            } else {
                this.displayPasswordValidationSuccess();
                returnBool = true;
            }
        } catch (e) {
            console.log('Error occurred during password validation: ' + e);
        }
        return returnBool;
    }

    private displayPasswordValidationSuccess() {
        $('#register-password')
            .removeClass('validation-error')
            .addClass('validation-success');
        $('#password-error').hide().text('');
    }

    private displayPasswordValidationError(validationError: ValidationError) {
        $('#register-password')
            .removeClass('validation-success')
            .addClass('validation-error');
        $('#password-error').text(validationError.constraints[Object.keys(validationError.constraints)[0]]).show();
    }

    private async validateRePassword() {
        let returnBool = false;
        try {
            const validationErrors = await validate(this.user);
            const validationError = validationErrors
                .find((element) => element.property === '_rePassword');
            if (validationError) {
                this.displayRePasswordValidationError(validationError);
            } else if (!this.validator.equals(this.user.password, this.user.rePassword)) {
                this.displayRePasswordNotEqualsValidationError();
            } else {
                this.displayRePasswordValidationSuccess();
                returnBool = true;
            }
        } catch (e) {
            console.log('Error occurred during retype password validation: ' + e);
        }
        return returnBool;
    }

    private displayRePasswordValidationSuccess() {
        $('#register-re-password')
            .removeClass('validation-error')
            .addClass('validation-success');
        $('#re-password-error').hide().text('');
    }

    private displayRePasswordValidationError(validationError: ValidationError) {
        $('#register-re-password')
            .removeClass('validation-success')
            .addClass('validation-error');
        $('#re-password-error').text(validationError.constraints[Object.keys(validationError.constraints)[0]]).show();
    }

    private displayRePasswordNotEqualsValidationError() {
        $('#register-re-password')
            .removeClass('validation-success')
            .addClass('validation-error');
        $('#re-password-error').text('Password does not match!').show();
    }
}