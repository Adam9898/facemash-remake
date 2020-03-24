import {IsUserAlreadyExistConstraint} from '../../ts/validation/IsUserAlreadyExistConstraint';

describe('A user already exist constraint validator ', () => {

    let usernameValidator: IsUserAlreadyExistConstraint;

    beforeEach(() => {
         usernameValidator = new IsUserAlreadyExistConstraint();
    });

    it('should call validate unique username', () => {
        spyOn(usernameValidator, 'validate');
        usernameValidator.validate('uniqusername');
        expect(usernameValidator.validate).toHaveBeenCalled();
    });

    it('should validate unique username', () => {
        spyOn(usernameValidator, 'validate').and.returnValue(true);
        usernameValidator.validate('uniqusername');
        expect(usernameValidator.validate('uniqusername')).toBeTrue();
    });
})