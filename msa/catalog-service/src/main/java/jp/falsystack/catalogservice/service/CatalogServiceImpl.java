package jp.falsystack.catalogservice.service;

import jp.falsystack.catalogservice.repository.CatalogRepository;
import jp.falsystack.catalogservice.vo.ResponseCatalog;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;

    @Override
    public List<ResponseCatalog> getAllCatalogs() {
        return catalogRepository.findAll().stream().map(ResponseCatalog::fromCatalog).toList();
    }
}
