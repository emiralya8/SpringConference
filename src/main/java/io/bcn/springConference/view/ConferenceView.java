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
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import io.bcn.springConference.Converter.ConvertStringToDate;
import io.bcn.springConference.Converter.ConvertStringToInteger;
import io.bcn.springConference.model.Book;
import io.bcn.springConference.model.Conference;
import io.bcn.springConference.model.Speaker;
import io.bcn.springConference.repository.BookRepository;
import io.bcn.springConference.repository.ConferenceRepository;
import io.bcn.springConference.repository.SpeakerRepository;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@PermitAll
@PageTitle("Conference")
@Route(value = "/Conference", layout = MainLayout.class)
public class ConferenceView extends Composite<VerticalLayout> {

    private final ComboBox idConferenceBox = new ComboBox();
    private final TextField linkYoutube = new TextField("Link Youtube");
    private final TextField title = new TextField("Title");
    private final TextField conferenceName = new TextField("Conference name");
    private final TextField content = new TextField("Content");
    private final TextField duration = new TextField("Duration");
    private final TextField room = new TextField("Room");
    private final DatePicker datePicker = new DatePicker("Date");
    private final ComboBox<Book> books = new ComboBox<>();
    private final ComboBox<Speaker> speakers = new ComboBox<>();
    private final FormLayout formLayout = new FormLayout();
    private final Button createButton = new Button("Create");
    private final Button updateButton = new Button("Update");
    private final Button deleteButton = new Button("Delete");

    private final HorizontalLayout buttonLayout = new HorizontalLayout(createButton, updateButton, deleteButton);
    private ConferenceRepository conferenceRepository;
    private BookRepository bookRepository;
    private SpeakerRepository speakerRepository;
    private Binder<Conference> conferenceBinder = new Binder<>();

    public ConferenceView(ConferenceRepository conferenceRepository, BookRepository bookRepository, SpeakerRepository speakerRepository){
        this.conferenceRepository = conferenceRepository;
        this.bookRepository = bookRepository;
        this.speakerRepository = speakerRepository;
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        fillCombobox();
        buttonLayout.add(createButton,updateButton,deleteButton);
        addStylesComponents();
        addComponentsListener();
        formLayout.add(idConferenceBox,datePicker, linkYoutube, title, conferenceName, content, duration, room, books, speakers);
        uploadIdItems();
        getContent().add(formLayout);
        showButtons("init");
        getContent().add(buttonLayout);
        bindingModelForm();
    }

    private void bindingModelForm(){
        conferenceBinder.forField(linkYoutube).bind(Conference::getLinkToYouTubeVideo, Conference::setLinkToYouTubeVideo);
        conferenceBinder.forField(title).bind(Conference::getTitle,Conference::setTitle);
        conferenceBinder.forField(conferenceName).bind(Conference::getConferenceName,Conference::setConferenceName);
        conferenceBinder.forField(content).bind(Conference::getContent, Conference::setContent);
        conferenceBinder.forField(duration)
                .withConverter(new ConvertStringToInteger())
                .bind(Conference::getDuration,Conference::setDuration);
        conferenceBinder.forField(room).bind(Conference::getRoom,Conference::setRoom);
        conferenceBinder.forField(datePicker)
                .withConverter(new ConvertStringToDate("yyyy-MM-dd"))
                .bind(Conference::getDate,Conference::setDate);

        conferenceBinder.forField(books)
                .bind(Conference::getBook,Conference::setBook);
        conferenceBinder.forField(speakers)
                .bind(Conference::getSpeaker,Conference::setSpeaker);
    }

    private void addStylesComponents(){
        idConferenceBox.setLabel("Id");
        idConferenceBox.setWidth("1000px");
        idConferenceBox.setMinWidth("50px");
        idConferenceBox.setMaxWidth("350px");

        books.setLabel("Books");
        speakers.setLabel("Speakers");

        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 2));
        buttonLayout.getStyle().set("flex-wrap", "wrap");
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
    }

    private void addComponentsListener(){
        createButton.addClickListener(e -> save());
        updateButton.addClickListener(e -> update());
        deleteButton.addClickListener(e -> delete());
        idConferenceBox.addValueChangeListener(e -> read(e));
    }

    private void fillCombobox(){
        List<Book> lstBooks = bookRepository.findAll();
        books.setItems(lstBooks);
        List<Speaker> lstSpeakers = speakerRepository.findAll();
        speakers.setItems(lstSpeakers);
    }

    private void uploadIdItems(){
        List<Conference> lstConferences = conferenceRepository.findAll();
        List<String> lstIds = new ArrayList<>();
        lstIds.add("No seleccionable");
        lstIds.addAll(lstConferences.stream().map(e->e.getId().toString()).toList());
        idConferenceBox.setItems(lstIds);
    }

    private void read(HasValue.ValueChangeEvent e){
        String id = e.getValue().toString();
        fillDataForm(id);
    }

    private void fillDataForm(String id){
        if (id.equals("No seleccionable")){
            cleanForm();
            showButtons("new");
        }else{
            UUID uid = UUID.fromString(id);
            var sp = conferenceRepository.findById(uid).orElse(null);
            if(sp != null){
                conferenceBinder.setBean(sp);
                showButtons("fill");
            }
        }
    }

    private void cleanForm(){
        conferenceBinder.setBean(new Conference());
    }

    private void update(){
        Conference conferenceData = conferenceBinder.getBean();
        if (conferenceData != null){
            Conference existConference = conferenceRepository.findById(conferenceData.getId()).orElse(null);
            if (existConference != null){
                conferenceRepository.save(conferenceData);
            }
        }
    }

    private void delete(){
        Conference conferenceData = conferenceBinder.getBean();
        if (conferenceData != null){
            Conference existConference = conferenceRepository.findById(conferenceData.getId()).orElse(null);
            if (existConference != null){
                conferenceRepository.delete(existConference);
            }
        }
    }

    private void save(){
        Conference conferenceData = conferenceBinder.getBean();
        if (conferenceData != null){
            conferenceRepository.save(conferenceData);
        }
    }

    private void showButtons(String str){
        switch (str){
            case "fill":
                createButton.setEnabled(false);
                createButton.setVisible(false);
                updateButton.setEnabled(true);
                updateButton.setVisible(true);
                deleteButton.setEnabled(true);
                deleteButton.setVisible(true);
                break;
            case "init":
                createButton.setEnabled(false);
                createButton.setVisible(false);
                updateButton.setEnabled(false);
                updateButton.setVisible(false);
                deleteButton.setEnabled(false);
                deleteButton.setVisible(false);
                break;
            case "new":
                createButton.setEnabled(true);
                createButton.setVisible(true);
                updateButton.setEnabled(false);
                updateButton.setVisible(false);
                deleteButton.setEnabled(false);
                deleteButton.setVisible(false);
                break;
            default:
                break;
        }
    }
}
