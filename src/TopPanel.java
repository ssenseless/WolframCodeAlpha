import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TopPanel extends GridPane {
    private BorderPane borderPane;
    private Label verticalSizeLabel, ruleNumLabel;
    private Spinner<Integer> verticalSizeSpinner, ruleNumSpinner;
    private Button button;
    private final int MIN_VERTICAL_SIZE = 10, MAX_VERTICAL_SIZE = 93, DEFAULT_VERTICAL_SIZE = 93,
            MIN_RULE_NUM = 0, MAX_RULE_NUM = 255, DEFAULT_RULE_NUM = 0;

    public TopPanel(Main main) {
        initialize();

        add(ruleNumLabel, 1, 1);
        add(verticalSizeLabel, 1, 2);
        add(ruleNumSpinner, 2, 1);
        add(verticalSizeSpinner, 2, 2);
        add(button, 2, 3);

        setHgap(10);
        setVgap(10);

        button.setOnAction(e -> main.startWolfram(ruleNumSpinner.getValue(), verticalSizeSpinner.getValue()));
    }

    public void initialize() {
        ruleNumSpinner = new Spinner<>(MIN_RULE_NUM, MAX_RULE_NUM, DEFAULT_RULE_NUM);
        verticalSizeSpinner = new Spinner<>(MIN_VERTICAL_SIZE, MAX_VERTICAL_SIZE, DEFAULT_VERTICAL_SIZE);

        ruleNumLabel = new Label("Rule number:");
        verticalSizeLabel = new Label("Vertical Size:");

        button = new Button("Load");

        borderPane = new BorderPane();
    }
}
