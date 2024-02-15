package Client.Objects;

public class Item {
    int id;
    String url;
    String file_name;

    String host_name;

    public Item(int id, String url, String file_name, String host_name) {
        this.id = id;
        this.url = url;
        this.file_name = file_name;
        this.host_name = host_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", file_name='" + file_name + '\'' +
                ", host_name='" + host_name + '\'' +
                '}';
    }

}
