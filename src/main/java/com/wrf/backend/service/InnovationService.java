package com.wrf.backend.service;

import com.wrf.backend.config.AppConfig;
import com.wrf.backend.db_api.InnovationDbApi;
import com.wrf.backend.entity.Innovation;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.ErrorMessage;
import com.wrf.backend.model.request.ImageRequestModel;
import com.wrf.backend.model.request.UserInnovationRequest;
import com.wrf.backend.model.response.CategoryDTO;
import com.wrf.backend.model.response.InnovationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InnovationService {

    final InnovationDbApi innovationDbApi;

    final HibernateTemplate hibernateTemplate;

    final ImageService imageService;

    final AuthService authService;

    final AppConfig appConfig;

    @Transactional
    public InnovationDTO addInnovation(final UserInnovationRequest request) {
        var innovation = new Innovation(
                request.getText(),
                request.getCategoryId(),
                authService.getUserInfo().getId()
        );

        var innovationId = (String) hibernateTemplate.save(innovation);
        return new InnovationDTO(innovationId);
    }

    public List<CategoryDTO> getAllCategories() {
        return innovationDbApi.getAllCategories();
    }

    public void saveImageByInnovationId(final ImageRequestModel model) {
        var innovation = Optional.ofNullable(hibernateTemplate.get(Innovation.class, model.getId()))
                .orElseThrow(() -> new BusinessException(ErrorMessage.INNOVATION_IS_NOT_FOUND));

        String imageUuid = UUID.randomUUID().toString();
        imageService.saveImage(model.getImage(), appConfig.getInnovationImagePath() + imageUuid, imageUuid);
        innovation.setImageUuid(imageUuid);
        hibernateTemplate.saveOrUpdate(innovation);
    }
}
