/*

package io.bcn.springConference.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;


@Route("/conference")
public class ConferenceView extends VerticalLayout {



    private final TextField name = new TextField("Name");
    private final TextField email = new TextField("Email");
    private final TextField phoneNumber = new TextField("Phone Number");
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");




    // Method to create the main layout
    private Component createMainLayout() {
        // Create the 3-column layout
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        mainLayout.setPadding(false);
        mainLayout.setSpacing(false);

        // Left column (empty for spacing)
        VerticalLayout leftColumn = new VerticalLayout();
        leftColumn.setWidth("20%");

        // Center column (contains all the components)
        VerticalLayout centerColumn = new VerticalLayout();
        centerColumn.setWidth("60%");
        centerColumn.setAlignItems(Alignment.CENTER);

        // Right column (empty for spacing)
        VerticalLayout rightColumn = new VerticalLayout();
        rightColumn.setWidth("20%");

        // Create a form layout
        HorizontalLayout formLayout = new HorizontalLayout(name, email, phoneNumber);
        formLayout.setWidth("100%");
        formLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        // Create a button layout
        HorizontalLayout buttonLayout = new HorizontalLayout(save, delete);
        buttonLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        // Add components to the center column
        centerColumn.add(
                new H2("Customer Management"),
                formLayout,
                buttonLayout
        );

        // Add all columns to the main layout
        mainLayout.add(leftColumn, centerColumn, rightColumn);

        return mainLayout;
    }

}
*/

package io.bcn.springConference.view;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Conference")
@Route(value = "/Conference", layout = MainLayout.class)
public class ConferenceView extends Composite<VerticalLayout> {
    public ConferenceView(){
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        TextField linkYoutube = new TextField("Link Youtube");
        TextField title = new TextField("Title");
        TextField conferenceName = new TextField("Conference name");
        TextField content = new TextField("Content");
        ComboBox durationBox = new ComboBox();
        durationBox.setLabel("Duration");
        durationBox.setWidth("1000px");
        durationBox.setMinWidth("50px");
        durationBox.setMaxWidth("350px");
        setComboBoxSampleData(durationBox);

        TextField duration = new TextField("Duration");
        TextField room = new TextField("Room");
        PasswordField password = new PasswordField("Password");
        PasswordField confirmPassword = new PasswordField("Confirm password");

        DatePicker datePicker = new DatePicker("Date");
        FormLayout formLayout = new FormLayout();
        formLayout.add(datePicker, linkYoutube, title, conferenceName, content, durationBox,
                room);
        formLayout.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 2));
        // Stretch the username field over 2 columns
        //formLayout.setColspan(username, 2);
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

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<String> sampleItems = new ArrayList<>();
        sampleItems.add("20");
        sampleItems.add("30");
        sampleItems.add("40");
        sampleItems.add("50");
        sampleItems.add("60");
        sampleItems.add("70");
        sampleItems.add("80");
        sampleItems.add("90");
        sampleItems.add("100");
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((String) item));
    }
}
