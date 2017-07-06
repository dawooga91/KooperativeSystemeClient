package de.fhdortmund.koopSys.DYUServer.ui.panel;

public interface InputPanel<T> {
	public void clearInput();

	public T getElement();

	public void setElement(T element);
}
