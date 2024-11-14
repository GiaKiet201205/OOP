package project;


import java.util.List;

public interface IModify<T> {
    void add(T t);
    void edit(T t);
    void delete(int id);
    public void importTo(String filePath);
    void exportFrom(String filePath);
    List<T> search(List<T> t,String attribute, String value);
}

