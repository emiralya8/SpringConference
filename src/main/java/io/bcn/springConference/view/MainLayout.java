package io.bcn.springConference.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Route;

@CssImport("./css/layout.css")
@Route("/")
public class MainLayout extends AppLayout {

    private H2 conferenceTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addNavbarContent();
        addDrawerContent();
    }

    private void addNavbarContent() {
        var toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");
        toggle.setTooltipText("Menu toggle");

        conferenceTitle = new H2();

        var header = new Header(toggle, conferenceTitle);
        Avatar user = new Avatar("Enrique Miralles Yataco");
        user.addThemeVariants(AvatarVariant.LUMO_LARGE);
        user.addClassNames("right-top-avatar");
        //user.setImage(pictureUrl);
        header.add(user);
        addToNavbar(false, header);
    }

    private void addDrawerContent() {
        var appName = new Span("Conference");
        addToDrawer(appName, new Scroller(createSideNav()));

    }

    private SideNav createSideNav() {
        SideNav nav = new SideNav();
        SideNavItem navigableParent = new SideNavItem("Menu",
                "/home");
        navigableParent.setId("Menu");
        navigableParent.addItem(new SideNavItem("Conference",ConferenceView.class, VaadinIcon.AIRPLANE.create()));
        navigableParent.addItem(new SideNavItem("Speaker", SpeakerView.class, VaadinIcon.BUILDING.create()));
        nav.addItem(navigableParent);

        return nav;
    }
}
