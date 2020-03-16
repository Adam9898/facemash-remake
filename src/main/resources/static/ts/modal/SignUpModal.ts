import {Modal} from "./Modal";
import $ from "jquery";

/**
 *  This class uses an implicit singleton pattern. Just initalize it as you would do it any other way.
 */
export class SignUpModal extends Modal {

    private static instance: SignUpModal;

    constructor(context: JQuery<HTMLElement>,
                private _username = "",
                private _password = "",
                private _reEnteredPassword = "") {
        super(context);
        if (SignUpModal.instance) {
            return SignUpModal.instance;
        }
        this.activateModalListeners();
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

    get reEnteredPassword(): string {
        return this._reEnteredPassword;
    }

    set reEnteredPassword(value: string) {
        this._reEnteredPassword = value;
    }

    private activateModalListeners() {
        $(this.modalContext.attr('id') + ' button')
            .on("keydown", this.individuallyValidateUsername);
    }

    protected focus() {
        $(`#${this.modalID} .modal input`).first().get(0).focus();
    }



    private validateUsername() {
        // todo ajax from database
        return true;
    }

    /**
     * Use this method to check if the given username still available, and based on the boolean value it will display
     * the proper message to the user.
     */
    private individuallyValidateUsername() {

    }

    displayUsernameValid() {

    }

    displayUsernameAlreadyExists() {

    }

    validate() {
        return false;
    }
}