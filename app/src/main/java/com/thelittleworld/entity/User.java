package com.thelittleworld.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

@Entity(
        nameInDb = "user"
)
public class User {

    @Id
    public Long id;

    public String login;

    public String nick;

    public String sex;

    public String email;

    public Date dataVersion;

    public Date updated;

    public Date created;

    @Generated(hash = 615851640)
    public User(Long id, String login, String nick, String sex, String email,
                Date dataVersion, Date updated, Date created) {
        this.id = id;
        this.login = login;
        this.nick = nick;
        this.sex = sex;
        this.email = email;
        this.dataVersion = dataVersion;
        this.updated = updated;
        this.created = created;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataVersion() {
        return this.dataVersion;
    }

    public void setDataVersion(Date dataVersion) {
        this.dataVersion = dataVersion;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
