import {SignUpModal} from '../../ts/modal/SignUpModal';
import $ from 'jquery';

describe('Sign up modal', () => {
    let testDomElement: HTMLElement;
    let signUpModal: SignUpModal;

    beforeEach(() => {
        signUpModal = new SignUpModal($(testDomElement));
    });


    // This is a test for the implicit singleton pattern
    it('should give back same instance', function () {
        expect(new SignUpModal($(testDomElement))).toBe(signUpModal);
    });

    it('should have a modal context', function () {
        expect(Reflect.get(signUpModal, 'modalContext')).toBeTruthy();
    });
})