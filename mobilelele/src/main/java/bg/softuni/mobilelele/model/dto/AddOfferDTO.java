package bg.softuni.mobilelele.model.dto;

import bg.softuni.mobilelele.model.enums.EngineEnum;

import javax.validation.constraints.NotNull;

public class AddOfferDTO {
    @NotNull
    private EngineEnum engine;

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }
}
