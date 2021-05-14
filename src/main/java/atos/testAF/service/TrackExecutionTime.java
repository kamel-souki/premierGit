package atos.testAF.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @apiNote Crée une annotation qui une fois appliqué à une méthode retourne son processing time.<br>
 * Son fonctionnement se trouve dans la classe ExecutiontimeAdvice 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackExecutionTime {

}