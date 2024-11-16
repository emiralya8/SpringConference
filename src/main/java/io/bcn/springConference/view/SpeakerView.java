package io.bcn.springConference.view;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Speaker")
@Route(value = "/Speaker", layout = MainLayout.class)
public class SpeakerView extends Composite<VerticalLayout> {
    public SpeakerView(){
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        TextField name = new TextField("Name");
        TextField bio = new TextField("Bio");
        TextField email = new TextField("Email");

        FormLayout formLayout = new FormLayout();
        formLayout.add(name, bio, email);
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

        getContent().add(formLayout);
        Button createButton = new Button("Create");
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button readButton = new Button("Read");
        readButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button updateButton = new Button("Update");
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button deleteButton = new Button("Delete");
        deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout buttonLayout = new HorizontalLayout(createButton, readButton, updateButton, deleteButton);
        buttonLayout.getStyle().set("flex-wrap", "wrap");
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        getContent().add(buttonLayout);

    }
}
