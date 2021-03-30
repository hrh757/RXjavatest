package hrh.commonlib.commonlib.model;

public class AppInfoEntity {


    /**
     * id : 14
     * app_type : 1
     * app_name : service
     * package_name : com.ycsoft.smartbox.service
     * status : 1
     * created_at : 1576563187
     * updated_at : 1576563187
     * deleted_at : 0
     */

    private int id;
    private int app_type;
    private String app_name;
    private String package_name;
    private int status;
    private long created_at;
    private long updated_at;
    private int deleted_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApp_type() {
        return app_type;
    }

    public void setApp_type(int app_type) {
        this.app_type = app_type;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public int getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(int deleted_at) {
        this.deleted_at = deleted_at;
    }
}
