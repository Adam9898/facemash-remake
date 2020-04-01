import {ValidationArguments, ValidatorConstraint, ValidatorConstraintInterface} from 'class-validator';

@ValidatorConstraint({async: true})
export class IsUserAlreadyExistConstraint implements ValidatorConstraintInterface {
    async validate(username: string, validationArguments?: ValidationArguments) {
        let response = await fetch(`/uniqueUser/${username}`);
        let jsonResult: UniqueUsernameJSONFormat = await response.json();
        return jsonResult.usernameIsValid;
    }
}