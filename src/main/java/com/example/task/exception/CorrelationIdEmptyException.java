package com.example.task.exception;

public class CorrelationIdEmptyException extends RuntimeException{
   public CorrelationIdEmptyException(){
        super("Correlation id empty");
    }
}
