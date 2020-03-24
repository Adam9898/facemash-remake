import {SignUpModal} from '../../ts/modal/SignUpModal';
import $ from 'jquery';

describe('Sign up modal', () => {
    let testDomElement: HTMLElement;
    let signUpModal: SignUpModal;

    beforeEach(() => {
        signUpModal = new SignUpModal($(testDomElement));
    });


    // This is a test for the implicit singleton pattern
    it('should give back same instance', () => {
        expect(new SignUpModal($(testDomElement))).toBe(signUpModal);
    });

    // this is not a good practice, but I wanted to try out reflection with javascript. Generally speaking, you only
    // want to test the public api, so no private fields and methods
    it('should have a modal context', () => {
        expect(Reflect.get(signUpModal, 'modalContext')).toBeTruthy();
    });
})