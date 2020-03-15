import $ from "jquery";
import {SignUpModal} from "./modal/SignUpModal";
import "../scss/app.scss"

/**
 * Connecting the view to frontend logic. The majority of the logic is written in a object-oriented way.
 */
export default class LogicConnector {

    static initModal(element: HTMLElement) {
        // implicit singleton
        new SignUpModal($(element)).open();
    }

    static closeModal(element: HTMLElement) {
        new SignUpModal($(element)).close();
    }
}
