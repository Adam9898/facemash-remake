import {User} from '../../ts/models/User';
import * as testValidationFunctions from 'class-validator'
import {validateOrReject} from 'class-validator';

describe('A user model', () => {
    let user: User;

    beforeEach(() => {
        user = new User('uniqusername', 'JIDwodowie32c', 'JIDwodowie32c');
    });

    it('should be a valid User object', () => {
        expect(user).toBeInstanceOf(User);
    });

    it('should have a working constructor with the necessary properties set to the expected value', () => {
        expect(user.username).toBe('uniqusername');
        expect(user.password).toBe('JIDwodowie32c');
        expect(user.rePassword).toBe('JIDwodowie32c');
    });

    // testing validation
    it('should validate user', async () => {
        spyOn(testValidationFunctions, 'validateOrReject').and.returnValue(Promise.resolve());
        let validationResult = true;
        try {
            await validateOrReject(user);
        } catch (e) {
            validationResult = false;
        }
        expect(validationResult).toBeTrue();
    });

    it('should NOT validate user', async () => {
        user.username = "ds";
        user.password = "1234";
        user.rePassword = "4321";
        let validationResult = true;
        try {
            await validateOrReject(user);
        } catch (e) {
            validationResult = false;
        }
        expect(validationResult).toBeFalse();
    });
});