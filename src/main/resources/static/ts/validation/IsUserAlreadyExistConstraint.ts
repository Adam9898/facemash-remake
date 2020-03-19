import {ValidationArguments, ValidatorConstraint, ValidatorConstraintInterface} from 'class-validator';

@ValidatorConstraint({async: true})
export class IsUserAlreadyExistConstraint implements ValidatorConstraintInterface {
    validate(username: any, validationArguments?: ValidationArguments): Promise<boolean> | boolean {
        // todo ajax api call to server to check username
        return true;
    }
}