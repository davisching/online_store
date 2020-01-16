package pers.dc.ols.resources;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "main")
@PropertySource("classpath:dev.properties")
public class DevProperties {

    private String faceStoringLocation;

    public String getFaceStoringLocation() {
        return faceStoringLocation;
    }

    public void setFaceStoringLocation(String faceStoringLocation) {
        this.faceStoringLocation = faceStoringLocation;
    }
}
