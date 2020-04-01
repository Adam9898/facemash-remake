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

    it('should validate unique username', async () => {
        spyOn(usernameValidator, 'validate').and.returnValue(Promise.resolve(true));
        let expectResult = await usernameValidator.validate('uniqusername');
        expect(expectResult).toBeTrue();
    });
})