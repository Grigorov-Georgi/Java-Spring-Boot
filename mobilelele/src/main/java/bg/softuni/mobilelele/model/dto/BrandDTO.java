package bg.softuni.mobilelele.model.dto;

import java.util.ArrayList;
import java.util.List;

public class BrandDTO {
    private List<ModelDTO> models;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelDTO> getModels() {
        return models;
    }

    public void setModels(List<ModelDTO> models) {
        this.models = models;
    }

    public BrandDTO addModel(ModelDTO modelDTO){
        if (this.models == null){
            this.models = new ArrayList<>();
        }
        this.models.add(modelDTO);
        return this;
    }
}
