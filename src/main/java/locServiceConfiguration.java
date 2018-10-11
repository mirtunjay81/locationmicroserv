import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class locServiceConfiguration extends Configuration {

    @NotNull
    private String login;

    @NotNull
    private String password;

    @JsonProperty
    public String getLogin() {
        return this.login;
    }

    @JsonProperty
    public String getPassword() {
        return this.password;
    }


    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();


    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
}
