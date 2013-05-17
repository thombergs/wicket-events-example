package org.wickedsource;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	private int counter1 = 0;

	private int counter2 = 0;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		final NumberTextField<Integer> textField1 = new NumberTextField<Integer>(
				"textField1", new PropertyModel(this, "counter1")) {
			@Override
			public void onEvent(IEvent<?> event) {
				if (event.getPayload() instanceof IncrementEvent) {
					counter1 += ((IncrementEvent) event.getPayload())
							.getIncrement();
				}
			}
		};
		textField1.setOutputMarkupId(true);
		add(textField1);

		final NumberTextField<Integer> textField2 = new NumberTextField<Integer>(
				"textField2", new PropertyModel(this, "counter2")) {
			@Override
			public void onEvent(IEvent<?> event) {
				if (event.getPayload() instanceof IncrementEvent) {
					counter2 += ((IncrementEvent) event.getPayload())
							.getIncrement();
				}
			}
		};
		textField2.setOutputMarkupId(true);
		add(textField2);

		AjaxLink<Void> incrementByOneLink = new AjaxLink<Void>("incrementByOne") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				send(getPage(), Broadcast.BREADTH, new IncrementEvent(1));
				target.add(textField1);
				target.add(textField2);
			}
		};
		add(incrementByOneLink);

		AjaxLink<Void> incrementByTenLink = new AjaxLink<Void>("incrementByTen") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				send(getPage(), Broadcast.BREADTH, new IncrementEvent(10));
				target.add(textField1);
				target.add(textField2);
			}
		};
		add(incrementByTenLink);

	}
}
