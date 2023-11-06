package com.application.views;

import com.application.security.SecurityUtils;
import com.application.views.security.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.BootstrapListener;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

/**
 * Custom {@link VaadinServiceInitListener} implementation which provides:
 * <li>setting of favicon via {@link BootstrapListener} adding</li>
 * <li>ability of rerouting to login page from any other pages in case of not active login session</li>
 *
 * @author Ilya Ryabukhin
 * @since 23.03.2023
 */
@Component
public class CustomServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.addBootstrapListener(response -> {
            final Element head = response.getDocument().head();
            head.append(
                    "<link rel=\"shortcut icon\" href=\"icons/favicon.svg\">");
        });

        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (!LoginView.class.equals(event.getNavigationTarget())
                && !SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }
}
