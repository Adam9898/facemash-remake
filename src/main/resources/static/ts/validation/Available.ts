import { registerDecorator, ValidationOptions } from 'class-validator';
import {IsUserAlreadyExistConstraint} from './IsUserAlreadyExistConstraint';

export function Available(validationOptions?: ValidationOptions) {
    return (object: Object, propertyName: string) => {
        registerDecorator({
            target: object.constructor,
            propertyName: propertyName,
            options: validationOptions,
            constraints: [],
            validator: IsUserAlreadyExistConstraint
        });
    };
}