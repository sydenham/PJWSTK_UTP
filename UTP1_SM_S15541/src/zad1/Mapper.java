/**
 *
 *  @author Szarek Marcin S15541
 *
 */

package zad1;


public interface Mapper <T, S> { // Uwaga: interfejs musi być sparametrtyzowany
	public S map (T x);
}  
