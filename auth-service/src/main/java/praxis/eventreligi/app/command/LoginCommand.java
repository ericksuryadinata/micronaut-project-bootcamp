package praxis.eventreligi.app.command;
import javax.validation.constraints.NotEmpty;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class LoginCommand{
    
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
