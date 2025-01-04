package com.application.views.players;

import com.application.model.Player;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class PlayerForm extends FormLayout {
    TextField name = new TextField("Имя");
    DatePicker birthday = new DatePicker("День рождения");
    ComboBox<Player.ActivityStatus> activityStatus = new ComboBox<>("Статус");

    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Player> binder = new BeanValidationBinder<>(Player.class);

    private Player player;

    public PlayerForm(List<Player.ActivityStatus> statuses) {
        addClassName("player-form");
        binder.bindInstanceFields(this);

        DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
        singleFormatI18n.setDateFormat("dd-MM-yyyy");
        birthday.setI18n(singleFormatI18n);
        birthday.setPlaceholder("dd-MM-yyyy");

        activityStatus.setItems(statuses);
        activityStatus.setItemLabelGenerator(Player.ActivityStatus::getTextualStatus);

        add(name,
                birthday,
                activityStatus,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, player)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setPlayer(Player player) {
        this.player = player;
        binder.readBean(player);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(player);
            fireEvent(new SaveEvent(this, player));
        } catch (ValidationException e) {
            //TODO: logging
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class PlayerFormEvent extends ComponentEvent<PlayerForm> {
        private final Player player;

        protected PlayerFormEvent(PlayerForm source, Player player) {
            super(source, false);
            this.player = player;
        }

        public Player getPlayer() {
            return player;
        }
    }

    public static class SaveEvent extends PlayerFormEvent {
        SaveEvent(PlayerForm source, Player player) {
            super(source, player);
        }
    }

    public static class DeleteEvent extends PlayerFormEvent {
        DeleteEvent(PlayerForm source, Player player) {
            super(source, player);
        }
    }

    public static class CloseEvent extends PlayerFormEvent {
        CloseEvent(PlayerForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
