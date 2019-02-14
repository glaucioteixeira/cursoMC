package br.com.cenarioesolucao.cursoMC.services.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//Anotações Customizadas para atualizar registros 

@Constraint(validatedBy = ClienteUpdateACValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteUpdateAC {
	String message() default "Error de validação";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
