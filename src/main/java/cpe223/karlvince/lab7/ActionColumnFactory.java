package cpe223.karlvince.lab7;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Callback;

import java.util.function.Consumer;

public final class ActionColumnFactory {

    private ActionColumnFactory() {}

    public static <T> void install(TableColumn<T, Void> column,
                                   Consumer<T> onUpdate,
                                   Consumer<T> onDelete) {

        column.setCellFactory(new Callback<TableColumn<T, Void>, TableCell<T, Void>>() {
            @Override
            public TableCell<T, Void> call(TableColumn<T, Void> param) {
                return new TableCell<T, Void>() {

                    private final MenuButton menuButton = buildMenuButton(this, onUpdate, onDelete);

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(menuButton);
                            setAlignment(Pos.CENTER);
                        }
                    }
                };
            }
        });
    }

    private static <T> MenuButton buildMenuButton(TableCell<T, Void> cell,
                                                   Consumer<T> onUpdate,
                                                   Consumer<T> onDelete) {

        MenuItem updateItem = new MenuItem("Update", createEditIcon());
        updateItem.setOnAction(e -> {
            T record = cell.getTableView().getItems().get(cell.getIndex());
            onUpdate.accept(record);
        });

        MenuItem deleteItem = new MenuItem("Delete", createDeleteIcon());
        deleteItem.setOnAction(e -> {
            T record = cell.getTableView().getItems().get(cell.getIndex());
            onDelete.accept(record);
        });

        MenuButton mb = new MenuButton();
        mb.setGraphic(createTriggerIcon());
        mb.getItems().addAll(updateItem, deleteItem);
        mb.setStyle("-fx-background-color: transparent; -fx-padding: 2;");
        return mb;
    }

    private static SVGPath createTriggerIcon() {
        SVGPath icon = new SVGPath();
        icon.setContent("M12 3H5a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7 "
                + "M18.375 2.625a1 1 0 0 1 3 3l-9.013 9.014a2 2 0 0 1-.853.505l-2.873.84a.5.5 0 0 1-.62-.62l.84-2.873a2 2 0 0 1 .506-.852z");
        icon.setStrokeWidth(2);
        icon.setScaleX(0.75);
        icon.setScaleY(0.75);
        icon.setStroke(Color.web("#00a0e4ff"));
        icon.setFill(Color.TRANSPARENT);
        return icon;
    }

    private static SVGPath createEditIcon() {
        SVGPath icon = new SVGPath();
        icon.setContent("M14.364 13.634a2 2 0 0 0-.506.854l-.837 2.87a.5.5 0 0 0 .62.62l2.87-.837a2 2 0 0 0 .854-.506l4.013-4.009a1 1 0 0 0-3.004-3.004z "
                + "M14.487 7.858A1 1 0 0 1 14 7V2 "
                + "M20 19.645V20a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h8a2.4 2.4 0 0 1 1.704.706l2.516 2.516 "
                + "M8 18h1");
        icon.setStrokeWidth(2);
        icon.setScaleX(0.55);
        icon.setScaleY(0.55);
        icon.setStroke(Color.web("#1a73e8"));
        icon.setFill(Color.TRANSPARENT);
        return icon;
    }

    private static SVGPath createDeleteIcon() {
        SVGPath icon = new SVGPath();
        icon.setContent("M6 22a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h8a2.4 2.4 0 0 1 1.704.706l3.588 3.588A2.4 2.4 0 0 1 20 8v12a2 2 0 0 1-2 2z "
                + "M14 2v5a1 1 0 0 0 1 1h5 "
                + "M14.5 12.5l-5 5 "
                + "M9.5 12.5l5 5");
        icon.setStrokeWidth(2);
        icon.setScaleX(0.55);
        icon.setScaleY(0.55);
        icon.setStroke(Color.web("#d93025"));
        icon.setFill(Color.TRANSPARENT);
        return icon;
    }
}
