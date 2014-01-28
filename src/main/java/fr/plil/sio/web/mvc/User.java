package fr.plil.sio.web.mvc;

public class User {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return !((this.username == null) ? (other.username != null) : !this.username.equals(other.username));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }
}
