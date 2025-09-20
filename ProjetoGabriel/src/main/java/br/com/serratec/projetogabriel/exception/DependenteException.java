package br.com.serratec.projetogabriel.exception;

/**
 * Exceção personalizada para tratar erros relacionados aos dependentes.
 */
public class DependenteException extends Exception {
    
    public DependenteException(String message) {
        super(message);
    }
    
    public DependenteException(String message, Throwable cause) {
        super(message, cause);
    }
}