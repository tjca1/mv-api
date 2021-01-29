package br.com.mv.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ApiMvException extends RuntimeException {

	private static final long serialVersionUID = 6033295178842238013L;

	public ApiMvException(String msgMvErro) {
		super(msgMvErro);
	}

	public ApiMvException(Exception e) {
		super(e);
	}

	public ApiMvException(String msgMvErro, Exception e) {
		super(msgMvErro, e);
	}

}
