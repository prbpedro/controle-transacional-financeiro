package com.github.prbpedro.ctf.dtos;

import java.util.HashMap;
import java.util.Map;

import com.github.prbpedro.ctf.util.Constantes;

public class GenericOperationResponse {

	private boolean error;
	private Map<String, String> messages;
	private Object entity;

	public GenericOperationResponse(boolean error, String msg, Object entity) {
		this.error = error;
		this.entity = entity;
		this.messages = new HashMap<String, String>();
		this.messages.put(Constantes.GLOBAL_MESSAGE, msg);
	}

	public boolean isError() {
		return error;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public Object getEntity() {
		return entity;
	}
}
