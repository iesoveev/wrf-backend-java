package com.wrf.backend.service;

import com.wrf.backend.config.AppConfig;
import com.wrf.backend.db_api.InnovationDbApi;
import com.wrf.backend.db_api.repository.InnovationRepository;
import com.wrf.backend.entity.Innovation;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.ErrorMessage;
import com.wrf.backend.model.request.ImageRequestModel;
import com.wrf.backend.model.request.UserInnovationRequest;
import com.wrf.backend.model.response.CategoryDTO;
import com.wrf.backend.model.response.GeneralIdDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InnovationService {

    final InnovationDbApi innovationDbApi;

    final HibernateTemplate hibernateTemplate;

    final ImageService imageService;

    final AuthService authService;

    final AppConfig appConfig;

    final InnovationRepository innovationRepository;

    public GeneralIdDTO addInnovation(final UserInnovationRequest request) {
        String imageUuid = UUID.randomUUID().toString();
        var innovation = new Innovation(
                request.getText(),
                request.getCategoryId(),
                authService.getUserInfo().getId(),
                imageUuid
        );

        //async call
        imageService.saveImage(request.getImage(), appConfig.getInnovationBucket(), imageUuid);

        return new GeneralIdDTO(innovationRepository.save(innovation).getId());

    }

    public List<CategoryDTO> getAllCategories() {
        return innovationDbApi.getAllCategories();
    }

    public void saveImageByInnovationId(final ImageRequestModel model) {
        var innovation = innovationRepository.findById(model.getId())
                .orElseThrow(() -> new BusinessException(ErrorMessage.INNOVATION_IS_NOT_FOUND));

        String imageUuid = UUID.randomUUID().toString();
        imageService.saveImage(model.getImage(), appConfig.getInnovationBucket() + imageUuid, imageUuid);
        innovation.setImageUuid(imageUuid);
        innovationRepository.save(innovation);
    }

    public String getImage(final String imageUuid) {
        return Base64.encodeBase64String(imageService.getImage(appConfig.getInnovationBucket(), imageUuid));
    }
}
