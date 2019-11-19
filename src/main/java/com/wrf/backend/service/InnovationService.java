package com.wrf.backend.service;

import com.wrf.backend.DbApi;
import com.wrf.backend.entity.Innovation;
import com.wrf.backend.entity.InnovationCategory;
import com.wrf.backend.exception.ErrorCode;
import com.wrf.backend.exception.RestException;
import com.wrf.backend.model.request.ImageRequestModel;
import com.wrf.backend.model.request.UserInnovationRequest;
import com.wrf.backend.model.response.InnovationResponse;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InnovationService {

    @Autowired
    DbApi dbApi;

    @Autowired
    HibernateTemplate hibernateTemplate;

    @Autowired
    ImageService imageService;

    @Autowired
    AuthService authService;

    @Value("${image.path}")
    String imagePath;

    public InnovationResponse addInnovation(UserInnovationRequest innovationRequest) {
        InnovationCategory category = hibernateTemplate.get(InnovationCategory.class, innovationRequest.getCategoryId());
        if (category == null) {
            throw new RestException(ErrorCode.RECORD_NOT_FOUND.getCode(), "Категория не найдена");
        }

        Innovation innovation = new Innovation(
                innovationRequest.getText(),
                innovationRequest.getCategoryId(),
                authService.getUserInfo().getId()
        );

        String innovationId = hibernateTemplate.save(innovation).toString();
        return new InnovationResponse(innovationId);
    }

    public List<InnovationCategory> getAllCategories() {
        DetachedCriteria criteria = DetachedCriteria.forClass(InnovationCategory.class);
        return (List<InnovationCategory>) hibernateTemplate.findByCriteria(criteria);
    }

    public void saveImageByInnovationId(ImageRequestModel model) {
        Innovation innovation = hibernateTemplate.get(Innovation.class, model.getId());
        if (innovation == null) {
            throw new RestException(ErrorCode.RECORD_NOT_FOUND.getCode(), "Инновация не найдена");
        }
        String imageUuid = UUID.randomUUID().toString();
        imageService.saveImage(model.getImage(), imagePath + imageUuid);
        innovation.setImageUuid(imageUuid);
        hibernateTemplate.saveOrUpdate(innovation);
    }
}
