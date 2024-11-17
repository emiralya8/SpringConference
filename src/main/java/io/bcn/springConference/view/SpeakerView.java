package io.bcn.springConference.view;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.data.binder.Binder;
import io.bcn.springConference.Converter.ListToSetConverter;
import io.bcn.springConference.model.Conference;
import io.bcn.springConference.model.Speaker;
import io.bcn.springConference.repository.ConferenceRepository;
import io.bcn.springConference.repository.SpeakerRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@PermitAll
@PageTitle("Speaker")
@Route(value = "/Speaker", layout = MainLayout.class)
public class SpeakerView extends Composite<VerticalLayout> {

    private final SpeakerRepository speakerRepository;
    private final ConferenceRepository conferenceRepository;
    private final ComboBox idSpeakerBox = new ComboBox();
    //private final MultiSelectListBox<Conference> conferences = new MultiSelectListBox<>();
    private final FormLayout formLayout = new FormLayout();
    private final TextField name = new TextField("Name");
    private final TextField bio = new TextField("Bio");
    private final TextField email = new TextField("Email");

    private final Button createButton = new Button("Create");
    private final Button updateButton = new Button("Update");
    private final Button deleteButton = new Button("Delete");
    private final Binder<Speaker> speakerBinder = new Binder<>();
    private final HorizontalLayout buttonLayout = new HorizontalLayout();

    public SpeakerView(SpeakerRepository speakerRepository, ConferenceRepository conferenceRepository){
        this.speakerRepository = speakerRepository;
        this.conferenceRepository = conferenceRepository;
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        buttonLayout.add(createButton, updateButton, deleteButton);
        addStylesComponents();
        //fillCombobox();
        addComponentsListener();
        formLayout.add(idSpeakerBox, name, bio, email); //, conferences
        uploadIdItems();
        getContent().add(formLayout);
        showButtons("init");
        getContent().add(buttonLayout);
        bindingModelForm();
    }

    private void bindingModelForm(){
        speakerBinder.forField(name).bind(Speaker::getName, Speaker::setName);
        speakerBinder.forField(bio).bind(Speaker::getBio,Speaker::setBio);
        speakerBinder.forField(email).bind(Speaker::getEmail,Speaker::setEmail);
//        speakerBinder.forField(conferences)
//                .withConverter(new ListToSetConverter())
//                .bind(Speaker::getConferences, Speaker::setConferences);
    }

    private void addStylesComponents(){
        idSpeakerBox.setLabel("Id");
//        conferences.setMaxWidth("100px");
//        conferences.setHeight("150px");
        //conferences.label.setLabel("Conferences");
        idSpeakerBox.setWidth("1000px");
        idSpeakerBox.setMinWidth("50px");
        idSpeakerBox.setMaxWidth("350px");
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));
        buttonLayout.getStyle().set("flex-wrap", "wrap");
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
    }

    private void addComponentsListener(){
        createButton.addClickListener(e -> save());
        updateButton.addClickListener(e -> update());
        deleteButton.addClickListener(e -> delete());
        idSpeakerBox.addValueChangeListener(e -> read(e));
    }

//    private void fillCombobox(){
//        HashSet<Conference> lstConferences = new HashSet<>(conferenceRepository.findAll()) ;
//        conferences.setItems(lstConferences);
//    }

    private void uploadIdItems(){
        List<Speaker> lstSpeakers = speakerRepository.findAll();
        List<String> lstIds = new ArrayList<>();
        lstIds.add("No seleccionable");
        lstIds.addAll(lstSpeakers.stream().map(e->e.getId().toString()).toList());
        idSpeakerBox.setItems(lstIds);

    }

    private void read(HasValue.ValueChangeEvent e){
        String id = e.getValue().toString();
        fillDataForm(id);
    }

    @Transactional
    private void fillDataForm(String id){
        if (id.equals("No seleccionable")){
            cleanForm();
            showButtons("new");
        }else{
            UUID uid = UUID.fromString(id);
            var sp = speakerRepository.findById(uid).orElseThrow();
            //Hibernate.initialize(sp.getConferences());
            if(sp != null){
                speakerBinder.setBean(sp);
                showButtons("fill");
            }
        }
    }

    private void cleanForm(){
        speakerBinder.setBean(new Speaker());
    }

    private void update(){
        Speaker speakerData = speakerBinder.getBean();
        if (speakerData != null){
            Speaker espeaker = speakerRepository.findById(speakerData.getId()).orElse(null);
            if (espeaker != null){
                speakerRepository.save(speakerData);
            }
        }
    }

    private void delete(){
        Speaker speakerData = speakerBinder.getBean();
        if (speakerData != null){
            Speaker espeaker = speakerRepository.findById(speakerData.getId()).orElse(null);
            if (espeaker != null){
                speakerRepository.delete(speakerData);
            }
        }
    }

    private void save(){
        Speaker speakerData = speakerBinder.getBean();
        if (speakerData != null){
            speakerRepository.save(speakerData);
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
