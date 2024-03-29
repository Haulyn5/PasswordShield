package cn.adminzero.passwordshield_demo0.entity;


/**
 * APP的范围内的账户密码
 */
public class PasswordItem {

    /**
     * 主属性id
     */
    private int id;

    private String name;
    /**
     * app登录账户名
     */
    private String account;
    /**
     * app登录密码
     */
    private String password;
    /**
     * app或者web类型
     */
    private int type;
    /**
     * 应用程序包名(非浏览器)
     * 或者  浏览器网页域名
     */
    private String uri;
    /**
     * 备注信息
     */
    private String note;

    /**
     * 日期描述
     */
    private String recordtime;

    /**
     * important message
     **/
    private boolean isImportant;


    public PasswordItem() {
        isImportant = false;//默认为flase

    }


    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean getImport() {
        return isImportant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return recordtime;
    }

    public void setTime(String recordtime) {
        this.recordtime = recordtime;
    }
}