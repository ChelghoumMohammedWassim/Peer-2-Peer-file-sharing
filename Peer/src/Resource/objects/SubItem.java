package Resource.objects;

public class SubItem {
    int id;
    String file_name;
    String file_path;

    public SubItem(int id, String file_name, String file_path) {
        this.id = id;
        this.file_name = file_name;
        this.file_path = file_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
