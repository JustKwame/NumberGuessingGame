package com.example.examplefeature.ui;
import com.vaadin.flow.component.UI;
import com.vaadin.copilot.shaded.checkerframework.checker.guieffect.qual.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;

@Route(value = "")
@StyleSheet("./styles/game-styles.css")
@JavaScript("https://cdn.jsdelivr.net/npm/@tsparticles/confetti@3.0.3/tsparticles.confetti.bundle.min.js")

public class TaskListView extends VerticalLayout {
   NumberGuessing game = new NumberGuessing();

   public TaskListView(){
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        addClassName("main-view-bg");

        VerticalLayout cardContainer = new VerticalLayout();
        cardContainer.setAlignItems(Alignment.CENTER);
        cardContainer.setJustifyContentMode(JustifyContentMode.CENTER);
        cardContainer.addClassName("game-card");

        H1 Title = new H1("Number Guessing Game");
        Title.addClassName("game-title");

        Paragraph instructions = new Paragraph("Guess a number between 1 - 30:"); 
        Paragraph AttemptsRemaining = new Paragraph("Attempts Remaining: " + game.counter);
        AttemptsRemaining.addClassName("game-feedback");

        IntegerField Userinput_Integers = new IntegerField();
        Userinput_Integers.setPlaceholder("Enter a number...");
        Userinput_Integers.addClassName("game-input");

        Button submitButton = new Button("Submit Guess");
        submitButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        submitButton.addClassName("game-submit-btn");
        submitButton.addClickShortcut(com.vaadin.flow.component.Key.ENTER);

submitButton.addClickListener(event -> {
    Integer currentGuess = Userinput_Integers.getValue();

    if (currentGuess != null){
        String gameResponse = game.Checks(currentGuess);

        AttemptsRemaining.setText(gameResponse);

        if (gameResponse.contains("You guessed the number")) {
            AttemptsRemaining.getStyle().set("color", "#2e7d32");
            UI.getCurrent().getPage().executeJs("confetti();");
        // Green for winning!

        } else if (gameResponse.contains("Number out of bound, Try again ⚠️")) {
                AttemptsRemaining.getStyle().set("color", "#540b8f");

        }else if (gameResponse.contains("run out of attempts")) {
            AttemptsRemaining.getStyle().set("color", "#d32f2f"); 
            // Dark red for losing
        } else {
            AttemptsRemaining.getStyle().set("color", "#ff9800"); // Orange/Amber for hints (Warm/Cold/Close)
        }

        Userinput_Integers.clear();
    }
});

        cardContainer.add(Title, instructions, Userinput_Integers, submitButton, AttemptsRemaining);
        add(cardContainer);
   }
}