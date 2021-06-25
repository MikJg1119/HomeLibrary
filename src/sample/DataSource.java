package sample;

import sample.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static final String DB_NAME = "Library";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\aaaa\\IdeaProjects\\Music\\"+ DB_NAME;
    public static final String TABLE_AUTHORS = "authors";
    public static final String COLUMN_AUTHORS_ID = "_id";
    public static final String COLUMN_AUTHORS_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;
    public static final String COLUMN_ISBN ="_isbn";
    public static final String TABLE_BOOKS = "books";

    public static final String COLUMN_BOOK_TITLE = "title";


    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String QUERY_VIEW_BOOK_INFO_PREP = "SELECT " + COLUMN_AUTHORS_NAME + ", " +
            COLUMN_BOOK_TITLE + " FROM " + TABLE_AUTHORS_BOOK_VIEW +
            " WHERE " + COLUMN_BOOK_TITLE + " = ?";

    public static final String INSERT_AUTHOR = "INSERT INTO " + TABLE_AUTHORS +
            '(' + COLUMN_AUTHORS_NAME + ") VALUES(?)";
    public static final String INSERT_BOOK = "INSERT INTO "+TABLE_BOOKS+"("+COLUMN_AUTHORS_NAME+", "+COLUMN_BOOK_TITLE+") VALUES (?,?)";








    private PreparedStatement queryBookInfoView;
    private PreparedStatement insertIntoAuthors;
    private PreparedStatement insertIntoBooks;

    private PreparedStatement queryBooksByAuthorId;

    private PreparedStatement queryAuthors;
    private PreparedStatement queryBook;


    private Connection conn;
    private static DataSource instance = new DataSource();
    private DataSource(){


    }
    public static DataSource getInstance(){

        return instance;
    }

    public boolean open(){
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            queryBookInfoView = conn.prepareStatement(QUERY_VIEW_BOOK_INFO_PREP);
            insertIntoAuthors=conn.prepareStatement(INSERT_AUTHOR, Statement.RETURN_GENERATED_KEYS);
            insertIntoBooks = conn.prepareStatement(INSERT_BOOK,Statement.RETURN_GENERATED_KEYS);
            queryAuthors= conn.prepareStatement(QUERY_AUTHORS);
            queryBook=conn.prepareStatement(QUERY_BOOK);
            queryBooksByAuthorId=conn.prepareStatement(QUERY_BOOKS_BY_AUTHOR_ID);
            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to the db"+e.getMessage());
            return false;
        }
    }

    public void close (){

        try {
            if (queryBookInfoView !=null){
                queryBookInfoView.close();
            }
            if (insertIntoAuthors !=null){
                insertIntoAuthors.close();
            }
            if(insertIntoBooks !=null){
                insertIntoBooks.close();
            }
            if (queryBook !=null){
                queryBook.close();
            }
            if (queryAuthors !=null){
                queryAuthors.close();
            }
            if (queryBooksByAuthorId !=null){
                queryBooksByAuthorId.close();
            }
            if (conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: "+e.getMessage());
        }
    }
    public List<Author> queryAuthors(int sortOrder){
        StringBuilder sb = new StringBuilder("SELECT*FROM ");
        sb.append(TABLE_AUTHORS);
        if (sortOrder !=ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_AUTHORS_NAME);
            sb.append((" COLLATE NOCASE "));
            if (sortOrder == ORDER_BY_DESC){
                sb.append("DESC");
            }else {
                sb.append("ASC");
            }
        }
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())){

            List<Author> authors = new ArrayList<>();



        }catch (SQLException e){
            System.out.println("Query failed: "+e.getMessage());
            return null;
        }

    }
}
