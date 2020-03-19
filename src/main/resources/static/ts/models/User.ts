import {Equals, IsNotEmpty, Length, Matches} from 'class-validator';
import {Available} from '../validation/Available';
import {SignUpModal} from '../modal/SignUpModal';

/**
 *  The User class is close to a POJO (Plain Old JavaScript Object), but it uses decorators for validation.
 *  A user represents a registered user to the main application.
 */
export class User {

    @Available({
        message: '$value is already taken. Please try something else'
    })
    @Length(3, 12, {
        message: 'Username length has to be minimum $constraint1 and maximum $constraint2 characters long'
    })
    @IsNotEmpty({
        message: 'Please provide a username'
    })
    private _username = '';


    @Matches(new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)+.{6,50}$'), {
        message: 'The password should contain an Upper case letter, a lower case letter and a number'
    })
    @Length(6, 50, {
        message: 'The password length needs to be at least $constraint1 character long and a maximum of $constraint2'
    })
    @IsNotEmpty({
        message: 'Please provide a password'
    })
    private _password = '';

    // equals to password is checked from outside
    @IsNotEmpty({
        message: 'Please enter your password again'
    })
    private _rePassword = '';

    constructor(username?: string, password?: string, rePassword?: string) {
        this.username += username;
        this.password += password;
        this.rePassword += rePassword;
    }


    get username(): string {
        return this._username;
    }

    set username(value: string) {
        this._username = value;
    }

    get password(): string {
        return this._password;
    }

    set password(value: string) {
        this._password = value;
    }

    get rePassword(): string {
        return this._rePassword;
    }

    set rePassword(value: string) {
        this._rePassword = value;
    }
}