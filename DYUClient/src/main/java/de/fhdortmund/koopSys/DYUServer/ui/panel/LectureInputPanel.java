package de.fhdortmund.koopSys.DYUServer.ui.panel;

import com.vaadin.data.Binder;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.fhdortmund.koopSys.DYUServer.logic.entities.Lecture;

public class LectureInputPanel extends VerticalLayout implements InputPanel<Lecture> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TextField tfVlName;

	public LectureInputPanel() {
		setMargin(true);

		tfVlName = new TextField("Name");
		new Binder<String>().forField(tfVlName).withValidator(str -> str.length() <= 4, "At least 4 Characters");
		addComponent(tfVlName);

	}

	@Override
	public void clearInput() {
		for (Component component : components) {
			AbstractField field = (AbstractField) component;
			if (component instanceof AbstractField) {
				field.clear();
			}
		}

	}

	@Override
	public Lecture getElement() {
		Lecture retLecture = new Lecture();
		retLecture.setName(tfVlName.getValue());
		
		
		return retLecture;
	}

	@Override
	public void setElement(Lecture element) {
		element.setName(tfVlName.getValue());

	}

}
