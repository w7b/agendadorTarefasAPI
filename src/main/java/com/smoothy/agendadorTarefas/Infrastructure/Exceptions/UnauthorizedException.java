package com.smoothy.agendadorTarefas.Infrastructure.Exceptions;


import javax.security.sasl.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {
  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable throwable) {
    super(message , throwable);
  }
}
