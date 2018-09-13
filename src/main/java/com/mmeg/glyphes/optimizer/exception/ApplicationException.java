package com.mmeg.glyphes.optimizer.exception;

public interface ApplicationException {

	public void setId(String id);

	public String getId();

	public boolean hasId();

	public boolean hasNoId();

	public String getMessage();

}
