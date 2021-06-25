package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import sample.model.Author;

public class Controller {

    @FXML
    public void listAuthors(){
        Task<ObservableList<Author>> task = new
    }
}

class GetAllAuthorsTask extends Task{
    @Override
    public ObservableList<Author> call(){
        return FXCollections.observableArrayList(DataSource.getInstance().queryAuthors(DataSource.ORDER_BY_ASC));
    }
}
